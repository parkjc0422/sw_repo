package com.stu.sample.user.PJC.study_db



class SampleExample
{

    /**
     * more info
     * https://realm.io/docs/java/latest/
     */
    fun test () {
        var realm= CustomRealmDB.instance
        var model = CustomModel.build().name().list()
        realm.storeItem(model)
    }
}