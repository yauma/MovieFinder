package com.example.jaimequeraltgarrigos.data

import com.example.jaimequeraltgarrigos.data.factory.DataFactory
import com.example.jaimequeraltgarrigos.data.mappper.MovieMapper
import com.example.jaimequeraltgarrigos.data.model.MovieEntity
import com.example.jaimequeraltgarrigos.data.repository.MovieDataStoreFactory
import com.example.jaimequeraltgarrigos.data.repository.MoviesCache
import com.example.jaimequeraltgarrigos.data.store.MoviesDataStore
import com.example.jaimequeraltgarrigos.domain.model.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MoviesDataRepositoryTest {

    val mapper: MovieMapper = mock()
    val moviesCache: MoviesCache = mock()
    val factory: MovieDataStoreFactory = mock()
    val store: MoviesDataStore = mock()
    lateinit var repository: MoviesDataRepository

    @Before
    fun setUp() {
        stubFactoryDataStore()
        stubCacheDataStore()
        stubIsCacheExpire(false)
        stubIsAreMoviesCached(true)
        stubDataStoreSaveMovie()
        repository = MoviesDataRepository(mapper, moviesCache, factory)
    }

    @Test
    fun getMovies() {
        stubGetMovies(Observable.just(DataFactory.getMoviesLEntityist(4)))
        val testObserver = repository.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val movieEntity = DataFactory.randomMovieMovieEntity()
        val movie = DataFactory.randomMovie()
        stubMapper(movie, movieEntity)
        stubGetMovies(Observable.just(listOf(movieEntity)))
        val testObserver = repository.getMovies().test()
        testObserver.assertValue(listOf(movie))
    }

    @Test
    fun setUnBookmarkedMovie() {
        stubSetUnBookmarkedMovie(any())
        val testObserver = repository.unbookmarkMovie(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookMarkedMovies() {
        val movieEntity = DataFactory.randomMovieMovieEntity()
        val movie = DataFactory.randomMovie()
        stubMapper(movie, movieEntity)
        stubGetBookmarkedMovie(listOf(movieEntity))
        val testObserver = repository.getBookMarkedMovies().test()
        testObserver.assertValue(listOf(movie))
    }

    @Test
    fun setBookMarkMovie() {
        stubSetBookmarkedMovie(any())
        val testObserver = repository.bookmarkMovie(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    private fun stubSetBookmarkedMovie(id: String) {
        whenever(store.setBookmarkedMovie(id)).thenReturn(Completable.complete())
    }

    private fun stubGetBookmarkedMovie(movies: List<MovieEntity>) {
        whenever(store.getBookmarkedMovies()).thenReturn(Observable.just(movies))
    }

    private fun stubSetUnBookmarkedMovie(movieId: String) {
        whenever(store.setUnBookmarkedMovie(movieId)).thenReturn(Completable.complete())
    }

    private fun stubGetMovies(moviesEntity: Observable<List<MovieEntity>>) {
        whenever(store.getMovies()).thenReturn(moviesEntity)
    }

    private fun stubDataStoreSaveMovie() {
        whenever(store.saveMovies(any())).thenReturn(Completable.complete())
    }

    private fun stubMapper(movie: Movie, entity: MovieEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(movie)
    }

    private fun stubIsAreMoviesCached(areMoviesCached: Boolean) {
        whenever(moviesCache.areMoviesCached()).thenReturn(Single.just(areMoviesCached))
    }

    private fun stubIsCacheExpire(isCacheExpired: Boolean) {
        whenever(moviesCache.isCacheExpired()).thenReturn(Single.just(isCacheExpired))
    }

    private fun stubFactoryDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    private fun stubCacheDataStore() {
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }
}