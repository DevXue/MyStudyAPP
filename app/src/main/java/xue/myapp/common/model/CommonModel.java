package xue.myapp.common.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import xue.myapp.common.interfaces.ResponseLintener;

/**
 * 作者：薛
 * 时间：2017/5/9 17:02
 */
public class CommonModel implements ResponseLintener{

    protected Context context;

    public CommonModel(Context context){
        this.context=context;

    }


    protected List<ResponseLintener> responseLinteners = new ArrayList<ResponseLintener>();

    public void addResponseListener(ResponseLintener listener) {
        if (!responseLinteners.contains(listener)) {
            responseLinteners.add(listener);
        }
    }


    @Override
    public void onResponseSuccess(String code, Object result) {
        for (ResponseLintener iterable_element : responseLinteners) {
            iterable_element.onResponseSuccess(code, result);
        }
    }

    public void removeResponseListener(ResponseLintener listener) {
        responseLinteners.remove(listener);
    }

    @Override
    public void onResponseFailed(Response response, Exception e) {

    }

}
