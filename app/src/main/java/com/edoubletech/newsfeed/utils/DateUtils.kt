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


object DateUtils {
    /**
     * This method gets the time difference between the date passed as its parameter and the
     * current date from the device.
     *
     * @param dateString The date as a String which we want to get the time difference.
     * @return the time difference as a long variable.
     */
    @JvmStatic
    fun getTimeDifferenceInSeconds(dateString: String): Long {
        // Gets the current date from the device
        val androidTime = Calendar.getInstance().time

        // Find the difference between the two long values and then convert the value to hours
        val dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(dateString)
        dateTime.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()))
        val seconds1 = dateTime.millis / 1000
        val seconds2 = androidTime.time / 1000

        return seconds2 - seconds1
    }

    /**
     * This method creates a String variable according to the time in seconds passed as a parameter.
     * For example, if 1 was used then the method would return "1 second ago" . If 2 was used, it
     * would return "2 seconds ago".
     *
     * @param time The time in seconds which you want to get the appropriate string for.
     * @return The prettified time string
     */
    @JvmStatic
    fun getPrettifiedTimeString(time: Long): String {
        val timeStrings = arrayOf("minute", "hour", "day", "week")
        val timePassed = arrayOf(60, 3600, 86400, 604800)
        var prettifiedTime = if (time <= 1) time.toString() + " second ago" else time.toString() + " seconds ago"
        for (i in timePassed.indices) {
            val calc = time / timePassed[i]
            if (time >= timePassed[i]) {
                prettifiedTime = if (calc > 1) calc.toString() + " " + timeStrings[i] + "s ago" else calc.toString() + " " + timeStrings[i] + " ago"
            }
        }
        return prettifiedTime

    }
}
