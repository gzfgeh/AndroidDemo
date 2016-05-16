package com.gzfgeh.CustomScrollView;

import android.app.Activity;
import android.os.Bundle;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/16 10:59.
 */
public class ScrollViewActivity extends Activity implements FlexibleScrollView.OnProgressListener {

    @Bind(R.id.track_view_one)
    ColorTrackView trackViewOne;
    @Bind(R.id.track_view_two)
    ColorTrackView trackViewTwo;
    @Bind(R.id.scroll_view)
    FlexibleScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ButterKnife.bind(this);

        scrollView.setOnProgressListener(this);
        trackViewOne.setDirection(ColorTrackView.DIRECTION_BOTTOM);
        trackViewTwo.setDirection(ColorTrackView.DIRECTION_BOTTOM);
    }

    @Override
    public void getProgress(float p) {
        trackViewOne.setProgress(p);
        trackViewTwo.setProgress(p);
    }
}
