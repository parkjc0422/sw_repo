package com.example.sampleProject.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.sampleProject.R
import com.example.sampleProject.ui.adapter.CustomAdapter
import android.Manifest.permission
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactActivity : AppCompatActivity() {

    private val INDEX_NAME = 0
    private val PERMISSIONS_REQUEST_READ_CONTACTS = 100

    private lateinit var adapter : CustomAdapter
    private lateinit var textDisposable : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callPermission()
    }

    /**
     * 퍼미션 여부 확인 후 부여되지 않으면 퍼미션 요청
     * @author khk
     * */
    private fun callPermission() {
        // 버전 체크 및 해당 퍼미션이 허용됐는지 확인 후 없으면 퍼미션 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // requestCode인 PERMISSIONS_REQUEST_READ_CONTACTS는 식별하기 위해 사용자가 정의한다.
            // permission은 배열이므로 여러 개를 요청 할 수 있다.
            requestPermissions(arrayOf(permission.READ_CONTACTS), PERMISSIONS_REQUEST_READ_CONTACTS)
            //-> request 요청 후 onRequestPermissionResult 메서드로 간다.
        }
        else {
            init()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                init()
            }
        }
    }

    private fun init(){

        setContentView(R.layout.activity_contacts)

        adapter = CustomAdapter(this, getContactList(contactCheckTextView.text.toString()))
        contactRecyclerView.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this)
        contactRecyclerView.layoutManager = linearLayoutManager
        contactRecyclerView.setHasFixedSize(true)
        contactRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // RxBinding을 통해 TextWatcher를 통한 리스너가 아닌 textChangeEvents를 사용
        textDisposable = RxTextView.textChangeEvents(contactCheckTextView)
            //.debounce(1000L,TimeUnit.MILLISECONDS)
            //.filter{ !TextUtils.isEmpty(it.text())}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Toast.makeText(this, "Searching => ${it.text()}", Toast.LENGTH_SHORT).show()
                reload()
            }
    }

    private fun reload(){
        adapter = CustomAdapter(this, getContactList(contactCheckTextView.text.toString()))
        contactRecyclerView.adapter = adapter
    }

    private fun getContactList(filterString: String?) : List<String>{
        var contactList = object : ArrayList<String>() {}
        var cursor : Cursor? = null
        try {
            /**
             * uri : 컨텐츠 uri. content provider 의 테이블 이름에 매핑된다.
             * projection : 어떤 컬럼을 볼 지
             * selection : 조건문
             * sortOrder : 정렬 기준
             * selectionArgs : 조건문에 들어갈 파라미터
             */
            var uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            var projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            var selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?"
            var selectionArgs = arrayOf( "%" + filterString + "%")
            var sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC"

            cursor = if(filterString.equals(null)){
                contentResolver.query(uri, projection, null, null, sortOrder)
            } else {
                contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
            }


            // cursor를 결과의 첫번째 row로 이동시킨다.
            // 커서가 empty면 false를 return 한다.
            if (cursor.moveToFirst()) {
                do {

                    var name = cursor.getString(INDEX_NAME)

                    contactList.add(name)

                } while (cursor.moveToNext())
            }
        }catch(ex:Exception){
            ex.printStackTrace()
        }finally{
            cursor?.close()
        }

        return contactList
    }

    override fun onDestroy() {
        super.onDestroy()

        /**
         * dispose the resource.
         * */
        textDisposable.dispose()
    }
}