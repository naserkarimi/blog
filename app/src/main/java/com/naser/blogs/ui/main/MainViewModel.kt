package com.naser.blogs.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naser.blogs.core.api.ApiHelperImpl
import com.naser.blogs.core.api.RetrofitBuilder
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.core.utils.Resource
import com.naser.blogs.core.utils.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val sliders = MutableLiveData<Resource<List<Slider>>>()
    private val blogsByPopular = MutableLiveData<Resource<List<Blog>>>()
    private val blogsByLatest = MutableLiveData<Resource<List<Blog>>>()
    private val apiHelper = ApiHelperImpl(RetrofitBuilder.apiService)
    init {
        fetchSliders()
        fetchBlogsByLatest()
        fetchBlogsByPopular()
    }

    private fun fetchSliders() {
        viewModelScope.launch {
            sliders.postValue(Resource.loading(null))
            apiHelper.getSliders()
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    sliders.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    sliders.postValue(Resource.success(it))
                }
        }
    }

    private fun fetchBlogsByPopular() {
        viewModelScope.launch {
            blogsByPopular.postValue(Resource.loading(null))
            apiHelper.getBlogs(SortType.Popular)
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    blogsByPopular.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    blogsByPopular.postValue(Resource.success(it))
                }
        }
    }

    private fun fetchBlogsByLatest() {
        viewModelScope.launch {
            blogsByLatest.postValue(Resource.loading(null))
            apiHelper.getBlogs(SortType.Latest)
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    blogsByLatest.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    blogsByLatest.postValue(Resource.success(it))
                }
        }
    }

    fun getSliders(): LiveData<Resource<List<Slider>>> {
        return sliders
    }

    fun getBlogsByPopular(): LiveData<Resource<List<Blog>>> {
        return blogsByPopular
    }

    fun getBlogsByLatest(): LiveData<Resource<List<Blog>>> {
        return blogsByLatest
    }
}