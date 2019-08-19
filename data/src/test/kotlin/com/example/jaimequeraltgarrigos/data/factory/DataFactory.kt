package com.example.jaimequeraltgarrigos.data.factory

import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import com.example.jaimequeraltgarrigos.domain.model.Movie
import java.util.*

object DataFactory {

    fun randomInt(): Int {
        return Random().nextInt(50) + 1
    }

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomMovie(): Movie {
        return Movie(randomInt(), randomUuid(), randomUuid(), randomBoolean())
    }

    fun randomMovieMovieEntity(): MovieEntity {
        return MovieEntity(randomInt(), randomUuid(), randomUuid(), randomBoolean())
    }

    fun randomBookmarkedMovieEntity(): MovieEntity {
        return MovieEntity(randomInt(), randomUuid(), randomUuid(), true)
    }

    fun getMoviesLEntityist(count: Int): List<MovieEntity> {
        val movieList = mutableListOf<MovieEntity>()
        repeat(count) {
            movieList.add(randomMovieMovieEntity())
        }
        return movieList
    }

    fun getBookmarkedMovieEntityList(count: Int): List<MovieEntity> {
        val bookmarkedMovieList = mutableListOf<MovieEntity>()
        repeat(count) {
            bookmarkedMovieList.add(randomBookmarkedMovieEntity())
        }
        return bookmarkedMovieList
    }
}
