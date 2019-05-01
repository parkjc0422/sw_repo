package com.stu.sample.user.PJC.study_db

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


class CustomModel: RealmObject(){
    @PrimaryKey
    var id: Int = -1

    lateinit var list: RealmList<String>

    lateinit var name: String


    companion object {
        @JvmStatic
        fun build():CustomModel {
            return CustomModel()
        }
    }



    fun list(list: RealmList<String> = RealmList()): CustomModel{
        this.list = list
        return this
    }

    fun name(name:String = ""): CustomModel {
        this.name = name
        return this
    }
}