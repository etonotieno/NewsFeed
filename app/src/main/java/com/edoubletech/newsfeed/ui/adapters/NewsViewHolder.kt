package com.edoubletech.newsfeed.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.guardian.model.News
import com.edoubletech.newsfeed.utils.getPrettifiedTimeString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

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