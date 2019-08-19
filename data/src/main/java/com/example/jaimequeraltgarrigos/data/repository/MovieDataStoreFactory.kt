package com.example.jaimequeraltgarrigos.data.repository

import com.example.jaimequeraltgarrigos.data.store.MoviesCacheDataStore
import com.example.jaimequeraltgarrigos.data.store.MoviesDataStore
import com.example.jaimequeraltgarrigos.data.store.MoviesRemoteDataStore
import javax.inject.Inject

open class MovieDataStoreFactory @Inject constructor(val movieCacheDataStore: MoviesCacheDataStore,
                                                val moviesRemoteDataStore: MoviesRemoteDataStore) {

    open fun getDataStore(projectCached: Boolean, isCachedExpired: Boolean): MoviesDataStore {
        if (projectCached && !isCachedExpired) {
            return movieCacheDataStore
        } else {
            return moviesRemoteDataStore
        }
    }

    open fun getCacheDataStore(): MoviesDataStore {
        return movieCacheDataStore
    }
}