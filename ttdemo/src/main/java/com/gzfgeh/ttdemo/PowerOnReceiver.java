package com.gzfgeh.ttdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        /*判断是否与action匹配*/
//        if(action.equals("android.intent.action.BOOT_COMPLETED")) {
//            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("main_activity", "PowerOnReceiver");
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//            Toast.makeText(context, "main_activity", Toast.LENGTH_SHORT).show();
//        }
        Toast.makeText(context, "main_activity", Toast.LENGTH_SHORT).show();
    }
}
