package com.example.jaimequeraltgarrigos.domain.repository

import com.example.jaimequeraltgarrigos.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable

interface MoviesRepository {

    fun getMovies(): Observable<List<Movie>>

    fun bookmarkMovie(movieId: String): Completable

    fun unbookmarkMovie(movieId: String): Completable

    fun getBookMarkedMovies(): Observable<List<Movie>>
}