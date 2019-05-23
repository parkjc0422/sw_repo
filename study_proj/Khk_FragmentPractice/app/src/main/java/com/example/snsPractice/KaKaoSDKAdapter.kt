package com.example.snsPractice

import com.kakao.auth.*

class KaKaoSDKAdapter : KakaoAdapter(){

    /**
     * App 이 가지고 있는 정보를 얻기 위함.
     * */
    override fun getApplicationConfig(): IApplicationConfig {

        /**
         * return object : IApplicationConfig {
         *  override fun getApplicationContext(): Context {
         *      return GlobalApplication.getGlobalApplicationContext()
         *  }
         * }
         * 와 같음.
         * */
        return IApplicationConfig { GlobalApplication.getGlobalApplicationContext() }

    }

    /**
     *  로그인 할 때 사용 될, Session의 옵션 설정을 위함.
     *  Session은 로그인 객체를 유지시키는 객체.
     *  Access Token을 관리함.
     * */
    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig {
            /**
             * 인증 타입. enum class
             * */
            override fun getAuthTypes(): Array<AuthType> {
                return arrayOf(AuthType.KAKAO_LOGIN_ALL)
            }

            /**
             * 로그인 웹 뷰(Pause 상태로 전환)에서 pause, resume시에 타이머 설정하여 CPU 소모를 절약할 지 여부 지정
             * */
            override fun isUsingWebviewTimer(): Boolean {
                /**
                 * true, 로그인 웹 뷰의 onPause(), onResume() 타이머 설정.
                 * */
                return false
            }

            /**
             * 로그인 시 토큰 저장할 때 암호화 여부 설정.
             * */
            override fun isSecureMode(): Boolean {
                return false
            }

            /**
             * Kakao와 제휴 된 앱에서 사용됨.
             * default 는 INDIVIDUAL
             * */
            override fun getApprovalType(): ApprovalType? {
                return ApprovalType.INDIVIDUAL
            }

            /**
             * 로그인 웹뷰에서 email 입력 폼의 데이터를 저장할 지 여부를 설정.
             * */
            override fun isSaveFormData(): Boolean {
                return true
            }
        }
    }





}