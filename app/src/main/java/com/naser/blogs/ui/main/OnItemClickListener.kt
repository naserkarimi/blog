package com.naser.blogs.ui.main

import com.naser.blogs.core.api.entity.Blog

interface OnItemClickListener {
    fun onItemClick(item: Blog)
}