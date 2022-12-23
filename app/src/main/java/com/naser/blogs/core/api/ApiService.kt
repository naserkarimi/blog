package com.naser.blogs.core.api

import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.BlogDetail
import com.naser.blogs.core.api.entity.Slider
import retrofit2.http.*

interface ApiService {

    @GET("sliders")
    suspend fun getSliders(): List<Slider>

    @GET("blogs")
    suspend fun getBlogs(@Query("sortType") sortType: String): List<Blog>

    @GET("blogs/{id}")
    suspend fun getBlogDetail(@Path(value = "id", encoded = true) id: Int): BlogDetail

}