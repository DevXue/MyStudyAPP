package xue.myapp.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import xue.myapp.R;

/**
 * 作者：薛
 * 时间：2017/4/17 09:36
 * 吐司工具类:  单例Toast，加载布局Toast
 */
public class ToastUtil {

    private static Toast toast;

    /**
     * 自定义Toast，加载布局显示Toast
     */

    public static void  showViewToast(Context context, CharSequence text) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.textView1);
        textView.setText(text);
        if (toast == null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(v);
        }else{
            toast.show();
        }
        toast.show();
    }

    /**
     * 引用String.xml
     */
    public static void  showViewToast(Context context, int resId) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.textView1);
        textView.setText(resId);
        if (toast == null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(v);
        }else{
            toast.show();
        }
        toast.show();
    }


    /**
     * 只显示一次Toast，只改变内容,避免多次创建
     */
    public static void  showToast(Context context,CharSequence content){
        if (toast==null){
            toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else{
            toast.setText(content);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * 引用String.xml
     */
    public static void  showToast(Context context,String resId){
        if (toast==null){
            toast=Toast.makeText(context,resId,Toast.LENGTH_SHORT);
        }else{
            toast.setText(resId);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    /**
     * 普通的Toast
     */
    public static void makeText(Context context,String content){
        toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 引用String.xml
     */
    public static void makeText(Context context,int resId){
        toast=Toast.makeText(context,resId,Toast.LENGTH_SHORT);
        toast.show();
    }


    //取消Toast
    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
