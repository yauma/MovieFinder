package com.example.jaimequeraltgarrigos.data.store

import com.example.jaimequeraltgarrigos.data.factory.DataFactory
import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import com.example.jaimequeraltgarrigos.data.repository.MoviesCache
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before

import org.junit.Test

class MoviesCacheDataStoreTest {

    val moviesCache: MoviesCache = mock<MoviesCache>()
    lateinit var movieCacheDataStore: MoviesCacheDataStore

    @Before
    fun setUp() {
        movieCacheDataStore = MoviesCacheDataStore(moviesCache)
    }

    @Test
    fun getMoviesComplete() {
        stubGetMovies(Observable.just(listOf(DataFactory.randomMovieMovieEntity())))
        var testObserver = movieCacheDataStore.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val movieEntity = DataFactory.randomMovieMovieEntity()
        stubGetMovies(Observable.just(listOf(movieEntity)))
        val testObserver = movieCacheDataStore.getMovies().test()
        testObserver.assertValue(listOf(movieEntity))
    }

    @Test
    fun saveMoviesComplete() {
        whenever(moviesCache.saveMovies(any())).thenReturn(Completable.complete())
        whenever(moviesCache.setLastCachedTime(any())).thenReturn(Completable.complete())
        var testObserver = movieCacheDataStore.saveMovies(DataFactory.getMoviesLEntityist(1)).test()
        testObserver.assertComplete()
    }

    @Test
    fun clearMoviesComplete() {
        whenever(moviesCache.clearMovies()).thenReturn(Completable.complete())
        var testObserver = movieCacheDataStore.clearMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedMoviesReturnData() {
        val movieEntity = DataFactory.randomMovieMovieEntity()
        whenever(moviesCache.getBookmarkedMovies()).thenReturn(Observable.just(listOf(movieEntity)))
        val testObserver = movieCacheDataStore.getBookmarkedMovies().test()
        testObserver.assertValue(listOf(movieEntity))
    }

    @Test
    fun setBookmarkedMoviesComplete() {
        whenever(moviesCache.setBookmarkedMovie(any())).thenReturn(Completable.complete())
        var testObserver = movieCacheDataStore.setBookmarkedMovie(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setUnBookmarkedMovieComplete() {
        whenever(moviesCache.setUnBookmarkedMovie(any())).thenReturn(Completable.complete())
        var testObserver = movieCacheDataStore.setUnBookmarkedMovie(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    private fun stubGetMovies(movies: Observable<List<MovieEntity>>) {
        whenever(moviesCache.getMovies()).thenReturn(movies)
    }
}