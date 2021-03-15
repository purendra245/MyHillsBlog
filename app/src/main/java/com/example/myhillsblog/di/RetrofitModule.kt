package com.example.myhillsblog.di

import android.net.Network
import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.retrofit.BlogNetworkEntity
import com.example.myhillsblog.retrofit.BlogRetrofit
import com.example.myhillsblog.utils.EntityMapper
import com.example.myhillsblog.utils.NetworkMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun getBaseUrl(): String{
        return "https://open-api.xyz/placeholder/"
    }

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<BlogNetworkEntity,Blogs>{
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson{
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,baseUlr: String): Retrofit.Builder{
        return Retrofit.Builder().baseUrl(baseUlr)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogServicet(retrofit: Retrofit.Builder): BlogRetrofit{
        return retrofit.build().create(BlogRetrofit::class.java)
    }
}