package com.example.jaimequeraltgarrigos.data.mappper

import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import com.example.jaimequeraltgarrigos.domain.model.Movie
import javax.inject.Inject

open class MovieMapper @Inject constructor() : EntityMapper<MovieEntity, Movie> {
    override fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(entity.id, entity.backdrop_path, entity.original_title, entity.isBookmarked)
    }

    override fun mapToEntity(domain: Movie): MovieEntity {
        return MovieEntity(domain.id, domain.backdrop_path, domain.original_title, domain.isBookmarked)
    }
}