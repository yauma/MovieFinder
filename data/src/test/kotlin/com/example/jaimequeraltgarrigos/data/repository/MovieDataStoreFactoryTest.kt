package com.example.jaimequeraltgarrigos.data.repository

import com.example.jaimequeraltgarrigos.data.store.MoviesCacheDataStore
import com.example.jaimequeraltgarrigos.data.store.MoviesRemoteDataStore
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class MovieDataStoreFactoryTest {
    private lateinit var movieDataStoreFactory: MovieDataStoreFactory
    @Mock
    lateinit var movieCacheDataStore: MoviesCacheDataStore
    @Mock
    lateinit var moviesRemoteDataStore: MoviesRemoteDataStore

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieDataStoreFactory = MovieDataStoreFactory(movieCacheDataStore, moviesRemoteDataStore)
    }

    @Test
    fun remoteDataStoreShouldBeReturnWherCacheIsExpired() {
        assertEquals(moviesRemoteDataStore, movieDataStoreFactory.getDataStore(true, true))
    }

    @Test
    fun cacheDataStoreShouldBeReturnWherCacheIsNotExpiredAndProhjectIsCached() {
        assertEquals(movieCacheDataStore, movieDataStoreFactory.getDataStore(true, false))
    }

    @Test
    fun remoteDataStoreShouldBeReturnWherProjectIsNotCached() {
        assertEquals(moviesRemoteDataStore, movieDataStoreFactory.getDataStore(false, false))
    }

    @Test
    fun cacheDataStoreShouldBeReturnWherCallGetCacheStore() {
        assertEquals(movieCacheDataStore, movieDataStoreFactory.getCacheDataStore())
    }
}