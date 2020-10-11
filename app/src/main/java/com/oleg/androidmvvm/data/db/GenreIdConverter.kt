package com.oleg.androidmvvm.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreIdConverter {

    @TypeConverter
    fun stringToGenreList(string: String?): List<Int> {
        if (string == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun genreListToString(genreIds: List<Int>?): String {
        if (genreIds == null) {
            return Gson().toJson(Collections.emptyList<Int>())
        }
        return Gson().toJson(genreIds)
    }
}