package com.stu.sample.cp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stu.sample.R;

public class EventActivity extends AppCompatActivity {
    private EditText keypadEvent;
    private Button clickEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        keypadEvent = findViewById(R.id.keypadEvent);
        clickEvent = findViewById(R.id.clickEvent);

        /**
         *  key pad event
         */
        keypadEvent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clickEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO : click event
                 */
            }
        });
    }
}
