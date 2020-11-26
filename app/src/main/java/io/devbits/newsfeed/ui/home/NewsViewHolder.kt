/*
 *  Copyright 2019 Eton Otieno
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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.devbits.newsfeed.data.News
import io.devbits.newsfeed.databinding.NewsItemBinding
import io.devbits.newsfeed.databinding.TopStoriesHeaderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat

class NewsViewHolder private constructor(
    private val binding: NewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        Glide.with(binding.root)
            .load(news.imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.thumbnailImageView)

        binding.newsSourceTextView.text = news.source
        binding.titleTextView.text = news.title

        binding.dateTextView.text = getFormattedDate(news.publicationDate)

        binding.root.setOnClickListener {
            val navController = it.findNavController()
            val directions = HomeFragmentDirections.actionHomeToDetail(news)
            navController.navigate(directions)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NewsItemBinding.inflate(inflater, parent, false)
            return NewsViewHolder(binding)
        }
    }

    private fun getFormattedDate(date: String): String {
        val dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(date)
        dateTime.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()))
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val time = Calendar.getInstance().apply { timeInMillis = dateTime.millis }.time
        return simpleDateFormat.format(time)
    }
}

class TopStoriesHeaderViewHolder private constructor(
    binding: TopStoriesHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): TopStoriesHeaderViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TopStoriesHeaderBinding.inflate(inflater, parent, false)
            return TopStoriesHeaderViewHolder(binding)
        }
    }
}
