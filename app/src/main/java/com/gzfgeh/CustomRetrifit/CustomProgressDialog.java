package com.gzfgeh.CustomRetrifit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gzfgeh.R;

/**
 * Description:
 * Created by guzhenfu on 16/5/20.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class CustomProgressDialog extends ProgressDialog {
    private Context context;
    private WaterView waterView;

    public CustomProgressDialog(Context context) {
        this(context, R.style.theme_customer_progress_dialog);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_content, null);
        waterView = (WaterView) view.findViewById(R.id.water_view);
        setContentView(view);
        setCanceledOnTouchOutside(false) ;
    }

    public static CustomProgressDialog show(Context context){
        CustomProgressDialog dialog = new CustomProgressDialog(context) ;
        dialog.show() ;
        return dialog ;
    }

    public void setProgress(float progress){
        waterView.setWaterLevel(progress);
    }
}
