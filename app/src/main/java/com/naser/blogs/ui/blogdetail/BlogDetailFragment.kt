package com.naser.blogs.ui.blogdetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.naser.blogs.R
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.BlogDetail
import com.naser.blogs.core.utils.Status
import com.naser.blogs.core.utils.Time
import com.naser.blogs.databinding.FragmentBlogDetailBinding
import com.naser.blogs.ui.blog.BlogAdapter
import com.naser.blogs.ui.comment.CommentAdapter


class BlogDetailFragment(val blog: Blog) : Fragment() {

    companion object {
        fun newInstance(blog: Blog) = BlogDetailFragment(blog)
    }

    private lateinit var binding: FragmentBlogDetailBinding
    private lateinit var viewModel: BlogDetailViewModel
    private lateinit var commentsAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentBlogDetailBinding.bind(inflater.inflate(R.layout.fragment_blog_detail, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setupObserver()
        bindBlog()
        bindCommentsList()
    }

    private fun clearAll() {
        binding.loading.visibility = View.VISIBLE
        binding.blogBody.text = ""
        binding.blogAuthor.text = ""
        binding.blogDate.text = ""
        binding.blogTitle.text = ""
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(requireActivity())[BlogDetailViewModel::class.java]
        viewModel.fetchBlogDetail(blog)
    }

    private fun bindBlog() {
        Glide.with(requireContext())
            .load(blog.path)
            .into(binding.blogImage)

        binding.blogTitle.text = blog.title

        binding.blogDate.text = Time.getTime(blog.date)
    }

    private fun bindCommentsList() = with(binding.commentsList) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager)
        commentsAdapter = CommentAdapter(requireContext())
        binding.commentsList.adapter = commentsAdapter
    }

    private fun setupObserver() {

        viewModel.getBlogDetail().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.loading.visibility = View.GONE
                    it.data?.let { blogDetail -> renderBlogDetail(blogDetail) }
                }
                Status.LOADING -> {
                    clearAll()
                }
                Status.ERROR -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun renderBlogDetail(blogDetail: BlogDetail) {
        bindDetail(blogDetail)
        bindAuthor(blogDetail)
        setCommentsData(blogDetail)
    }

    private fun setCommentsData(blogDetail: BlogDetail) {
        commentsAdapter.commentsList = blogDetail.comments
        commentsAdapter.notifyDataSetChanged()
    }

    private fun bindAuthor(blogDetail: BlogDetail) = with(binding.blogAuthor){
        text = blogDetail.author
    }

    private fun bindDetail(blogDetail: BlogDetail) = with(binding.blogBody) {
        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(blogDetail.body, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(blogDetail.body)
        }
    }

}