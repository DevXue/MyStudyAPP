package xue.myapp.module.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;
import xue.myapp.Constants;
import xue.myapp.R;
import xue.myapp.common.interfaces.ResponseLintener;
import xue.myapp.common.ui.CommonFragment;
import xue.myapp.module.demo.adapter.CategoryAdapter;
import xue.myapp.module.demo.bean.CategoryData;
import xue.myapp.module.demo.model.CategoryModel;
import xue.myapp.module.main.IM.PushManager;
import xue.myapp.module.main.IM.PushWatcher;
import xue.myapp.module.main.IM.bean.Message;
import xue.myapp.utils.ArrayUtil;
import xue.myapp.utils.LogUtil;
import xue.myapp.utils.ToastUtil;

/**
 * 作者：薛
 * 时间：2017/5/9 14:47
 */
public class CategoryFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener,ResponseLintener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private Context context;
    private CategoryAdapter adapter;
    private CategoryData.Category category;
    private CategoryModel model;
    private int page=1;
    private int pageCount=20;
    private String title;
    public static CategoryFragment newInstance(String info) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, info);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        title=getArguments().getString(Constants.ARG_PARAM1);
        return view;
    }

    @Override
    protected void initData() {
        model=new CategoryModel(context);
        model.addResponseListener(this);
        setRefreshing(true); //开启下拉刷新
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout.setOnRefreshListener(this);
        adapter=new CategoryAdapter(R.layout.item_category_fragment,model.getCategoryList(),context);
        adapter.setOnLoadMoreListener(this,recyclerView);
        adapter.setNotDoAnimationCount(7);
        recyclerView.setAdapter(adapter);
        onRefresh();
        PushManager.getInstance(getContext()).addObserver(watcher1);

        Message message=new Message();
        message.setId("11");
        message.setType("ok");
        message.setStatus("ing");
        PushManager.getInstance(getContext()).sendMessage(message);

        PushManager.getInstance(getContext()).handlerPush("sssssssssssssssss");
    }


    PushWatcher watcher1=new PushWatcher(){
        @Override
        public void messageUpdate(Message message) {
            LogUtil.e("观察者"+message.toString());
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        model.removeResponseListener(this);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        page=1;
        loadData();
    }

    //上拉加载
    @Override
    public void onLoadMoreRequested() {
        page++;
        loadData();
    }

    //请求数据
    private void loadData(){
        Log.e("获取数据","loadData");
        model.requestCategoryData(pageCount,page,title,"");
    }


    public void setRefreshing(final boolean refreshing) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        },200);

       /* refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });*/
    }


    @Override
    public void onResponseSuccess(String code, Object result) {
        LogUtil.e(result);
        setRefreshing(false);
        if (!ArrayUtil.isEmptyList(model.getCategoryList())) {
            if (page == 1) { //如果页数等于1，那么说明是下拉刷新获取的数据
                adapter.setNewData(model.getCategoryList());
            }else{  //如果页数不是1 那么说明是上拉加载
                refreshLayout.setEnabled(false);
                adapter.addData(model.getCategoryList());
                adapter.loadMoreComplete();
            }
            refreshLayout.setEnabled(true);

        }else {
            ToastUtil.showViewToast(context,"暂无数据");
        }
    }

    @Override
    public void onResponseFailed(Response response, Exception e) {
        setRefreshing(false);
        //setRefreshing(false);
        ToastUtil.showViewToast(context,"网络异常,请检查网络设置");
        LogUtil.e(e.getMessage());
        adapter.loadMoreFail();
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.e("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.removeResponseListener(this);
    }

}
