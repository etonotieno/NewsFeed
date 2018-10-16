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

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.data.model.News
import com.edoubletech.newsfeed.utils.getPrettifiedTimeString
import com.squareup.picasso.Picasso

/**
 * Created by EtonOtieno on 2/15/2018
 */

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    private var mNewsList: List<News> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = mNewsList[position]
        holder.mHeadlineTextView.text = currentNews.title

        holder.mTrailTextTextView.text = currentNews.trailText

        holder.mSectionTextView.text = currentNews.sectionName

        val dateString = currentNews.publicationDate
        holder.mPublicationTime.text = dateString.getPrettifiedTimeString()

        val imageUrl = currentNews.imageUrl
        if (imageUrl == null) {
            holder.mArticleImageView.visibility = View.GONE
        } else {
            Picasso.get().load(imageUrl).into(holder.mArticleImageView)
        }
        holder.itemView.tag = currentNews
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    fun setNews(newListOfNews: List<News>) {
        mNewsList = newListOfNews
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mTrailTextTextView: TextView = itemView.findViewById(R.id.trailtext_text_view)
    val mHeadlineTextView: TextView = itemView.findViewById(R.id.headline_text_view)
    val mSectionTextView: TextView = itemView.findViewById(R.id.section_text_view)
    val mPublicationTime: TextView = itemView.findViewById(R.id.time_text_view)
    val mArticleImageView: ImageView = itemView.findViewById(R.id.article_image_view)
}
