/*
 *  Copyright (C) 2019 Eton Otieno
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.devbits.newsfeed.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.devbits.newsfeed.R
import io.devbits.newsfeed.data.model.News
import io.devbits.newsfeed.ui.MainViewModel
import io.devbits.newsfeed.ui.activities.DetailActivity
import io.devbits.newsfeed.ui.adapters.NewsAdapter
import io.devbits.newsfeed.ui.state.NewsState
import io.devbits.newsfeed.utils.bindView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment() {

    private val newsAdapter = NewsAdapter {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        startActivity(intent)
    }

    private val homeNewsRecyclerView by bindView<RecyclerView>(R.id.news_list_recycler_view)
    private val homeEmptyView by bindView<TextView>(R.id.news_empty_view)
    private val homeLoadingIndicator by bindView<ProgressBar>(R.id.news_loading_indicator)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeNewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        val viewModel = getViewModel<MainViewModel>()

        viewModel.setCategory("technology")
        viewModel.fetchNews()
        viewModel.newsLiveData.observe(this, Observer { state ->
            state?.let { handleState(state) }
        })
    }

    private fun handleState(newsState: NewsState) {
        when (newsState) {
            is NewsState.Loading -> setUpScreenForLoadingState()
            is NewsState.Success -> setUpScreenForSuccess(newsState.data)
            is NewsState.Error -> setUpScreenForError(newsState.error)
        }
    }

    private fun setUpScreenForError(errorMessage: String?) {
        // Show the Error View and Hide the loading, Empty and Recycler Views
        homeLoadingIndicator.visibility = View.GONE
        homeNewsRecyclerView.visibility = View.GONE
        homeEmptyView.visibility = View.VISIBLE
        errorMessage?.let { homeEmptyView.text = it }
    }

    private fun setUpScreenForSuccess(data: List<News>?) {
        // Hide the Error View and the Progress View
        homeEmptyView.visibility = View.GONE
        homeLoadingIndicator.visibility = View.GONE

        if (data.isNullOrEmpty()) {
            // Show the Empty View
            homeEmptyView.visibility = View.VISIBLE
            homeEmptyView.text = "No Data was found 😑😑"
        } else {
            newsAdapter.submitList(data)
            // Show the RecyclerView
            homeNewsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setUpScreenForLoadingState() {
        // Show the Progress View and hide the RecyclerView, EmptyView and LoadingView
        homeLoadingIndicator.visibility = View.VISIBLE
        homeNewsRecyclerView.visibility = View.GONE
        homeEmptyView.visibility = View.GONE
    }
}
