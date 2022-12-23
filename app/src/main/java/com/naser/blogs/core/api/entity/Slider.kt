package com.naser.blogs.core.api.entity

import com.google.gson.annotations.SerializedName

data class Slider(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("path")
    val path: String = "",
    @SerializedName("title")
    val title: String = ""
)
