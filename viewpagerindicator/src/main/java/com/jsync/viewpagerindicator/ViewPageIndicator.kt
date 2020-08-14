package com.jsync.viewpagerindicator

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

class ViewpagerIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedDrawable: Int
    private var unSelectedDrawable: Int
    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setActiveDot(position)
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.content_view_pager_indicator, this)
        setPadding(
            convertDpToPixel(2f).toInt(),
            0,
            convertDpToPixel(2f).toInt(),
            0
        )
        background = ContextCompat.getDrawable(context, R.drawable.bg_black_curved_12)
        orientation = HORIZONTAL

        selectedDrawable = R.drawable.ic_selected_dot
        unSelectedDrawable = R.drawable.ic_unselected_dot
    }

    fun attachViewPager(viewPager: ViewPager) {
        viewPager.adapter?.let {
            createDots(it.count)
            viewPager.removeOnPageChangeListener(pageChangeListener)
            viewPager.addOnPageChangeListener(pageChangeListener)
        }
    }

    private fun createDots(count: Int) {
        removeAllViews()
        if (count <= 0) {
            addView(createDot())
        } else {
            for (i in 0 until count) {
                addView(createDot())
            }
        }
        getChildAt(0).setBackgroundResource(selectedDrawable)
    }

    private fun createDot(): View {
        val dot = View(context)
        dot.setBackgroundResource(unSelectedDrawable)
        dot.layoutParams = getDotParams()
        return dot
    }

    private fun getDotParams(): LayoutParams {
        val diameter = convertDpToPixel(8f).toInt()
        val marginDps = convertDpToPixel(6f).toInt()

        val singleDotParams = LayoutParams(
            diameter,
            diameter
        )
        singleDotParams.setMargins(
            marginDps,
            marginDps,
            marginDps,
            marginDps
        )
        return singleDotParams
    }

    private fun setActiveDot(position: Int) {
        for (i in 0 until childCount) {
            getChildAt(i).setBackgroundResource(
                if (i == position) {
                    selectedDrawable
                } else {
                    unSelectedDrawable
                }
            )
        }
    }

    private fun convertDpToPixel(dp: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}