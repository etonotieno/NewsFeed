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
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.data.model.Category
import com.edoubletech.newsfeed.ui.activities.CategoryActivity
import com.edoubletech.newsfeed.ui.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

import java.util.ArrayList

/**
 * Created by EtonOtieno on 3/7/2018
 */

class CategoryFragment : Fragment(), CategoryAdapter.ListItemClickListener {

    private var mCategories = arrayListOf<Category>()
    private lateinit var mAdapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_category, container, false)

        mCategories.apply {
            add(Category(getString(R.string.arts_section), R.drawable.ic_arts))
            add(Category(getString(R.string.books_section), R.drawable.ic_books))
            add(Category(getString(R.string.business_section), R.drawable.ic_business))
            add(Category(getString(R.string.education_section), R.drawable.ic_education))
            add(Category(getString(R.string.fashion_section), R.drawable.ic_fashion))
            add(Category(getString(R.string.film_section), R.drawable.ic_film))
            add(Category(getString(R.string.health_section), R.drawable.ic_health))
            add(Category(getString(R.string.lifestyle_section), R.drawable.ic_lifestyle))
            add(Category(getString(R.string.media_section), R.drawable.ic_media))
            add(Category(getString(R.string.money_section), R.drawable.ic_money))
            add(Category(getString(R.string.music_section), R.drawable.ic_music))
            add(Category(getString(R.string.science_section), R.drawable.ic_science))
            add(Category(getString(R.string.sports_section), R.drawable.ic_sports))
            add(Category(getString(R.string.technology_section), R.drawable.ic_technology))
            add(Category(getString(R.string.travel_section), R.drawable.ic_travel))
            add(Category(getString(R.string.world_section), R.drawable.ic_world_news))
        }
        mAdapter.submitList(mCategories)
        mAdapter = CategoryAdapter(this)
        setUpRecyclerView()
        return rootView
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(activity,
                LinearLayoutManager.VERTICAL)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.spanCount = 3
        } else {
            layoutManager.spanCount = 2
        }

        category_recycler_view.apply {
            setLayoutManager(layoutManager)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    override fun onListItemClick(clickedItemIndex: Int) {
        val name = mCategories[clickedItemIndex].name
        val intent = Intent(activity, CategoryActivity::class.java)
        intent.putExtra(EXTRA_CATEGORY_NAME, name)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_CATEGORY_NAME = "com.edoubletech.newsfeed.EXTRA_CATEGORY_NAME"
    }
}
