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

package com.edoubletech.newsfeed.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.utils.getPrettifiedTimeString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by EtonOtieno on 2/15/2018
 */

class NewsAdapter(
        private val onItemClick: (news: News) -> Unit
) : ListAdapter<News, NewsViewHolder>(COMPARATOR) {

    companion object {
        val COMPARATOR: DiffUtil.ItemCallback<News> = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = getItem(position) as News
        holder.bind(currentNews, onItemClick)
    }

}

class NewsViewHolder(newsItemView: View) : RecyclerView.ViewHolder(newsItemView) {

    fun bind(news: News, onItemClick: (news: News) -> Unit) {
        itemView.headline_text_view.text = news.title
        itemView.trailtext_text_view.text = news.trailText
        itemView.section_text_view.text = news.sectionName

        val dateString = news.publicationDate
        itemView.time_text_view.text = dateString.getPrettifiedTimeString()

        val imageUrl = news.imageUrl
        if (imageUrl == null) {
            itemView.article_image_view.visibility = View.GONE
        } else {
            Picasso.get().load(imageUrl).into(itemView.article_image_view)
        }

        itemView.setOnClickListener {
            onItemClick(news)
        }
    }

}