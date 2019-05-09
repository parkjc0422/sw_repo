package com.chh.ttt;

import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLExt;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private Button create;
    private Button select;
    private Button update;
    private Button delete;
    private EditText txtName;
    private EditText txtNum;
    private String sPhoneNum;
    //private int brightness = 0;

    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init(); //  초기화

        recyclerView = findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        ArrayList<itemData> itemData = new ArrayList<>();
//        itemData.add(new itemData("01020770071",R.drawable.k ));
//        itemData.add(new itemData("01098705758",R.drawable.q ));
//
//        MyAdapter myAdapter = new MyAdapter(itemData);
//        recyclerView.setAdapter(myAdapter);

        create = (Button)findViewById(R.id.btnCreate);
        select = (Button)findViewById(R.id.btnSelect);
        update = (Button)findViewById(R.id.btnUpate);
        delete = (Button)findViewById(R.id.btnDelete);
        txtName = (EditText)findViewById(R.id.txtName);
        txtNum = (EditText)findViewById(R.id.txtNum);

//        try {
//            brightness = android.provider.Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Exception e) {
//
//        }
    }
    public void dbTransaction(View v) {
        switch (v.getId()) {
            case R.id.btnCreate : create(); break;
            case R.id.btnSelect : break;
            case R.id.btnUpate : break;
            case R.id.btnDelete : break;
        }
    }
    public void create() {
        if(txtName.getText().toString().length() == 0) {
            Toast.makeText(this,"입력!",Toast.LENGTH_LONG).show();
            return;
        }
        else  {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Info info = realm.createObject(Info.class, UUID.randomUUID().toString());
                    info.setName(txtName.getText().toString());
                    info.setpNum(txtNum.getText().toString());
                    realm.beginTransaction();
                    realm.copyToRealm(info);
                    realm.commitTransaction();
                }
            });
        }
    }
    public void select() {

    }
    private void init() {

        realm.init(this);
        realm = Realm.getDefaultInstance();

        ArrayList<Info> arrInfo = new ArrayList<>();
        RealmResults<Info> infoList = getInfoList();

        for(Info info : infoList) {
            //infoList를 어떻게 recyclerView에 뿌려줄지..
            MyAdapter myAdapter = new MyAdapter(arrInfo);
            recyclerView.setAdapter(myAdapter);
        }
    }
    private RealmResults<Info> getInfoList() {
        return realm.where(Info.class).findAll();
    }


}
