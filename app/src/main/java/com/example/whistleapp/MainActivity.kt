package com.example.whistleapp


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.whistleapp.databinding.ActivityMainBinding
import com.example.whistleapp2.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : FragmentActivity() {

    private lateinit var bnd: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bnd.root)

        setupViews()
    }


    private fun setupViews(){
        val adapter = SectionsPagerAdapter(this)
        bnd.viewPager.adapter = adapter

        TabLayoutMediator(bnd.tabs, bnd.viewPager){ tab, position ->
            tab.text = adapter.sectionsArray[position]
        }.attach()

    }




}