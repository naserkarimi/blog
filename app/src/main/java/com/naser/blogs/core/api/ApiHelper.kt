package com.naser.blogs.core.api

import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.BlogDetail
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.core.utils.SortType
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getSliders(): Flow<List<Slider>>

    fun getBlogs(sortType: SortType): Flow<List<Blog>>

    fun getBlogDetail(id: Int): Flow<BlogDetail>

}