package com.example.jaimequeraltgarrigos.data.store

import com.example.jaimequeraltgarrigos.data.repository.MoviesCache
import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class MoviesCacheDataStore @Inject constructor(val moviesCache: MoviesCache) : MoviesDataStore {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getMovies()
    }

    override fun saveMovies(movies: List<MovieEntity>): Completable {
        return moviesCache.saveMovies(movies).andThen(moviesCache.setLastCachedTime(System.currentTimeMillis()))
    }

    override fun clearMovies(): Completable {
        return moviesCache.clearMovies()
    }

    override fun getBookmarkedMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getBookmarkedMovies()
    }

    override fun setBookmarkedMovie(id: String): Completable {
        return moviesCache.setBookmarkedMovie(id)
    }

    override fun setUnBookmarkedMovie(id: String): Completable {
        return moviesCache.setUnBookmarkedMovie(id)
    }
}