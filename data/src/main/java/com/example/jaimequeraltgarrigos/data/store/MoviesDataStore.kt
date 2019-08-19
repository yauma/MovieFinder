package com.example.jaimequeraltgarrigos.data.store

import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface MoviesDataStore {
    fun getMovies(): Observable<List<MovieEntity>>

    fun saveMovies(movies: List<MovieEntity>): Completable

    fun clearMovies(): Completable

    fun getBookmarkedMovies(): Observable<List<MovieEntity>>

    fun setBookmarkedMovie(id: String): Completable

    fun setUnBookmarkedMovie(id: String): Completable
}