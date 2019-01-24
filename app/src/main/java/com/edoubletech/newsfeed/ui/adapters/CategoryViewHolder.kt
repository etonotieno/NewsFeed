package com.edoubletech.newsfeed.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edoubletech.newsfeed.R
import com.edoubletech.newsfeed.data.model.Category

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val gridImage: ImageView = itemView.findViewById(R.id.grid_image)
    private val gridName: TextView = itemView.findViewById(R.id.grid_name)

    fun bind(category: Category, onItemClick: (category: Category) -> Unit) {
        gridName.text = category.name
        gridImage.setImageResource(category.image)

        itemView.setOnClickListener {
            onItemClick(category)
        }
    }
}