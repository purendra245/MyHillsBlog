package com.example.myhillsblog.room

import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<BlogCacheEntity,Blogs>{

    override fun mapFromEntity(entity: BlogCacheEntity): Blogs {
        return Blogs(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blogs): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blogs>{
        return entities.map { mapFromEntity(it) }
    }

}