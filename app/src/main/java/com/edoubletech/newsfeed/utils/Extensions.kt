package com.edoubletech.newsfeed.utils

import android.content.res.Configuration
import androidx.fragment.app.Fragment

val Fragment.isInLandscapeMode: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
