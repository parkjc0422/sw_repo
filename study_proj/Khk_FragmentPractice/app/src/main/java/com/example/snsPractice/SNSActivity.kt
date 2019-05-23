package com.example.snsPractice

import android.content.Context
import android.content.Intent



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.Toast
import com.example.fragmentpractice.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class SNSActivity : AppCompatActivity() {

    private lateinit var mContext : Context
    //private lateinit var mLoginManager: LoginManager
    //private lateinit var callbackManager: CallbackManager
    private lateinit var kakaoLoginButton : LoginButton
    private lateinit var callback : SessionCallback


    override fun onCreate(savedInstanceState: Bundle?) {

        //FacebookSdk.sdkInitialize(this)
        //callbackManager = CallbackManager.Factory.create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = applicationContext

        kakaoLoginButton = findViewById(R.id.com_kakao_login)

        kakaoLoginButton.setOnContextClickListener {
            callback = SessionCallback()
            Session.getCurrentSession().addCallback(callback)
            Session.getCurrentSession().checkAndImplicitOpen()
        }

        /*
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
        */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            return
        }

        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    private class SessionCallback : ISessionCallback{
        override fun onSessionOpened() {
            //redirectSignupActivity()
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if(exception != null){
                Logger.e(exception)
            }
        }
    }

    protected fun redirectSignupActivity() {
        val intent = Intent(this, SNSActivity::class.java)
        startActivity(intent)
        finish()
    }


/*
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
    */

}