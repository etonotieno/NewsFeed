package com.edoubletech.newsfeed.ui

import com.edoubletech.newsfeed.data.ResourceState
import com.edoubletech.newsfeed.guardian.model.News

/**
 * This class holds the View state for the app using a sealed class.
 * There are 3 states:
 * [Loading], [Success] and [Error]
 */
sealed class NewsState(val resourceState: ResourceState,
                       val data: List<News>? = null,
                       val errorMessage: String? = null) {
    object Loading : NewsState(ResourceState.LOADING)

    data class Success(private val newsList: List<News>) : NewsState(ResourceState.SUCCESS,
            newsList)

    data class Error(private val message: String? = null) : NewsState(ResourceState.ERROR,
            errorMessage = message)
}