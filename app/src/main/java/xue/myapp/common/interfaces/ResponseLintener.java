package xue.myapp.common.interfaces;

import okhttp3.Response;

/**
 * 作者：薛
 * 时间：2017/5/9 17:03
 */
public interface ResponseLintener {
       void onResponseSuccess(String code,Object result);
       void onResponseFailed(Response response, Exception e);
}
