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

package com.edoubletech.newsfeed.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun <T> fastLazy(initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, initializer)
}

fun <ViewType : View> Activity.bindView(@IdRes viewId: Int): Lazy<ViewType> {
    return fastLazy { findViewById<ViewType>(viewId) }
}

// TODO: Convert to a safer way of accessing Views from a Fragment
fun <ViewType : View> Fragment.bindView(@IdRes viewId: Int): Lazy<ViewType> {
    return fastLazy { requireView().findViewById<ViewType>(viewId) }
}
