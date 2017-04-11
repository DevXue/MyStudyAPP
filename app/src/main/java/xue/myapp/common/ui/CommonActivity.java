package xue.myapp.common.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.dvp.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import xue.myapp.common.interfaces.PermissionLintener;

/**
 * 作者： 薛
 * 创建时间:2017/4/9
 * 功能描述：
 */

public class CommonActivity extends BaseActivity{

    private PermissionLintener lintener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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





