package xue.myapp.common.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.dvp.base.activity.BaseActivity;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import xue.myapp.R;
import xue.myapp.common.interfaces.PermissionLintener;
import xue.myapp.utils.ToolBarUtil;

/**
 * 作者： 薛
 * 创建时间:2017/4/9
 * 功能描述：Activity通用父类，一般需求继承此类即可
 */

public abstract class CommonActivity extends BaseActivity{

    private PermissionLintener lintener;
    private ToolBarUtil mToolBarHelper ;
    private Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initView(){};
    public void initData(){};
    @Override
    public void setContentView(int layoutResID) {
        if (isShowToolBar())
        {
            mToolBarHelper = new ToolBarUtil(this,layoutResID) ;
            toolbar = mToolBarHelper.getToolBar() ;
            setContentView(mToolBarHelper.getContentView());
            //*把 toolbar 设置到Activity 中*//*
            setSupportActionBar(toolbar);
            //*自定义的一些操作*//*
            onCreateCustomToolBar(toolbar) ;
        }else {
            super.setContentView(layoutResID);
        }
        setStatusBar();
    }

    //设置toolbar关闭界面
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //设置沉浸式状态栏
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }

    //设置中间的文字
    public void setCenterText(String text){
        setTitle("");
        mToolBarHelper.setCenterText(text);
    }

    //设置右边的文字
    public void setRightText(String text){
        mToolBarHelper.setRightText(text);
    }

    //设置右边的图片
    public void setRightImg(int resId){
        mToolBarHelper.setRightImg(resId);
    }

    //设置右侧文字单击事件
    public void setRightTextClick(View.OnClickListener rightTextClick){
        mToolBarHelper.setRightTextClick(rightTextClick);
    }

    //设置图片右侧单击事件
    public void setRightImgClick(View.OnClickListener rightImgClick){
        mToolBarHelper.setRightImgClick(rightImgClick);
    }


    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    /**
     *向子类提供一个可调用的方法，来控制是否显示ToolBar
     * true:代表子类用的就是父类的ToolBar
     * false:意思就是子类不需要父类的ToolBar,子类自己去实现吧
     */
    protected boolean isShowToolBar(){
        return true;
    }


    /**
     *因为Android6.0之后加入了运行时权限机制（高危权限大致分为9组24个），这里封装了运行时权限操作
     * 参数1：一次请求多少个权限
     * 参数2：请求后利用接口回调执行其他操作
     */
    public void requestRuntimepermission(String[] permissions,PermissionLintener permissionLintener){
        this.lintener=permissionLintener;
        List<String> permissionList=new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }

        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),1);
        }else {
            //做某事吧
            lintener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0) {
                    List<String> deniedPermissions=new ArrayList<>();
                    for (int i = 0; i <grantResults.length ; i++) {
                        int grantResult=grantResults[i];
                        String permission=permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {//权限被拒绝，告诉用户那些权限被拒绝
                            deniedPermissions.add(permission);
                        }
                    }


                    if (deniedPermissions.isEmpty()) {//如果被拒绝权限集合为空，说明用户接受了所有权限
                        lintener.onGranted();
                    }else {
                        lintener.onDenied(deniedPermissions);
                    }
                    
                }
                break;
        }
    }
}





