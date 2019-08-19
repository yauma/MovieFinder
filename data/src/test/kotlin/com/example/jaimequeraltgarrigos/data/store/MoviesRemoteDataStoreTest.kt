package com.example.jaimequeraltgarrigos.data.store

import com.example.jaimequeraltgarrigos.data.factory.DataFactory
import com.example.jaimequeraltgarrigos.data.repository.MoviesRemote
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.lang.UnsupportedOperationException

class MoviesRemoteDataStoreTest {

    private val moviesRemote: MoviesRemote = mock()
    lateinit var moviesRemoteDataStore: MoviesRemoteDataStore

    @Before
    fun setUp() {
        moviesRemoteDataStore = MoviesRemoteDataStore(moviesRemote)
    }

    @Test
    fun getMoviesComplete() {
        whenever(moviesRemote.getMovies()).thenReturn(Observable.just(DataFactory.getMoviesLEntityist(1)))
        val testObserver = moviesRemoteDataStore.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnData() {
        val movieEntity = DataFactory.randomMovieMovieEntity()
        whenever(moviesRemote.getMovies()).thenReturn(Observable.just(listOf(movieEntity)))
        val testObserver = moviesRemoteDataStore.getMovies().test()
        testObserver.assertValue(listOf(movieEntity))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveMoviesShouldReturnException() {
        moviesRemoteDataStore.saveMovies(listOf(DataFactory.randomMovieMovieEntity()))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearMoviesShouldReturnException() {
        moviesRemoteDataStore.clearMovies()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedMoviesShouldReturnException() {
        moviesRemoteDataStore.getBookmarkedMovies()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setBookmarkedMovieShouldReturnException() {
        moviesRemoteDataStore.setBookmarkedMovie(DataFactory.randomUuid())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setUnBookmarkedMovieShouldReturnException() {
        moviesRemoteDataStore.setUnBookmarkedMovie(DataFactory.randomUuid())
    }
}