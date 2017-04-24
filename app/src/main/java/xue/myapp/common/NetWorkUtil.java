package xue.myapp.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import xue.myapp.MyAPP;

/**
 * 作者：薛
 * 时间：2017/4/24 15:45
 * 网络变化监听类
 */
public class NetWorkUtil  extends BroadcastReceiver{
    public static IntentFilter intentFilter;
    public static NetWorkUtil  netWorkUtil;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager= (ConnectivityManager) MyAPP.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable() ) {
            LogUtil.e("监听网络","网络可用");
        }else{
            ToastUtil.showViewToast(MyAPP.getContext(),"网络不可用，请检查网络设置");
        }
    }

    /**
     * 注册网络监听广播
     */
    public static void registerNetWorkBroadcastReceiver(){
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkUtil=new NetWorkUtil();
        MyAPP.getContext().registerReceiver(netWorkUtil,intentFilter);
    }

    public static void unRegisterNetWorkBroadcastReceiver(){
        MyAPP.getContext().unregisterReceiver(netWorkUtil);
    }
}
