package com.naser.blogs.core.api.entity

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("username")
    val username: String = "",
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("body")
    val body: String = "",
    @SerializedName("createdOn")
    val createdOn: String = ""
)
