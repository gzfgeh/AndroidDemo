package com.gzfgeh.Login;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gzfgeh.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AutoLayoutActivity {

    @Bind(R.id.login_logo)
    ImageView loginLogo;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.name_wrapper)
    TextInputLayout nameWrapper;
    @Bind(R.id.pwd)
    EditText pwd;
    @Bind(R.id.pwd_wrapper)
    TextInputLayout pwdWrapper;
    @Bind(R.id.ll_pwd_layout)
    LinearLayout llPwdLayout;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.code_wrapper)
    TextInputLayout codeWrapper;
    @Bind(R.id.get_code)
    Button getCode;
    @Bind(R.id.ll_code_layout)
    LinearLayout llCodeLayout;
    @Bind(R.id.forget_pwd)
    TextView forgetPwd;
    @Bind(R.id.code_login)
    TextView codeLogin;
    @Bind(R.id.register)
    Button register;
    @Bind(R.id.move_layout)
    LinearLayout moveLayout;
    @Bind(R.id.login_ll_layout)
    LinearLayout loginLlLayout;

    private boolean isFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //AndroidBug5497Workaround.assistActivity(this);

        controlKeyboardLayout(loginLlLayout, moveLayout);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

//        loginLlLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int heightDiff = moveLayout.getRootView().getHeight() - moveLayout.getHeight();
//                if (heightDiff > 500){
//                    ViewCompat.animate(moveLayout)
//                            .translationY(-500)
//                            .setStartDelay(0)
//                            .setDuration(3000)
//                            .setInterpolator(new DecelerateInterpolator(1.2f))
//                            .start();
//
//
//                }else{
//                    ViewCompat.animate(moveLayout)
//                            .translationY(0)
//                            .setStartDelay(3000)
//                            .setDuration(3000)
//                            .setInterpolator(new DecelerateInterpolator(1.2f))
//                            .start();
//
//                }
//            }
//        });

//        if(isKeyboardShown(loginLlLayout)){
//            ViewCompat.animate(moveLayout)
//                    .translationY(-800)
//                    .setStartDelay(300)
//                    .setDuration(300)
//                    .setInterpolator(new DecelerateInterpolator(1.2f))
//                    .start();
//        }else{
//            ViewCompat.animate(moveLayout)
//                    .translationY(0)
//                    .setStartDelay(300)
//                    .setDuration(300)
//                    .setInterpolator(new DecelerateInterpolator(1.2f))
//                    .start();
//
//        }
    }

    /**
     * @param root             最外层布局
     * @param needToScrollView 要滚动的布局,就是说在键盘弹出的时候,你需要试图滚动上去的View,在键盘隐藏的时候,他又会滚动到原来的位置的布局
     */
    private void controlKeyboardLayout(final View root, final View needToScrollView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
//                loginLogo.setVisibility(isFirst ? View.VISIBLE : View.GONE);
//                isFirst = !isFirst;

                //获取当前界面可视部分
                LoginActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = LoginActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                if (heightDifference > 0){
                    loginLogo.setVisibility(View.GONE);
                    //needToScrollView.scrollTo(0, 100);

                    ViewCompat.animate(needToScrollView)
                            .translationY(-100)
                            .setStartDelay(0)
                            .setDuration(0)
                            .setInterpolator(new DecelerateInterpolator(1.2f))
                            .start();


                }else{
                    loginLogo.setVisibility(View.VISIBLE);

                    //needToScrollView.scrollTo(0, heightDifference);
                    ViewCompat.animate(needToScrollView)
                            .translationY(0)
                            .setStartDelay(0)
                            .setDuration(3000)
                            .setInterpolator(new DecelerateInterpolator(1.2f))
                            .start();

                }

            }
        });
    }
}
