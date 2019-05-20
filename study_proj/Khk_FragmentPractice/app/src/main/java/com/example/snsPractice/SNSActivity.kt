package com.example.snsPractice

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.Toast
import com.example.fragmentpractice.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SNSActivity : AppCompatActivity() {

    private lateinit var mLoginManager: LoginManager
    private lateinit var callbackManager: CallbackManager
    private lateinit var facebook_login: LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {

        FacebookSdk.sdkInitialize(this)

        callbackManager = CallbackManager.Factory.create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getHashKey()

        facebook_login = findViewById(R.id.facebook_login)

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // App code
                    println(loginResult.accessToken.userId)
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private fun getHashKey() {
        try {
            var info = packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES);
            info.signatures.forEach {
                var md = MessageDigest.getInstance("SHA");
                md.update(it.toByteArray());
                println("key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT)) }

        } catch (ex : PackageManager.NameNotFoundException ) {
            ex.printStackTrace();
        } catch (ex : NoSuchAlgorithmException) {
            ex.printStackTrace();
        }
    }

}