package com.example.snsPractice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fragmentpractice.R
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger

open class SampleLoginActivityKt() : AppCompatActivity(){

    private lateinit var callback : SessionCallback

    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
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
           exception?.let {  }
               .let{Logger.e(exception)}
        }
    }

    fun redirectSignupActivity() {
        val intent = Intent(this, SampleLoginActivityKt::class.java)
        startActivity(intent)
        finish()
    }



}



