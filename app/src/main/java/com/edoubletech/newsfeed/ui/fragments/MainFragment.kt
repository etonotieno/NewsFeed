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

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.ui.MainViewModel
import com.edoubletech.newsfeed.ui.adapters.NewsAdapter

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.category_activity_recycler_view)

        val newsAdapter = NewsAdapter()
        recyclerView.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.search("technology")

        viewModel.newsList.observe(this, Observer {
            if (it != null) {
                newsAdapter.setNews(it)
            }
        })

        return rootView
    }
}
