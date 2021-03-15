package com.example.myhillsblog.di

import com.example.myhillsblog.repository.MainRepository
import com.example.myhillsblog.retrofit.BlogRetrofit
import com.example.myhillsblog.room.BlogDao
import com.example.myhillsblog.room.CacheMapper
import com.example.myhillsblog.utils.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(  blogRetrofit: BlogRetrofit,
                                blogDao: BlogDao,
                                networkMapper: NetworkMapper,
                                cacheMapper: CacheMapper
    ):MainRepository {
       return MainRepository(blogRetrofit,blogDao,networkMapper,cacheMapper)
    }
}