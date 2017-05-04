package xue.myapp.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.Constants;
import xue.myapp.R;
import xue.myapp.adapter.GuideAdapter;
import xue.myapp.common.ui.CommonActivity;

@SuppressLint("InflateParams")
public class GuideActivity extends CommonActivity implements OnPageChangeListener {

    @Bind(R.id.guideViewPager)
    ViewPager guideViewPager;
    @Bind(R.id.guideLinearLayout)
    LinearLayout guideLinearLayout;

    private List<View> viewList;

    private ImageView[] dotsImageView;


    private GuideAdapter adapter;

    private TextView guideStartTextView;

    private int[] ids = {R.id.guideOneImageView, R.id.guideTwoImageView, R.id.guideThreeImageView};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
    }

    protected void setStatusBar(){
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,0);
    }

    @Override
    public void initView() {
        super.initView();
        LayoutInflater inflater = LayoutInflater.from(this);
        viewList = new ArrayList<View>();
        viewList.add(inflater.inflate(R.layout.guide_one, null));
        viewList.add(inflater.inflate(R.layout.guide_two, null));
        viewList.add(inflater.inflate(R.layout.guide_three, null));
        adapter = new GuideAdapter(viewList);
        guideViewPager = (ViewPager) findViewById(R.id.guideViewPager);
        guideViewPager.setAdapter(adapter);
        guideStartTextView = (TextView) viewList.get(2).findViewById(R.id.guideStartTextView);
        guideStartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置已经引导
                getAPP().getAppConfig().setBoolean(Constants.ISFIRST, false);
                String userid = getAPP().getAppConfig().getString(Constants.USERID, "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(HomePageActivity.class);
                }
                finish();
            }
        });
        guideViewPager.setOnPageChangeListener(this);

        dotsImageView = new ImageView[viewList.size()];
        for (int i = 0; i < viewList.size(); i++) {
            dotsImageView[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        for (int i = 0; i < ids.length; i++) {
            if (i == arg0)
                dotsImageView[i].setImageResource(R.drawable.guide_dot_focused);
            else {
                dotsImageView[i].setImageResource(R.drawable.guide_dot_normal);
            }
        }
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }
}
