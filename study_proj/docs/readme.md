
Feed
StrictMode

[StrictMode 정의]
진저브레드에서 부터 추가된 일종의 개발툴로 개발자가 실수하는 것들을 감지하고 해결 할 수 있도록 돕는 모드
(실제로 수정하지는 않음 단지 알려줌)
 
 [StrictMode의 주요기능]
 메인 스레드에서 디스크 접근, 네트워크 접근등의 비효율적인 작업을 하려는 것을 감지하여 프로그램 이 부드럽게 작동하도록 돕고,
 빠른 응답을 가지도록 함
 따라서,
 - ANR(Android Not Responding) 방지에도 도움을 줌
 
 - 디스크 I/O 병목 현상 방지
   안드로이드의 디스크는 플래시 메모리라서 빠를 것이라고 생각 할 수도 있으나
     안드로이드의 파일 시스템인 YAFFS(Yet Another Flash File System) 파일 시스템은 I/O 작업을 할 때 Global 범위의 lock이 사용됨
       간단히 말하면 전체 디바이스 상에서 오직 한 번에 하나의 디스크 작업만이 가능한 것
         (보다 자세한 내용 : http://lwn.net/Articles/353411/)
           메모리 여유 공간이 적어지면 적어질 수록 더더욱 느려짐 (참고 : http://code.google.com/p/zippy-android/)
           - 네트워크 접근 등 느린 작업으로 부터 메인 스레드 보호
             안드로이드 콜백과 라이프 사이클 관련된 이벤트들은 모두 메인 스레드(UI 스레드)에서 처리되므로, 보호가 필요함
              
              [사용 예]
              - 전체 어플리케이션에 StrictMode 설정
               public void onCreate() {
                    if (DEVELOPER_MODE) {
                             StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                              .detectDiskReads()
                                                               .detectDiskWrites()
                                                                                .detectNetwork()   // or .detectAll() for all detectable problems
                                                                                                 .penaltyLog()
                                                                                                                  .build());
                                                                                                                           StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                                                                                                                            .detectLeakedSqlLiteObjects()
                                                                                                                                                             .detectLeakedClosableObjects()
                                                                                                                                                                              .penaltyLog()
                                                                                                                                                                                               .penaltyDeath()
                                                                                                                                                                                                                .build());
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                          super.onCreate();
                                                                                                                                                                                                                           }
                                                                                                                                                                                                                           - 문제점 발견시 어떻게 해야 할지 결정 가능
                                                                                                                                                                                                                            
                                                                                                                                                                                                                            - penaltyLog()를 사용하면 문제점 감지시 logcat에 출력 해 줌
                                                                                                                                                                                                                             
                                                                                                                                                                                                                             - penaltyDropbox()를 이용 할 경우 문제점들이 DropBoxManager에 기록되고,
                                                                                                                                                                                                                               adb shell dumpsys sropbox data_app_strictmode --print 명령을 통해 해당 내용 확인 가능
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                - 자세한 방법은 참고 사이트에서 확인 바람
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 [StrictMode의 자율성]
                                                                                                                                                                                                                                 - StrictMode를 통해 찾아낸 문제점들은 threads, Handler, AsyncTask, IntentService등을 통해서 해결가능
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 - 하지만 강제성을 띄지 않음 (종종 디스크의 읽고 쓰기를 빈번하게 사용 할 경우와 같은 특이 케이스들이 존재하므로)
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 - 반면에 UI 스레드(메인 스레드)에서의 네트워크 접근은 반드시 피해야함
                                                                                                                                                                                                                                  
                                                                                                                                                                                                                                  [StrictMode의 불완전성 및 향후계획]
                                                                                                                                                                                                                                  - StrictMode는 보안 매커니즘이 아니므로 모든 디스크와 네트워크의 접근을 다 찾아내지는 못함
                                                                                                                                                                                                                                  
                                                                                                                                                                                                                                  - 계속적인 작업을 통해 보완해 나갈 계획 (API 계속 확장 할 계획임)
                                                                                                                                                                                                                                  
                                                                                                                                                                                                                                  - stackoverflow.com 에서 “strictmode” 라고 태그가 붙은 질문들을 모니터링 하고 있음
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                   [참고사이트]
                                                                                                                                                                                                                                   Developer 사이트 StrictMode 페이지 : http://developer.android.com/reference/android/os/StrictMode.html
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                   관련 블로깅 : http://android-developers.blogspot.com/2010/12/new-gingerbread-api-strictmode.html
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                   관련 블로깅 번역 : http://www.androidside.com/bbs/board.php?bo_table=B46&wr_id=15652
                                                                                                                                                                                                                                   출처 : https://noota.tistory.com/entry/StrictMode-%EA%B0%84%EB%8B%A8-%EC%A0%95%EB%A6%AC
