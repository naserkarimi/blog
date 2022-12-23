package com.naser.blogs.ui.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naser.blogs.R
import com.naser.blogs.core.api.entity.Comment

class CommentAdapter(private val context: Context) : RecyclerView.Adapter<CommentHolder>() {

    var commentsList: List<Comment> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.comment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(
            context,
            commentsList[position]
        )
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

}