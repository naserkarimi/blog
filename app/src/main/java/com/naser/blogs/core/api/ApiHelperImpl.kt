package com.naser.blogs.core.api

import com.naser.blogs.core.utils.SortType
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override fun getSliders() = flow { emit(apiService.getSliders()) }
    override fun getBlogs(sortType: SortType) = flow { emit(apiService.getBlogs(sortType.name)) }
    override fun getBlogDetail(id: Int) = flow { emit(apiService.getBlogDetail(id)) }
}