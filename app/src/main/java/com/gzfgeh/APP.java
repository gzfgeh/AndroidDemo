package com.gzfgeh;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;


/**
 * Description: 初始化一些全局类
 * Created by guzhenfu on 2016/2/25 15:03.
 */
public class APP extends Application {
    private static final String APATCH_PATH = "/out.apatch";
    private static Context context;
    private volatile static PatchManager patchManager;

    public static Context getContext(){
        return context;
    }

    public static PatchManager getPatchManagerInstance(){
        if (patchManager == null){
            synchronized (context){
                if (patchManager == null)
                    patchManager = new PatchManager(context);
            }
        }
        return patchManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        try {
            String version= context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            getPatchManagerInstance();
            patchManager.init(version);
            patchManager.loadPatch();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        //正常情况下应该是 从服务器下载，然后addPatch，因为这里测试方便就放到SDCard上面了
        //storage/emulated/0/out.apatch
        String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        try {
            patchManager.addPatch(patchFileString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean debugMode(){
        ApplicationInfo info = getContext().getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return true;
        }else{
            return false;
        }
    }

}
