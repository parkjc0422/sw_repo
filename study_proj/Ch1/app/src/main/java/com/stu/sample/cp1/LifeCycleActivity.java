package com.stu.sample.cp1;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stu.sample.R;
import com.stu.sample.utils.CLog;

public class LifeCycleActivity extends  AppCompatActivity {
    private final String TAG = "LifeCycleActivity";

    /**
     *
     * 화면이 만들어지기 시작하는 시점에 해당 함수가 호출 된다.
     *
     * Guidelines:
     *
     * Initialize and configure UI
     * Initialize Loaders
     *
     * @param savedInstanceState 화면이 전환될 때, onSaveInstanceState  함수를 수행했다면 저장될 데이터
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        CLog.d(TAG, "activity onCreate call");
    }


    /**
     * activity가 화면상으로 올라오기 시작 하는 시점에 해당 함수는 호출된다.
     * 1. 이전 화면으로 돌아가기 위해 back버튼을 누를시
     * 2. 홈버튼을 눌렀다가 해당 앱을 다시 작동시킬시
     *
     * Guidelines:
     *
     * Ensure required system features (GPS, etc) are enabled. Note that this is distinct from acquiring system resources, which should be done in onResume instead.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }


    /**
     * activity가 화면상에 완전히 보여지고, 화면에 focus와 사용자의 이벤트를 받을 준비가 되면 호출된다.
     * (iOS-> viewDidAppear 와 동일하다고 생각하면 된들.)
     *
     * Guidelines:
     *
     * Start (or resume) animations / CPU intensive actions
     * Acquire battery-intensive or exclusive-access system resources (Camera, BroadcastReceivers, GPS, etc)
     */
    @Override
    protected void onResume() {
        super.onResume();
        CLog.d(TAG, "activity onResume call");
    }

    /**
     * 실제 activity는 살아 있지만, 사용자의 이벤트를 감지 하지 못하는 곳으로 갈 때(다른 activity로 전환)
     * onPause 함수가 call 된다.
     * 그렇다면 onStop과의 차이는 무엇일까?
     * 두 상태의 차이는 쉽게 생각하면, onPause는 같은 앱에 있는 다른화면으로 이동시 onStop까지는 호출 되지 않는다.
     *
     * 그렇다면 해당 상태에서는 무엇을 해야 할까?
     * 1. cpu를 많이 사용하는 작업(animation)등을 해당 상태에서 정지 되어야 한다.
     * 2. battery에 영향을 많이 주는 GPS와 같은 서비스도 해당 시점에서 중지 시켜야 한다.
     * 3. 카메라와 같은 다른 리소스에 접근 하는것도 여기서 중지 시키는것이 좋다.
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        CLog.d(TAG, "activity onPause call");
    }

    /**
     * activity가 kill되기 직전에 무조건 한번은 호출 되는 함수이다.
     * low memory 등과 같이 일반적이지 않은 상황에서는 onDestroy는 호출되지 않지만, onStop은 호출된디.
     * 그러므로, 메모리를 해제 시켜줘야 할 일이있다면 해당 함수에서 작업해야 적절할 듯 하다.
     *
     * Guidelines:
     *
     * Write to database
     * Perform CPU-intensive shutdown operations
     * Release resources that might leak memory
     */
    @Override
    protected void onStop() {
        super.onStop();
        CLog.d(TAG, "activity onStop call");
    }

    /**
     * 더이상 필요가 없어진 activity의 경우 해당 function이 call 된다.
     * 가령 더이상 해당 activity를 유지하고 싶지 않은 경우 {{@link Activity#finish()}}를 호출하면, 해당 activity는
     * onDestroy를 call 하고, 메모리 상에서 삭제된다.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        CLog.d(TAG, "activity onDestroy call");
    }



    /**
     * activity 의 life cycle 에 명시되지 않는 함수이다.
     * 해당 함수는 화면 상태값을 보존 하기 위한 값.
     * bundle에 화면의 status를 정장하고 있다가,
     * {@link Activity#onCreate(Bundle)}혹은  {@link Activity#onRestoreInstanceState(Bundle)}에서
     * 화면의 상태 값을 지정할 수 있다.
     * {@info 해당 함수는 정식 life cycle 이 아니므로, 무조건 상태값을 저장하고 있다는 보장은 하지못한다. }
     * 보통 onPause() 함수 전에 호출
     *
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * 화면 전환 할 때 onSaveInstanceState에 의해 보존된 상태 값이 존재한다면 복구하는 함수
     * 정식 life cycle이 아님.
     * 보통 onResume() 함수전에 호출
     *
     * @param savedInstanceState
     *
     * */

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){

    }
}
