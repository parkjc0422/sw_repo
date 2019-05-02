package com.chh.ttt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private Button call;
    private EditText editText;
    private String sPhoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //loadFromIntenalStorage();

        recyclerView = findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<itemData> itemData = new ArrayList<>();
        itemData.add(new itemData("01020770071",R.drawable.k ));
        itemData.add(new itemData("01098705758",R.drawable.q ));

        MyAdapter myAdapter = new MyAdapter(itemData);
        recyclerView.setAdapter(myAdapter);

        call = (Button)findViewById(R.id.call);
        editText = (EditText)findViewById(R.id.editText);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String receiver = editText.getText().toString();
                /**
                 * ACTION_DIAL
                 */
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ receiver));

                startActivity(intent);

            }
        });

    }

    public void OnFileWrite(View v) {
        /**
         * getFilesDir : 안드로이드의 내부 파일구조
         *
         * 1. 내부 파일
         * 2. external storage(외부 공간)
         *
         * 1번의 경우 강제로 외부에서 찾아 갈 수는 있으나, 경로가 찾기 비교적 어렵게 되어있다.
         * 2번의 경우 공유폴더 같은 개념으로 Download등과 같이 다른 앱들이 공유 하기 위한 공간으로 사용된다.
         */


        String filePath = getFilesDir().toString();
        String sTimeKey = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try{
            FileWriter fileWriter = new FileWriter(filePath+"test.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sTimeKey);
            bufferedWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = openFileOutput("test", Context.MODE_PRIVATE);
//            fileOutputStream.write(sTimeKey.getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
//
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
    }
    private void loadFromIntenalStorage(){
        try {
            FileInputStream fis = openFileInput("test.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String str = buffer.readLine();

            String data[] = null;
            int i = 0;
            while (str != null) {
                data[i] = str;
                str = buffer.readLine();
                i++;
            }
            buffer.close();

        } catch (FileNotFoundException e) {

        } catch (Exception e) {

        }
    }
}
