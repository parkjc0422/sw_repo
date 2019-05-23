package com.example.snsPractice

import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger

class SessionCallbackKt : ISessionCallback{
    override fun onSessionOpened() {
        requestMe()
    }

    override fun onSessionOpenFailed(exception: KakaoException?) {
        exception?.let { }.let{ Logger.e(exception) }
    }

    private fun requestMe(){
        UserManagement.getInstance().requestMe(object : MeResponseCallback(){
            override fun onSessionClosed(errorResult: ErrorResult?) {

            }

            /**
             * 회원이 아닌 경우
             * */
            override fun onNotSignedUp() {

            }

            /**
             * 사용자 정보 요청에 성공한 경우
             * */
            override fun onSuccess(result: UserProfile?) {
                result?.nickname.let { Logger.i("null nickname") }.let { Logger.i(result?.nickname) }
            }

        })
    }
}
