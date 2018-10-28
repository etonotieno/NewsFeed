/*
 *  Copyright (C) 2018 Eton Otieno Oboch
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
 *
 */

package com.edoubletech.newsfeed.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.NewsState
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter

class MainFragment : Fragment() {

    private val newsAdapter = NewsAdapter()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mError: TextView
    private lateinit var mLoadingIndicator: ProgressBar

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        mRecyclerView = rootView.findViewById(R.id.main_fragment_recycler_view)
        mError = rootView.findViewById(R.id.main_fragment_empty_view)
        mLoadingIndicator = rootView.findViewById(R.id.main_fragment_loading_indicator)

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.search("technology")

        viewModel.getNews().observe(this, Observer { state ->
            state?.let { handleState(state) }
        })

        return rootView
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
            newsAdapter.submitList(data)
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

}
