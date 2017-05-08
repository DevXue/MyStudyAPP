package xue.myapp.home.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.Constants;
import xue.myapp.R;
import xue.myapp.common.ui.CommonActivity;

import static xue.myapp.Constants.SPLASH_ARRAY;

/**
 * 欢迎页
 */
public class SplashActivity extends CommonActivity {

    @Bind(R.id.iv_entry)
    ImageView ivEntry;
    private static final int ANIMATION_DURATION = 3000;
    private static final float SCALE_END = 1.13F;
    private boolean isFirstIn = false;//是否首次打开APP

    @Override
    protected void onCreated() {
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    public void initView() {
        Random r = new Random(SystemClock.elapsedRealtime());
        ivEntry.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        imageAnimate();
    }

    //不显示ToolBar
    @Override
    protected boolean isShowToolBar() {
        return false;
    }
    private void imageAnimate(){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivEntry, View.SCALE_X, 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivEntry, View.SCALE_Y, 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //这里开始逻辑判断 该跳转何方···
                isFirstIn=getAPP().getAppConfig().getBoolean(Constants.ISFIRST,true);
                if (!isFirstIn) {
                    String userid = getAPP().getAppConfig().getString(Constants.USERID,"");//获取用户id
                    if (TextUtils.isEmpty(userid)) {
                        //如果用户ID为空，说明没有登录过，那么进入登录页
                        //goLogin();
                        goHome();
                    } else {
                        // 登录过就进入页面
                        goHome();
                    }
                } else {
                    //说明是首次启动进入引导页
                    goGuide();
                }
            }
        });
    }


    private void goHome() {
        startActivity(MainActivity.class);
        finish();
    }

    private void goLogin() {
        startActivity(LoginActivity.class);
        finish();
    }

    private void goGuide() {
        startActivity(GuideActivity.class);
        finish();
    }


}
