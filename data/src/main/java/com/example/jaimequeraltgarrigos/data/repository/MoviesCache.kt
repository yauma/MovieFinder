package com.example.jaimequeraltgarrigos.data.repository

import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MoviesCache {
    fun getMovies(): Observable<List<MovieEntity>>

    fun clearMovies(): Completable

    fun saveMovies(movies: List<MovieEntity>): Completable

    fun getBookmarkedMovies(): Observable<List<MovieEntity>>

    fun setBookmarkedMovie(id: String): Completable

    fun setUnBookmarkedMovie(id: String): Completable

    fun areMoviesCached(): Single<Boolean>

    fun setLastCachedTime(lastCache: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}