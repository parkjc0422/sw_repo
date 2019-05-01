package com.stu.sample.user.PJC.study_gson

import com.google.gson.annotations.SerializedName

class CustomModel {
    @SerializedName("test1")
    var test1: String? = null

    @SerializedName("test2")
    var test2: String? = null
}