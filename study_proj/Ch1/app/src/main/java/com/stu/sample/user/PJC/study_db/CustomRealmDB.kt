package com.stu.sample.user.PJC.study_db

import android.content.Context
import com.stu.sample.cp1.SApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults


/**
 * What is realm?
 *
 *
 */
class CustomRealmDB {
    companion object {
        @JvmStatic
        var instance: CustomRealmDB = CustomRealmDB()
    }

    var items: RealmResults<CustomModel>? = null
    var realmDB:Realm
    init {
        /**
         * 1번만 실행 해야 하는코드
         * 일반적으로는 application class에서 적용한다.
         */
        Realm.init(SApplication.instance)
        realmDB = Realm.getDefaultInstance()
    }


    fun destory () {
        saveFull()
    }

    fun loadModel() {
        /**
         * Default Configuration :
         * RealmConfiguration.Builder(context).build()
         *
         *  this.directory = context.getFilesDir();
         *  this.fileName = Realm.DEFAULT_REALM_NAME;
         *  this.key = null;
         *  this.schemaVersion = 0;
         *  this.migration = null;
         *  this.deleteRealmIfMigrationNeeded = false;
         *  this.durability = OsRealmConfig.Durability.FULL;
         *  this.readOnly = false;
         *  this.compactOnLaunch = null;
         *  if (DEFAULT_MODULE != null) {
         *      this.modules.add(DEFAULT_MODULE);
         * }
         *
         * 1. db 파일의 이름 및 위치 저장소를 default로 저장되는듯 하다.
         *
         */
        val result = realmDB.where(CustomModel::class.java)
                .findAll()

        result.addChangeListener { t, changeSet ->
            changeSet.insertions
        }

        items = result
    }


    fun storeItem(model: CustomModel, withSave: Boolean = false):Boolean {
        items?.add(model)

        if(withSave) {
            saveItem(model)
        }
        return true
    }

    private fun saveItem(model: CustomModel) {
        /**
         * DB Transaction start
         */
        realmDB.beginTransaction()
        /**
         * sql query
         */
        realmDB.insertOrUpdate(model)
        /**
         * DB Transaction end
         */
        realmDB.commitTransaction()
    }


    private fun saveFull() {
        genericTransaction {
            it.insertOrUpdate(items)
        }
    }

    private fun genericTransaction(query: (Realm)->Unit) {
        realmDB.beginTransaction()
        query(realmDB)
        realmDB.commitTransaction()
    }
}