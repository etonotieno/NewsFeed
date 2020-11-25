/*
 * Copyright 2020 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.devbits.newsfeed.utils

import android.content.Context
import io.devbits.newsfeed.R

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun getTimeAgo(context: Context, timeInSeconds: Long, abbreviate: Boolean): String? {
    var time = timeInSeconds
    if (time < 1000000000000L) {
        // If timestamp given in seconds, convert to millis
        time *= 1000
    }

    val now = System.currentTimeMillis()
    if (time > now || time <= 0) {
        return null
    }

    val diff = now - time
    return if (diff < MINUTE_MILLIS) {
        context.getString(R.string.now)
    } else if (diff < 2 * MINUTE_MILLIS) {
        if (abbreviate) context.getString(R.string.a_minute_ago_abbreviate)
        context.getString(R.string.a_minute_ago)
    } else if (diff < 50 * MINUTE_MILLIS) {
        if (abbreviate) String.format(
            "%s %s",
            (diff / MINUTE_MILLIS).toString(),
            context.getString(R.string.minutes_ago_abbreviate)
        )
        else String.format(
            "%s %s",
            (diff / MINUTE_MILLIS).toString(),
            context.getString(R.string.minutes_ago)
        )
    } else if (diff < 90 * MINUTE_MILLIS) {
        if (abbreviate) context.getString(R.string.an_hour_ago_abbreviate)
        else context.getString(R.string.an_hour_ago)
    } else if (diff < 24 * HOUR_MILLIS) {
        if (abbreviate) String.format(
            "%s %s",
            (diff / HOUR_MILLIS).toString(),
            context.getString(R.string.hours_ago_abbreviate)
        )
        else String.format(
            "%s %s",
            (diff / HOUR_MILLIS).toString(),
            context.getString(R.string.hours_ago)
        )
    } else if (diff < 48 * HOUR_MILLIS) {
        context.getString(R.string.yesterday)
    } else {
        if (abbreviate) String.format(
            "%s %s",
            (diff / DAY_MILLIS).toString(),
            context.getString(R.string.days_ago_abbreviate)
        )
        else String.format(
            "%s %s",
            (diff / DAY_MILLIS).toString(),
            context.getString(R.string.days_ago)
        )
    }
}