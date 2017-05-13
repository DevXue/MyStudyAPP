package xue.myapp.module.demo.model;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import xue.myapp.Api;
import xue.myapp.common.model.CommonModel;
import xue.myapp.module.demo.bean.CategoryData;
import xue.myapp.utils.ArrayUtil;
import xue.myapp.utils.ToastUtil;

/**
 * 作者：薛
 * 时间：2017/5/9 16:34
 */
public class CategoryModel  extends CommonModel{
    private Gson gson;
    private Context context;

    public CategoryModel(Context context) {
        super(context);
        this.context=context;
        gson=new Gson();
    }


    private List<CategoryData.Category> categoryList=null;

    public List<CategoryData.Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryData.Category> categoryList) {
        this.categoryList = categoryList;
    }

    //                              Android/10/1
    public void requestCategoryData(int pageCount,int page,String cacheTitle, final String apiCode){
        OkGo.get(Api.HOMEDATA+cacheTitle+"/"+pageCount+"/"+page)
                .cacheKey(cacheTitle)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson=new Gson();
                        CategoryData category=gson.fromJson(s,CategoryData.class);
                        if (!ArrayUtil.isEmptyList(category.getResults())) {
                            categoryList=new ArrayList<CategoryData.Category>();
                            categoryList.addAll(category.getResults());
                            onResponseSuccess(apiCode,"OK");
                        }else {
                            ToastUtil.showViewToast(context,"暂无数据");
                        }
                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                        if (!isInitCache) {
                            //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                             onSuccess(s, call, null);
                           // onResponseSuccess(apiCode,"缓存OK");
                            isInitCache = true;
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        onResponseFailed(response,e);
                    }
                });
    }
}
