package xue.myapp.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.R;
import xue.myapp.common.ui.CommonActivity;

public class SplashActivity extends CommonActivity {

    private static final int ANIMATION_DURATION = 3000;
    private static final float SCALE_END = 1.13F;

    private static final int[] SPLASH_ARRAY = {
            R.drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,
            R.drawable.splash5,
            R.drawable.splash6,
            R.drawable.splash7,
            R.drawable.splash8,
            R.drawable.splash9,
            R.drawable.splash10,
            R.drawable.splash11,
            R.drawable.splash12,
            R.drawable.splash13,
            R.drawable.splash14,
            R.drawable.splash15,
            R.drawable.splash16,
    };

    @Bind(R.id.iv_entry)
    ImageView ivEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }


    private void init(){
        Random r = new Random(SystemClock.elapsedRealtime());
        ivEntry.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        imageAnimate();
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
               // TestToolBarActivity.start(SplashActivity.this);
                startActivity(MainActivity.class);
                SplashActivity.this.finish();
            }
        });
    }

}
