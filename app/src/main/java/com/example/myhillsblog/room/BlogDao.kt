package com.example.myhillsblog.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity:BlogCacheEntity):Long

    @Query("Select * FROM blogs")
    suspend fun getBlogs():List<BlogCacheEntity>
}