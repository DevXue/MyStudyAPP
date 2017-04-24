package xue.myapp;

import android.content.Context;

import com.dvp.base.app.APP;

/**
 * 作者： 薛
 * 创建时间:2017/4/9
 * 功能描述：
 */

public class MyAPP extends APP {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){return context;}

}
