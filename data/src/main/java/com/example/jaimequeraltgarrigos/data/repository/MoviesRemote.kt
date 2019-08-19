package com.example.jaimequeraltgarrigos.data.repository

import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import io.reactivex.Observable

interface MoviesRemote {
    fun getMovies(): Observable<List<MovieEntity>>
}