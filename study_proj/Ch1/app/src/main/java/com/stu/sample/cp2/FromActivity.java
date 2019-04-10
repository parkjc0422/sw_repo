package com.stu.sample.cp2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stu.sample.R;

public class FromActivity extends AppCompatActivity {
    private Button sendMsg;
    private Button receiveMsg;

    private final int ReqCode = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);


        /**
         * bind view
         */
        sendMsg = findViewById(R.id.btnSendMsg);
        receiveMsg = findViewById(R.id.btnReceivemsg);


        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FromActivity.this, ToActivity.class);
                startActivity(intent);
            }
        });

        receiveMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FromActivity.this, ToActivity.class);
                intent.putExtra("test","test");
                startActivityForResult(intent, ReqCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if (requestCode == ReqCode) {
                /**
                 *  receive msg
                 */
            }
        }
    }
}
