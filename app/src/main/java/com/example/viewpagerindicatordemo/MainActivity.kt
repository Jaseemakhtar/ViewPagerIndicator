package com.example.viewpagerindicatordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyFragmentPagerAdapter(supportFragmentManager)

        val firstFragment = MyFragment.newInstance("First Fragment")
        val secondFragment = MyFragment.newInstance("Second Fragment")
        val thirdFragment = MyFragment.newInstance("Third Fragment")

        adapter.addFragment(firstFragment, "ONE")
        adapter.addFragment(secondFragment, "TWO")
        adapter.addFragment(thirdFragment, "THREE")

        viewPager.adapter = adapter

        vpIndicator.attachViewPager(viewPager)
    }
}