package xue.myapp.common.interfaces;

import java.util.List;

/**
 * 作者： 薛
 * 创建时间:2017/4/9
 * 功能描述：运行时权限接口
 */

public interface PermissionLintener {

    void onGranted();//权限获取成功
    void onDenied(List<String> deniedPermisson );//获取权限失败喽

}
