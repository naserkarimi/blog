package com.naser.blogs.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.naser.blogs.core.api.ApiHelper
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.core.utils.Resource
import com.naser.blogs.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiSliderObserver: Observer<Resource<List<Slider>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(flowOf(emptyList<Slider>()))
                .`when`(apiHelper)
                .getSliders()
            val viewModel = MainViewModel()
            viewModel.getSliders().observeForever(apiSliderObserver)
            verify(apiHelper).getSliders()
            verify(apiSliderObserver).onChanged(Resource.success(emptyList()))
            viewModel.getSliders().removeObserver(apiSliderObserver)
        }
    }
}