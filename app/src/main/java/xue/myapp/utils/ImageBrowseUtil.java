package xue.myapp.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import xue.myapp.Constants;
import xue.myapp.common.photoview.ViewBigImageActivity;

/**
 * 作者：薛
 * 时间：2017/4/12 16:32
 */
public class ImageBrowseUtil {
    /**
     * 点击查看大图
     */
    public static void browseImage(Context context, ArrayList<String> imageUrl, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.SELET, 2);// 2,大图显示当前页数，1,头像，不显示页数
        bundle.putInt(Constants.CODE, position);//第几张
        bundle.putStringArrayList(Constants.IMAGEURI, imageUrl);
        Intent intent = new Intent(context, ViewBigImageActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
