package com.naser.blogs.core.api.entity

import com.google.gson.annotations.SerializedName

data class BlogDetail(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("comments")
    val comments: List<Comment> = listOf(),
    @SerializedName("author")
    val author: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("path")
    val path: String = "",
    @SerializedName("body")
    val body: String = ""
)
