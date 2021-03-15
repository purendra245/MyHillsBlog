package com.example.myhillsblog.repository

import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.retrofit.BlogRetrofit
import com.example.myhillsblog.room.BlogDao
import com.example.myhillsblog.room.CacheMapper
import com.example.myhillsblog.utils.DataState
import com.example.myhillsblog.utils.NetworkMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(    private val blogRetrofit: BlogRetrofit,
                private val blogDao: BlogDao,
                private val networkMapper: NetworkMapper,
                private val cacheMapper: CacheMapper
            )
{

    suspend fun getBlog(): Flow<DataState<List<Blogs>>> =  flow {
        emit(DataState.Loading)
        delay(1000)
        try {
                val networkBlogs = blogRetrofit.getBlogs()
                val blogs = networkMapper.mapFromEntityList(networkBlogs)
                for (blog in blogs){
                    blogDao.insert(cacheMapper.mapToEntity(blog))
                }
            val cachedBlogs = blogDao.getBlogs()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }
}