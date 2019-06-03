package com.example.sampleProject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.fragmentpractice.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class SampleLoginActivity extends AppCompatActivity {
    private SessionCallback callback;

    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton button = findViewById(R.id.com_kakao_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback = new SessionCallback();
                Session.getCurrentSession().addCallback(callback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    /**
     * 로그인 결과를 전달 받기 위한 Callback 클래스
     * */
    private class SessionCallback implements ISessionCallback {

        /**
         * 로그인 성공한 상태.
         * */
        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
        }

        /**
         * 로그인 실패한 상태.
         * */
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    protected void redirectSignupActivity() {

        UserManagement.getInstance().requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Logger.e("세션 닫힘 : " + errorResult.getErrorMessage());
            }

            @Override
            public void onNotSignedUp() {
                Logger.e("Not Signed Up." );
            }

            @Override
            public void onSuccess(UserProfile result) {
                Logger.e("김홍규 닉네임 : " + result.getNickname());
            }
        });

    }
}