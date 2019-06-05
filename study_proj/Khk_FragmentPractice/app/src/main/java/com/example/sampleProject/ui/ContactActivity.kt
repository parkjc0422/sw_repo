package com.example.sampleProject.ui


import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.sampleProject.R
import com.example.sampleProject.model.Contact
import com.example.sampleProject.ui.adapter.CustomAdapter
import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.concurrent.TimeUnit

class ContactActivity : AppCompatActivity() {

    val INDEX_NAME = 0
    val INDEX_NUMBER = 1
    val INDEX_ID = 2

    private val PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults[0].equals(PackageManager.PERMISSION_GRANTED)){
                init()
            }
        }
    }

    private fun init(){

        setContentView(R.layout.activity_contacts)

        if(ContextCompat.checkSelfPermission(this, permission.READ_CONTACTS).equals(PackageManager.PERMISSION_GRANTED)) {


            val adapter = CustomAdapter(this, getContactList())
            contactRecyclerView.adapter = adapter

            val linearLayoutManager = LinearLayoutManager(this)
            contactRecyclerView.layoutManager = linearLayoutManager
            contactRecyclerView.setHasFixedSize(true)
            contactRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        }
        Observable.create<View> { contactCheckTextView.setOnClickListener(it::onNext)  }
            .map {  }
            .subscribe {  }

        val source = Observable.create<CharSequence> { emitter ->
            contactCheckTextView.addTextChangedListener( object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                     s?.let{
                        emitter.onNext(it)
                    }
                }
            })
        }

        source.debounce(3000L, TimeUnit.MILLISECONDS)
            .filter{ !TextUtils.isEmpty(it)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Toast.makeText(this, "Searching => $it",Toast.LENGTH_SHORT).show()
            }
    }

    private fun getContactList() : List<String>{
        var contactList = object : ArrayList<String>() {}
        try {
            var uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            var projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            )
            var sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC"

            var cursor = contentResolver.query(uri, projection, null, null, sortOrder)


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
    private fun callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && checkSelfPermission(permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
        else{
            init()
        }
    }
}