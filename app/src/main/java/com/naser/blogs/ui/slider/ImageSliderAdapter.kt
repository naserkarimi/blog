package com.naser.blogs.ui.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.naser.blogs.R
import com.naser.blogs.core.api.entity.Slider
import com.naser.blogs.databinding.ImageSliderItemBinding

class ImageSlideAdapter(private val context: Context, private var sliderList: ArrayList<Slider>) : PagerAdapter() {
    override fun getCount(): Int {
        return sliderList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =  (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.image_slider_item, null)
        val binding = ImageSliderItemBinding.bind(view)

        sliderList[position].let {
            Glide.with(context)
                .load(it.path)
                .into(binding.sliderImages)

            binding.sliderTitle.text = it.title
        }

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}