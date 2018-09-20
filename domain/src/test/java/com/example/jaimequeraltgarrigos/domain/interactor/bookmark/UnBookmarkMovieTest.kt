package com.example.jaimequeraltgarrigos.domain.interactor.bookmark

import com.example.jaimequeraltgarrigos.domain.MovieDataFactory
import com.example.jaimequeraltgarrigos.domain.executor.PostExecutionThread
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkMovieTest{
    private lateinit var unbookmarkMovie: UnBookmarkMovie
    @Mock
    lateinit var moviesRepository: MoviesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkMovie = UnBookmarkMovie(moviesRepository, postExecutionThread)
    }

    @Test
    fun getMoviesCompletes() {
        val params = UnBookmarkMovie.Params.forMovie(MovieDataFactory.randomUuid())
        getCompletableMovies(Completable.complete(), params.id)
        val testObserver = unbookmarkMovie.buildUseCaseCompletable(params)
        testObserver.test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkNullParamThrowException() {
        unbookmarkMovie.buildUseCaseCompletable()
    }

    fun getCompletableMovies(completable: Completable, id: String) {
        whenever(moviesRepository.unbookmarkMovie(id)).thenReturn(completable)
    }
}