/*
 *   Copyright (C) 2018 Eton Otieno Oboch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.edoubletech.newsfeed.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter
import com.edoubletech.newsfeed.ui.fragments.CategoryFragment

class CategoryActivity : AppCompatActivity(){

    private val mNewsAdapter: NewsAdapter = NewsAdapter()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyStateTextView: TextView
    private lateinit var mNoInternetImage: ImageView
    private lateinit var mLoadingIndicator: View
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        mEmptyStateTextView = findViewById(R.id.main_fragment_empty_view)
        mRecyclerView = findViewById(R.id.category_activity_recycler_view)
        mLoadingIndicator = findViewById(R.id.category_loading_indicator)
        mNoInternetImage = findViewById(R.id.no_internet_image_main_fragment)
        categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME)
        mLoadingIndicator.visibility = View.GONE
        mNoInternetImage.visibility = View.GONE
        mEmptyStateTextView.visibility = View.GONE
        mRecyclerView.visibility = View.VISIBLE

        supportActionBar?.setTitle(categoryName)

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoryActivity,
                    LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mNewsAdapter
        }

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.search(getSectionId(categoryName))
        viewModel.newsList.observe(this, Observer {
            if (it != null) {
                mNewsAdapter.setNews(it)
            }
        })
    }

    private fun getSectionId(categoryName: String?): String {
        val formattedSectionName = categoryName!!.replace("\\s".toRegex(), "")
                .toLowerCase()

        val categoryNames = arrayOf("health", "sports", "worldnews")
        val correctSectionIds = arrayOf("healthcare-network", "sport", "world")

        for (index in categoryNames.indices) {
            if (formattedSectionName.toLowerCase().contains(categoryNames[index])) {
                return correctSectionIds[index]
            }
        }

        return formattedSectionName
    }
}
