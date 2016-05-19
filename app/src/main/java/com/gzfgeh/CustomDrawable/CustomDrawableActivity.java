package com.gzfgeh.CustomDrawable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/19 11:23.
 */
public class CustomDrawableActivity extends Activity {
    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.startpage);
        CircleImageDrawable drawable = new CircleImageDrawable(this,bitmap);
        image.setImageDrawable(drawable);
    }
}
