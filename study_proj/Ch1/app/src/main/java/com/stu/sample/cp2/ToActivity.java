package com.stu.sample.cp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stu.sample.R;

public class ToActivity extends AppCompatActivity {

    private Button btnFin;
    private Button btnFinWithResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);


        btnFin = findViewById(R.id.btnFin);
        btnFinWithResp = findViewById(R.id.btnFinWithMsg);

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnFinWithResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("result", "msg");
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
