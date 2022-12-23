package com.naser.blogs.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naser.blogs.R
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.core.utils.Status
import com.naser.blogs.databinding.FragmentMainBinding
import com.naser.blogs.ui.blog.BlogAdapter
import com.naser.blogs.ui.blogdetail.BlogDetailFragment
import com.naser.blogs.ui.slider.ImageSlideAdapter


class MainFragment : Fragment() , OnItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewPagerAdapter: ImageSlideAdapter
    private lateinit var popularAdapter: BlogAdapter
    private lateinit var latestAdapter: BlogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setupObserver()
        bindLatestList()
        bindPopularList()
    }

    private fun bindLatestList() = with(binding.latestBlogList) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager)
        latestAdapter = BlogAdapter(requireContext(), this@MainFragment)
        binding.latestBlogList.adapter = latestAdapter
    }

    private fun bindPopularList() = with(binding.popularBlogList) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager)
        popularAdapter = BlogAdapter(requireContext(), this@MainFragment)
        binding.popularBlogList.adapter = popularAdapter
    }

    private fun setupObserver() {
        viewModel.getSliders().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { slider -> renderSliderList(slider) }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getBlogsByPopular().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { blogs -> renderPopularList(blogs) }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getBlogsByLatest().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { blogs -> renderLatestList(blogs) }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun renderSliderList(sliders: List<Slider>) {
        viewPagerAdapter = ImageSlideAdapter(requireContext(), ArrayList(sliders))
        binding.viewpager.adapter = viewPagerAdapter
        binding.indicator.setViewPager(binding.viewpager)
    }

    private fun renderPopularList(blogs: List<Blog>) {
        popularAdapter.blogList = ArrayList(blogs)
        popularAdapter.notifyDataSetChanged()
    }

    private fun renderLatestList(blogs: List<Blog>) {
        latestAdapter.blogList = ArrayList(blogs)
        latestAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(item: Blog) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, BlogDetailFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

}