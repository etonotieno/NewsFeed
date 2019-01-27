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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.NewsState
import com.edoubletech.newsfeed.ui.activities.DetailActivity
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : Fragment() {

    private val newsAdapter = NewsAdapter {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        main_fragment_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        val viewModel = getViewModel<MainViewModel>()

        viewModel.startDataLoad("technology")
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
        main_fragment_loading_indicator.visibility = View.GONE
        main_fragment_recycler_view.visibility = View.GONE
        main_fragment_empty_view.visibility = View.VISIBLE
        errorMessage?.let { main_fragment_empty_view.text = it }
    }

    private fun setUpScreenForSuccess(data: List<News>?) {
        // Hide the Error View and the Progress View
        main_fragment_empty_view.visibility = View.GONE
        main_fragment_loading_indicator.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            newsAdapter.submitList(data)
            // Show the RecyclerView
            main_fragment_recycler_view.visibility = View.VISIBLE
        } else {
            // Show the Empty View
            main_fragment_empty_view.visibility = View.VISIBLE
            main_fragment_empty_view.text = "No Data was found 😑😑"
        }
    }

    private fun setUpScreenForLoadingState() {
        // Show the Progress View and hide the RecyclerView, EmptyView and LoadingView
        main_fragment_loading_indicator.visibility = View.VISIBLE
        main_fragment_recycler_view.visibility = View.GONE
        main_fragment_empty_view.visibility = View.GONE
    }

}
