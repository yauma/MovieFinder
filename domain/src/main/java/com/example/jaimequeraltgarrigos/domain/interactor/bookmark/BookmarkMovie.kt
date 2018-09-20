package com.example.jaimequeraltgarrigos.domain.interactor.bookmark

import com.example.jaimequeraltgarrigos.domain.executor.PostExecutionThread
import com.example.jaimequeraltgarrigos.domain.interactor.CompletableUseCase
import com.example.jaimequeraltgarrigos.domain.interactor.ObservableUseCase
import com.example.jaimequeraltgarrigos.domain.model.Movie
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BookmarkMovie @Inject constructor(private val moviesRespostory: MoviesRepository,
                                        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkMovie.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params != null) {
            return moviesRespostory.bookmarkMovie(params.id)
        } else {
            throw IllegalArgumentException("Params can't be null")
        }
    }

    data class Params(val id: String) {
        companion object {
            fun forMovie(id: String): Params {
                return Params(id)
            }
        }
    }

}