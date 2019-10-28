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

package io.devbits.newsfeed.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import io.devbits.newsfeed.R
import io.devbits.newsfeed.data.News
import kotlinx.android.synthetic.main.headlines_news_item.view.headlineNewsImageView
import kotlinx.android.synthetic.main.headlines_news_item.view.headlineTitleTextView

class HeadlinesViewHolder private constructor(
    private val headlinesItemView: View
) : RecyclerView.ViewHolder(headlinesItemView) {

    fun bind(news: News) {
        headlinesItemView.headlineNewsImageView.load(news.imageUrl) {
            transformations(RoundedCornersTransformation(8.0F))
            crossfade(true)
        }

        headlinesItemView.headlineTitleTextView.text = news.title
    }

    companion object {

        fun create(parent: ViewGroup): HeadlinesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.headlines_news_item, parent, false)

            return HeadlinesViewHolder(view)
        }
    }
}

class HeadlinesHeaderViewHolder private constructor(
    private val header: View
) : RecyclerView.ViewHolder(header) {

    fun bind(news: News) {
        header.headlineNewsImageView.load(news.imageUrl) {
            transformations(RoundedCornersTransformation(8.0F))
            crossfade(true)
        }

        header.headlineTitleTextView.text = news.title
    }

    companion object {

        fun create(parent: ViewGroup): HeadlinesHeaderViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_headline_header, parent, false)

            return HeadlinesHeaderViewHolder(view)
        }
    }
}
