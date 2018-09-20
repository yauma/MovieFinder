package com.example.jaimequeraltgarrigos.domain.interactor.browse

import com.example.jaimequeraltgarrigos.domain.MovieDataFactory
import com.example.jaimequeraltgarrigos.domain.executor.PostExecutionThread
import com.example.jaimequeraltgarrigos.domain.model.Movie
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetMoviesTest {
    private lateinit var getMovies: GetMovies
    @Mock
    lateinit var moviesRepository: MoviesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getMovies = GetMovies(moviesRepository, postExecutionThread)
    }

    @Test
    fun getMoviesCompletes() {
        getObservableMovies(Observable.just(MovieDataFactory.getMoviesList(2)))
        val testObserver = getMovies.buildUseCaseObservable()
        testObserver.test().assertComplete()
    }

    @Test
    fun getMoviesReturnDate() {
        val projects = MovieDataFactory.getMoviesList(3)
        getObservableMovies(Observable.just(projects))
        val testObserver = getMovies.buildUseCaseObservable()
        testObserver.test().assertValue(projects)
    }

    fun getObservableMovies(observable: Observable<List<Movie>>) {
        whenever(moviesRepository.getMovies()).thenReturn(observable)
    }
}