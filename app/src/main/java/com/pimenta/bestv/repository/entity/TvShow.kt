/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.repository.entity

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.j256.ormlite.table.DatabaseTable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by marcus on 06/07/18.
 */
@DatabaseTable(tableName = TvShow.TABLE)
class TvShow : Work() {

    @SerializedName("name")
    override var title: String? = null
    @SerializedName("original_name")
    override var originalTitle: String? = null
    @SerializedName("first_air_date")
    private var mFirstAirDate: String? = null

    private val dateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }

    override var releaseDate: Date?
        get() {
            return try {
                dateFormat.parse(mFirstAirDate)
            } catch (e: ParseException) {
                Log.e(TAG, "Error to get the release data", e)
                null
            }
        }
        set(value) {
            mFirstAirDate = value.toString()
        }

    companion object {

        private val TAG = TvShow::class.java.simpleName
        const val TABLE = "tv_show"
    }
}