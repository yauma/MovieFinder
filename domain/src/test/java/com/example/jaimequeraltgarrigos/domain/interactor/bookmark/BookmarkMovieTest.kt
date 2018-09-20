package com.example.jaimequeraltgarrigos.domain.interactor.bookmark

import com.example.jaimequeraltgarrigos.domain.MovieDataFactory
import com.example.jaimequeraltgarrigos.domain.executor.PostExecutionThread
import com.example.jaimequeraltgarrigos.domain.repository.MoviesRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkMovieTest {
    private lateinit var bookmarkMovie: BookmarkMovie
    @Mock
    lateinit var moviesRepository: MoviesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkMovie = BookmarkMovie(moviesRepository, postExecutionThread)
    }

    @Test
    fun getMoviesCompletes() {
        val params = BookmarkMovie.Params.forMovie(MovieDataFactory.randomUuid())
        getCompletableMovies(Completable.complete(), params.id)
        val testObserver = bookmarkMovie.buildUseCaseCompletable(params)
        testObserver.test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkNullParamThrowException() {
        bookmarkMovie.buildUseCaseCompletable()
    }

    fun getCompletableMovies(completable: Completable, id: String) {
        whenever(moviesRepository.bookmarkMovie(id)).thenReturn(completable)
    }
}