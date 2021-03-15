package com.example.myhillsblog.utils

import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.retrofit.BlogNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<BlogNetworkEntity,Blogs>
{
    override fun mapFromEntity(entity: BlogNetworkEntity): Blogs {
        return Blogs(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blogs): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(listEntity : List<BlogNetworkEntity>):List<Blogs>{
        return listEntity.map { mapFromEntity(it) }
    }
}