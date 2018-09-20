package com.example.jaimequeraltgarrigos.domain.interactor.browse

import com.example.jaimequeraltgarrigos.domain.executor.PostExecutionThread
import com.example.jaimequeraltgarrigos.domain.interactor.ObservableUseCase
import com.example.jaimequeraltgarrigos.domain.model.Movie
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies @Inject constructor(private val moviesRespostory: MoviesRepository,
                                    postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Movie>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Movie>> {
        return moviesRespostory.getMovies()
    }
}