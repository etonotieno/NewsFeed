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

package io.devbits.newsfeed.home

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.utils.getFormattedTimeString
import kotlinx.android.synthetic.main.activity_detail.view.time_text_view
import kotlinx.android.synthetic.main.news_item.view.article_image_view
import kotlinx.android.synthetic.main.news_item.view.headline_text_view
import kotlinx.android.synthetic.main.news_item.view.section_text_view

class NewsViewHolder(newsItemView: View) : RecyclerView.ViewHolder(newsItemView) {

    fun bind(news: News) {
        itemView.headline_text_view.text = news.title
        itemView.section_text_view.text = news.sectionName

        val date = news.publicationDate
        itemView.time_text_view.text = date.getFormattedTimeString()

        val imageUrl = news.imageUrl
        val articleImageView = itemView.article_image_view

        Glide.with(itemView.context).load(imageUrl).into(articleImageView)

        itemView.setOnClickListener {
            val navController = it.findNavController()
            val directions = HomeFragmentDirections.actionHomeToDetail()
            navController.navigate(directions)
        }
    }
}