package com.naser.blogs.ui.comment

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naser.blogs.core.api.entity.Comment
import com.naser.blogs.databinding.CommentItemBinding

class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = CommentItemBinding.bind(itemView)

    fun bind(context: Context, commentItem: Comment) {
        Glide.with(context)
            .load(commentItem.avatar)
            .into(binding.commentImages)

        binding.commentUsername.text = commentItem.username

        binding.commentBody.text = commentItem.body
    }

}
