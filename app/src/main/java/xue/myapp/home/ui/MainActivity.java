package xue.myapp.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import xue.myapp.R;
import xue.myapp.common.ui.CommonActivity;
import xue.myapp.home.adapter.ViewPagerAdapter;
import xue.myapp.home.fragment.SimpleFragment;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavigationViewPager();
    }

    private void initNavigationViewPager(){
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


        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SimpleFragment.newInstance("首页"));
        adapter.addFragment(SimpleFragment.newInstance("功能"));
        adapter.addFragment(SimpleFragment.newInstance("通知"));
        viewPager.setAdapter(adapter);
    }
}
