/*
 *  Copyright (C) 2018 Eton Otieno Oboch
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
 *
 */

package com.edoubletech.newsfeed.utils

import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat
import java.util.*

/**
 * This extension function gets the time difference between the date passed as its parameter and the
 * current date from the device.
 *
 * @return the time difference as a long variable.
 */
private fun String.getTimeDifferenceInSeconds(): Long {
    // Gets the current date from the device
    val androidTime = Calendar.getInstance().time

    // Find the difference between the two long values and then convert the value to hours
    val dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(this)
    dateTime.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()))
    val seconds1 = dateTime.millis / 1000
    val seconds2 = androidTime.time / 1000

    return seconds2 - seconds1
}

/**
 * This extension function creates a String variable according to the time in seconds passed as a parameter.
 * For example, if 1 was used then the method would return "1 second ago" . If 2 was used, it
 * would return "2 seconds ago".
 *
 * @return The prettified time string
 */
fun String.getPrettifiedTimeString(): String {
    val timeDifference = this.getTimeDifferenceInSeconds()
    val timeStrings = arrayOf("minute", "hour", "day", "week")
    val timePassed = arrayOf(60, 3600, 86400, 604800)
    var prettifiedTime = if (timeDifference <= 1) timeDifference.toString() + " second ago" else timeDifference.toString() + " seconds ago"
    timePassed.forEachIndexed { index, _ ->
        val calc = timeDifference / timePassed[index]
        if (timeDifference >= timePassed[index]) {
            prettifiedTime = if (calc > 1) calc.toString() + " " + timeStrings[index] + "s ago" else calc.toString() + " " + timeStrings[index] + " ago"
        }
    }
    return prettifiedTime
}
