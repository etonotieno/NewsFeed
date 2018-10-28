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

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.NewsState
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter
import com.edoubletech.newsfeed.ui.fragments.CategoryFragment

class CategoryActivity : AppCompatActivity() {

    private val mNewsAdapter: NewsAdapter = NewsAdapter()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mError: TextView
    private lateinit var mLoadingIndicator: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        mError = findViewById(R.id.category_activity_empty_view)
        mRecyclerView = findViewById(R.id.category_activity_recycler_view)
        mLoadingIndicator = findViewById(R.id.category_loading_indicator)
        val categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME)

        supportActionBar?.title = categoryName

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoryActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mNewsAdapter
        }

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.search(getSectionId(categoryName))

        viewModel.getNews().observe(this, Observer { state ->
            state?.let { handleState(state) }

        })
    }

    private fun handleState(newsState: NewsState) {
        when (newsState) {
            is NewsState.Loading -> setUpScreenForLoadingState()
            is NewsState.Success -> setUpScreenForSuccess(newsState.data)
            is NewsState.Error -> setUpScreenForError(newsState.errorMessage)
        }
    }

    private fun setUpScreenForError(errorMessage: String?) {
        // Show the Error View and Hide the loading, Empty and Recycler Views
        mLoadingIndicator.visibility = View.GONE
        mRecyclerView.visibility = View.GONE
        mError.visibility = View.VISIBLE
        errorMessage?.let { mError.text = it }
    }

    private fun setUpScreenForSuccess(data: List<News>?) {
        // Hide the Error View and the Progress View
        mError.visibility = View.GONE
        mLoadingIndicator.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            mNewsAdapter.submitList(data)
            // Show the RecyclerView
            mRecyclerView.visibility = View.VISIBLE
        } else {
            // Show the Empty View
            mError.visibility = View.VISIBLE
            mError.text = "No Data was found 😑😑"
        }
    }

    private fun setUpScreenForLoadingState() {
        // Show the Progress View and hide the RecyclerView, EmptyView and LoadingView
        mLoadingIndicator.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
        mError.visibility = View.GONE
    }

    private fun getSectionId(categoryName: String): String {
        val formattedSectionName = categoryName.replace("\\s".toRegex(), "").toLowerCase()

        val categoryNames = arrayOf("health", "sports", "worldnews")
        val correctSectionIds = arrayOf("healthcare-network", "sport", "world")

        categoryNames.forEachIndexed { index, name ->
            if (formattedSectionName.contains(name)) return correctSectionIds[index]
        }

        return formattedSectionName
    }
}
