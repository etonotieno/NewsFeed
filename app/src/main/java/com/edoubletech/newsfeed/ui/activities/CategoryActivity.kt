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
import android.view.View
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.data.model.News
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.NewsState
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter
import com.edoubletech.newsfeed.ui.fragments.CategoryFragment
import kotlinx.android.synthetic.main.activity_category.category_activity_empty_view as emptyView
import kotlinx.android.synthetic.main.activity_category.category_activity_recycler_view as recyclerView
import kotlinx.android.synthetic.main.activity_category.category_loading_indicator as progressBar

class CategoryActivity : AppCompatActivity() {

    private val mNewsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME)

        supportActionBar?.title = categoryName

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoryActivity,
                    LinearLayoutManager.VERTICAL, false)
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
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        errorMessage?.let { emptyView.text = it }
    }

    private fun setUpScreenForSuccess(data: List<News>?) {
        // Hide the Error View and the Progress View
        emptyView.visibility = View.GONE
        progressBar.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            mNewsAdapter.submitList(data)
            // Show the RecyclerView
            recyclerView.visibility = View.VISIBLE
        } else {
            // Show the Empty View
            emptyView.visibility = View.VISIBLE
            emptyView.text = "No Data was found 😑😑"
        }
    }

    private fun setUpScreenForLoadingState() {
        // Show the Progress View and hide the RecyclerView, EmptyView and LoadingView
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        emptyView.visibility = View.GONE
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
