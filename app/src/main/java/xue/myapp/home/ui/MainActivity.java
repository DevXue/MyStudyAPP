package xue.myapp.home.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.R;
import xue.myapp.common.ui.CommonActivity;
import xue.myapp.home.adapter.MyFragmentPagerAdapter;
import xue.myapp.module.demo.fragment.CategoryFragment;
import xue.myapp.utils.BottomNavigationViewHelper;


public class MainActivity extends CommonActivity {


    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @Bind(R.id.container)
    LinearLayout container;

    private MenuItem menuItem;



    @Override
    protected void initUI() {
        ButterKnife.bind(this);
        initViewPager();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    private void initViewPager(){
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        //viewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
     /*   viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/


        initContentFragment();
    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(CategoryFragment.newInstance("Android"));
        mFragmentList.add(CategoryFragment.newInstance("iOS"));
        mFragmentList.add(CategoryFragment.newInstance("拓展资源"));
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
    }
}
