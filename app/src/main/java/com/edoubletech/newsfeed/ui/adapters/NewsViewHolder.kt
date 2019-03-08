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

package com.edoubletech.newsfeed.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.utils.getPrettifiedTimeString
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(newsItemView: View) : RecyclerView.ViewHolder(newsItemView) {

    fun bind(news: News, onItemClick: (news: News) -> Unit) {
        itemView.headline_text_view.text = news.title
        itemView.section_text_view.text = news.sectionName

        val dateString = news.publicationDate
        itemView.time_text_view.text = dateString.getPrettifiedTimeString()

        val imageUrl = news.imageUrl
        if (imageUrl == null) {
            itemView.article_image_view.visibility = View.GONE
        } else {
            Glide.with(itemView.context).load(imageUrl).into(itemView.article_image_view)
        }

        itemView.setOnClickListener {
            onItemClick(news)
        }
    }

}