package com.stu.sample.cp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stu.sample.R;
import com.stu.sample.utils.CLog;

public class MessageActivity extends AppCompatActivity {
    private static final String TAG = "MessageActivity";
    public static final String MsgKey = "MessageKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        /**
         * {@link Intent#getStringExtra(String)} value is nullable
         */
        String value = getIntent().getStringExtra(MsgKey);
        CLog.i(TAG, "message from intent is " + value);
    }
}
