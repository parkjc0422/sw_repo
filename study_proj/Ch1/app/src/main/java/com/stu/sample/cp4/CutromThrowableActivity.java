package com.stu.sample.cp4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stu.sample.R;

public class CutromThrowableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutrom_throwable);

        try {
            test();
        }catch (CustomException e ){
            e.printStackTrace();
        }
    }

    private void test () throws CustomException{

    }
}
