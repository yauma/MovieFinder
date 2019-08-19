package com.example.jaimequeraltgarrigos.data

import com.example.jaimequeraltgarrigos.data.mappper.MovieMapper
import com.example.jaimequeraltgarrigos.data.repository.MovieDataStoreFactory
import com.example.jaimequeraltgarrigos.data.repository.MoviesCache
import com.example.jaimequeraltgarrigos.domain.model.Movie
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

open class MoviesDataRepository @Inject constructor(val mapper: MovieMapper, val moviesCache: MoviesCache,
                                               val factory: MovieDataStoreFactory) : MoviesRepository {
    override fun unbookmarkMovie(movieId: String): Completable {
        return factory.getCacheDataStore().setUnBookmarkedMovie(movieId)
    }

    override fun getBookMarkedMovies(): Observable<List<Movie>> {
        return factory.getCacheDataStore().getBookmarkedMovies()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkMovie(movieId: String): Completable {
        return factory.getCacheDataStore().setBookmarkedMovie(movieId)
    }

    override fun getMovies(): Observable<List<Movie>> {
        return Observable.zip(moviesCache.areMoviesCached().toObservable(), moviesCache.isCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areMoviesCached, isCacheExpired ->
                    Pair(areMoviesCached, isCacheExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getMovies()
                }
                .flatMap {
                    factory.getCacheDataStore().saveMovies(it).andThen(Observable.just(it))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}





