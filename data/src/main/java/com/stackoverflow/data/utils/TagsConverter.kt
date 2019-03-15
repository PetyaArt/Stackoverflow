package com.stackoverflow.data.utils

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.stackoverflow.data.entities.OwnerData
import android.provider.MediaStore.Video
import com.google.gson.reflect.TypeToken


class TagsConverter {

    @TypeConverter
    fun fromTags(tags: List<String>): String {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun toTags(str: String): List<String> {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }
}