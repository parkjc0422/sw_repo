package com.stu.sample.cp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.stu.sample.R;

public class ThreadActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        textView = findViewById(R.id.textView);


        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("test2");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("test2");
                    }
                });
            }
        }).start();
    }
}
