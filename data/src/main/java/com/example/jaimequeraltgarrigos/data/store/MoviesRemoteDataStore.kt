package com.example.jaimequeraltgarrigos.data.store

import com.example.jaimequeraltgarrigos.data.repository.MoviesRemote
import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

open class MoviesRemoteDataStore @Inject constructor(val moviesRemote: MoviesRemote): MoviesDataStore {
    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesRemote.getMovies()
    }

    override fun saveMovies(movies: List<MovieEntity>): Completable  {
        return throw UnsupportedOperationException("Saving Movies is not supported here")
    }

    override fun clearMovies(): Completable {
        return throw UnsupportedOperationException("Clearing Movies is not supported here")
    }

    override fun getBookmarkedMovies(): Observable<List<MovieEntity>> {
        return throw UnsupportedOperationException("Bookmarked Movies are not supported here")
    }

    override fun setBookmarkedMovie(id: String): Completable {
        return throw UnsupportedOperationException("Bookmarked Movies are not supported here")
    }

    override fun setUnBookmarkedMovie(id: String): Completable {
        return throw UnsupportedOperationException("Bookmarked Movies are not supported here")
    }
}