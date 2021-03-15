package com.example.myhillsblog.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(version = 1, entities = [BlogCacheEntity::class])
abstract class BlogDatabase : RoomDatabase() {

    abstract fun getDao() :BlogDao

    companion object{
        val  DATABASE_NAME="blog_db"
    }

}