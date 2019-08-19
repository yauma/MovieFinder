package com.example.jaimequeraltgarrigos.data.mappper

import com.example.jaimequeraltgarrigos.data.factory.DataFactory
import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import com.example.jaimequeraltgarrigos.domain.model.Movie
import org.junit.Test
import kotlin.test.assertEquals

class MovieMapperTest {
    val mapper: MovieMapper = MovieMapper()

    @Test
    fun movieIsMappedCorrectlyFromMovieEntity() {
        val entity: MovieEntity = DataFactory.randomMovieMovieEntity()
        val movie: Movie = mapper.mapFromEntity(entity)

        assertEqualsDataMapped(entity, movie)
    }

    @Test
    fun movieEntityIsMappedCorrectlyFromMovie() {
        val movie: Movie = DataFactory.randomMovie()
        val entity: MovieEntity = mapper.mapToEntity(movie)

        assertEqualsDataMapped(entity, movie)
    }

    private fun assertEqualsDataMapped(entity: MovieEntity, movie: Movie) {
        assertEquals(entity.id, movie.id)
        assertEquals(entity.backdrop_path, movie.backdrop_path)
        assertEquals(entity.original_title, movie.original_title)
        assertEquals(entity.isBookmarked, movie.isBookmarked)
    }
}