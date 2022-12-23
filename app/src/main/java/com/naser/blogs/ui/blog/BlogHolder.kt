package com.naser.blogs.ui.blog

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.utils.Time
import com.naser.blogs.databinding.BlogItemBinding
import com.naser.blogs.ui.main.OnItemClickListener

class BlogHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = BlogItemBinding.bind(itemView)

    fun bind(context: Context, blogItem: Blog, listener: OnItemClickListener) {
        Glide.with(context)
            .load(blogItem.path)
            .into(binding.blogImages)

        binding.blogTitle.text = blogItem.title

        binding.blogDate.text = Time.getTime(blogItem.date)

        binding.root.setOnClickListener {
            listener.onItemClick(blogItem)
        }
    }

}
