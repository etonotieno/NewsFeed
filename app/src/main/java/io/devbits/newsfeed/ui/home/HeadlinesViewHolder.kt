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
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import io.devbits.newsfeed.data.model.News
import io.devbits.newsfeed.databinding.HeadlinesNewsItemBinding
import io.devbits.newsfeed.databinding.NewsHeadlineHeaderBinding

class HeadlinesViewHolder private constructor(
    private val binding: HeadlinesNewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.headlineNewsImageView.load(news.imageUrl) {
            transformations(RoundedCornersTransformation(16F))
            crossfade(true)
        }

        binding.headlineTitleTextView.text = news.title
    }

    companion object {

        fun create(parent: ViewGroup): HeadlinesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HeadlinesNewsItemBinding.inflate(inflater, parent, false)
            return HeadlinesViewHolder(binding)
        }
    }
}

class HeadlinesHeaderViewHolder private constructor(
    binding: NewsHeadlineHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HeadlinesHeaderViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NewsHeadlineHeaderBinding.inflate(inflater, parent, false)
            return HeadlinesHeaderViewHolder(binding)
        }
    }
}
