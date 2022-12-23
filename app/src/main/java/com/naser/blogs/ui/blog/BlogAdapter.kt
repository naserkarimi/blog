package com.naser.blogs.ui.blog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naser.blogs.R
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.ui.main.OnItemClickListener

class BlogAdapter(private val context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<BlogHolder>() {

    var blogList: List<Blog>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        return BlogHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.blog_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(
            context,
            blogList!![position],
            listener
        )
    }

    override fun getItemCount(): Int {
        return blogList!!.size
    }

}