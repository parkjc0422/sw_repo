package com.stu.sample.cp2.receiver;

import android.app.DownloadManager;
import android.arch.core.util.Function;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DownloadReceiver extends BroadcastReceiver {
    public Function<Long, Void> function;
    @Override
    public void onReceive(Context context, Intent intent) {

        Long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        this.function.apply(id);
    }
}
