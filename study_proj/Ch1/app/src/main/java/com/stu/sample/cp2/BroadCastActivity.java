package com.stu.sample.cp2;

import android.app.DownloadManager;
import android.arch.core.util.Function;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stu.sample.R;
import com.stu.sample.cp2.receiver.DownloadReceiver;

public class BroadCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);


        DownloadReceiver receiver = new DownloadReceiver();
        receiver.function = new Function<Long, Void>() {
            @Override
            public Void apply(Long input) {
                /**
                 * TODO something
                 */
                return null;
            }
        };


        getApplicationContext().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
