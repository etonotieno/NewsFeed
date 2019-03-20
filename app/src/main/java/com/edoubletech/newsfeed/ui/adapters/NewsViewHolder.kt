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
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.utils.getPrettifiedTimeString

class NewsViewHolder(newsItemView: View) : RecyclerView.ViewHolder(newsItemView) {

    fun bind(news: News, onItemClick: (news: News) -> Unit) {
        itemView.findViewById<TextView>(R.id.headline_text_view).text = news.title
        itemView.findViewById<TextView>(R.id.section_text_view).text = news.sectionName

        val date = news.publicationDate
        itemView.findViewById<TextView>(R.id.time_text_view).text = date.getPrettifiedTimeString()

        val imageUrl = news.imageUrl
        val articleImageView = itemView.findViewById<ImageView>(R.id.article_image_view)

        if (imageUrl == null) {
            articleImageView.visibility = View.GONE
        } else {
            Glide.with(itemView.context).load(imageUrl).into(articleImageView)
        }

        itemView.setOnClickListener {
            onItemClick(news)
        }
    }
}