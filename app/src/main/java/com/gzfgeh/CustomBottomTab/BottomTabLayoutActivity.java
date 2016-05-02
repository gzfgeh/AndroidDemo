package com.gzfgeh.CustomBottomTab;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 16/5/2.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class BottomTabLayoutActivity extends AppCompatActivity {
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    private TabLayout.Tab one, two, three, four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        ButterKnife.bind(this);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] titles = new String[]{"唐僧", "大师兄", "二师兄", "三师兄"};

            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new TabFragment();

                    case 1:
                        return new TabFragment();

                    case 2:
                        return new TabFragment();

                    case 3:
                        return new TabFragment();
                }
                return new TabFragment();
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);

        one.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
        two.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
        three.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
        four.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)){
                    one.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
                    viewPager.setCurrentItem(0);
                }else if (tab == tabLayout.getTabAt(1)){
                    two.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
                    viewPager.setCurrentItem(1);
                }else if (tab == tabLayout.getTabAt(2)){
                    three.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
                    viewPager.setCurrentItem(2);
                }else{
                    four.setIcon(getResources().getDrawable(R.drawable.btn_backgroud));
                    viewPager.setCurrentItem(3);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)){
                    one.setIcon(getResources().getDrawable(R.drawable.tgclick));
                }else if (tab == tabLayout.getTabAt(1)){
                    two.setIcon(getResources().getDrawable(R.drawable.tgclick));
                }else if (tab == tabLayout.getTabAt(2)){
                    three.setIcon(getResources().getDrawable(R.drawable.tgclick));
                }else{
                    four.setIcon(getResources().getDrawable(R.drawable.tgclick));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
