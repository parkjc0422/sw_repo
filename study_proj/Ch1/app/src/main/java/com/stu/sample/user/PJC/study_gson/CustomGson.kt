package com.stu.sample.user.PJC.study_gson

import com.google.gson.Gson


class CustomGson {
    /**
     * String to json
     */
    fun testConvert(jsonString: String) :CustomModel {
        val gson = Gson()
        return gson.fromJson(jsonString, CustomModel::class.java)
    }
}