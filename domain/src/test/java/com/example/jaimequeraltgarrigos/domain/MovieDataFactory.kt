package com.example.jaimequeraltgarrigos.domain

import com.example.jaimequeraltgarrigos.domain.model.Movie
import java.util.*

object MovieDataFactory {

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

    fun randomBookmarked(): Movie {
        return Movie(randomInt(), randomUuid(), randomUuid(), true)
    }

    fun getMoviesList(count: Int): List<Movie> {
        val movieList = mutableListOf<Movie>()
        repeat(count) {
            movieList.add(randomMovie())
        }
        return movieList
    }

    fun getBookmarkedMoviesList(count: Int): List<Movie> {
        val bookmarkedMovieList = mutableListOf<Movie>()
        repeat(count) {
            bookmarkedMovieList.add(randomBookmarked())
        }
        return bookmarkedMovieList
    }
}
