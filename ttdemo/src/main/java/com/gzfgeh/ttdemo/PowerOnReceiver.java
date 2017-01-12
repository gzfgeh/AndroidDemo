package com.gzfgeh.ttdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerOnReceiver extends BroadcastReceiver {
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        /*判断是否与action匹配*/
        if(action.equals(ACTION_BOOT)) {
            Intent i = new Intent(context, MainActivity.class);
            i.putExtra("main_activity", "PowerOnReceiver");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
