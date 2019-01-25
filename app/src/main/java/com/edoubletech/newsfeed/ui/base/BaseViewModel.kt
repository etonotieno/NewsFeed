package com.edoubletech.newsfeed.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * This BaseViewModel is used to provide the CoroutineScope to all [ViewModel] classes that extend it
 */
open class BaseViewModel : ViewModel() {

    private val baseJob = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + baseJob)

    override fun onCleared() {
        baseJob.cancel()
        super.onCleared()
    }
}
