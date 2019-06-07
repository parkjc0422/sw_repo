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
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.concurrent.TimeUnit

class ContactActivity : AppCompatActivity() {

    val INDEX_NAME = 0

    private val PERMISSIONS_REQUEST_READ_CONTACTS = 100

    private lateinit var adapter : CustomAdapter

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

        // observable : 계속해서 data를 emit, produce하는 collection
        // 단순히 검색만 했다면 이벤트로만 등록해도 됨.
        // onNext() : 데이터 발행
        val source = Observable.create<CharSequence> { emitter ->
            contactCheckTextView.addTextChangedListener( object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // let
                    //  null이 아니면 수행.
                    // 변수 뿐만 아니라 함수를 호출하는 객체를 이어지는 블록의 파라미터로 넘기고, 블록의 결과 값을 반환
                    // 여기서의 반환 값은 onTextChanged의 반환 값이여야 하나 void 이므로 return 값이 없다.
                     s?.let{
                         // changedText 라는 인자이름을 생략하고 it로 가능
                         // textChanged 이벤트 발생 후 데이터 발행
                         changedText -> emitter.onNext(changedText)

                     }
                }
            })
        }
            .filter{ !TextUtils.isEmpty(it)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Toast.makeText(this, "Searching => $it",Toast.LENGTH_SHORT).show()
                reload()
            }


        // debounce 이벤트 입력 후 지정 시간동안 추가 이벤트가 발생하지 않으면 마지막 이벤트를 발행
        // filter : 조건
        // observeOn : 특정 작업의 스케쥴러를 설정. Toast는 UI(Maiu) 스레드에서 동작하기 때문에 observeOn을 사용
        // * subscribeOn : Observable이 동작하는 스케쥴러를 설정. Observable 객체가 실행 될 스레드를 정한다. 호출 시점과 상관 없음 X
        // subscribe : 이벤트 실행 부분
        // 1초 동안 아무 이벤트가 발생하지 않으면 subsctibe 부분이 실행된다.
//        source.debounce(1000L, TimeUnit.MILLISECONDS)
//            .filter{ !TextUtils.isEmpty(it)}
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{
//                Toast.makeText(this, "Searching => $it",Toast.LENGTH_SHORT).show()
//                reload()
//            }

        // 참고 : http://minsone.github.io/programming/reactive-swift-observeon-subscribeon
        // https://jungwoon.github.io/rxjava2/2017/12/14/RxJava-1/

    }

    private fun reload(){
        adapter = CustomAdapter(this, getContactList(contactCheckTextView.text.toString()))
        contactRecyclerView.adapter = adapter

    }

    private fun getContactList(filterString: String?) : List<String>{
        var contactList = object : ArrayList<String>() {}

        try {

            var cursor : Cursor

            // uri : 컨텐츠 uri. content provider 의 테이블 이름에 매핑된다.
            // projection : 어떤 컬럼을 볼 지
            // selection : 조건문
            // selectionArgs : 조건문에 들어갈 파라미터
            // sortOrder : 정렬 기준
            var uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            var projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            var selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?"
            var selectionArgs = arrayOf( "%" + filterString + "%")
            var sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC"

            // contentResolver : 컨텐츠 제공자. 외부 App에 대한 데이터를 표시. App 내에서 데이터를 공유하기 위한 컴포넌트.
            //                   App Layer와 Data Layer 사이에 존재.
            // VS SQLLite : Data에 직접 조회?
            // ??
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
        }

        return contactList
    }
}