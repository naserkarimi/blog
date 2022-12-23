package com.naser.blogs.ui.blogdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naser.blogs.core.api.ApiHelperImpl
import com.naser.blogs.core.api.RetrofitBuilder
import com.naser.blogs.core.api.entity.Blog
import com.naser.blogs.core.api.entity.BlogDetail
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.core.utils.Resource
import com.naser.blogs.core.utils.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class BlogDetailViewModel : ViewModel() {
    private val blogDetail = MutableLiveData<Resource<BlogDetail>>()
    private val apiHelper = ApiHelperImpl(RetrofitBuilder.apiService)

    fun fetchBlogDetail(blog: Blog) {
        viewModelScope.launch {
            blogDetail.postValue(Resource.loading(null))
            apiHelper.getBlogDetail(blog.id)
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    blogDetail.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    blogDetail.postValue(Resource.success(it))
                }
        }
    }


    fun getBlogDetail(): LiveData<Resource<BlogDetail>> {
        return blogDetail
    }

}