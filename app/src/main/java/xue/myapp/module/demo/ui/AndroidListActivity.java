package xue.myapp.module.demo.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;
import xue.myapp.R;
import xue.myapp.common.interfaces.ResponseLintener;
import xue.myapp.common.ui.CommonActivity;
import xue.myapp.module.demo.adapter.CategoryAdapter;
import xue.myapp.module.demo.bean.CategoryData;
import xue.myapp.module.demo.model.CategoryModel;
import xue.myapp.utils.ArrayUtil;
import xue.myapp.utils.LogUtil;
import xue.myapp.utils.ToastUtil;

public class AndroidListActivity extends CommonActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener,ResponseLintener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private String title;
    private CategoryAdapter adapter;
    private CategoryData.Category category;
    private CategoryModel model;
    private int page=1;
    private int pageCount=20;
    private boolean isFirst=true;


    @Override
    protected int setLayout() {
        return R.layout.activity_android_list;
    }

    @Override
    protected void onCreated() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        model=new CategoryModel(this);
        model.addResponseListener(this);
        setRefreshing(true); //开启下拉刷新
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setOnRefreshListener(this);
        adapter=new CategoryAdapter(R.layout.item_category_fragment,model.getCategoryList());
        adapter.setOnLoadMoreListener(this,recyclerView);
        adapter.setNotDoAnimationCount(7);
        recyclerView.setAdapter(adapter);
        onRefresh();
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

    @Override
    public void onResponseSuccess(String code, Object result) {
        LogUtil.e(result);
        setRefreshing(false);
        if (!ArrayUtil.isEmptyList(model.getCategoryList())) {
           /* if (isFirst) { //如果是首次加载，那么就刷新adapter 不需要setNewData();
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                isFirst=false;
                return;
            }*/

            if (page == 1) { //如果页数等于1，那么说明是下拉刷新获取的数据
                adapter.setNewData(model.getCategoryList());
            }else{  //如果页数不是1 那么说明是上拉加载
                refreshLayout.setEnabled(false);
                adapter.addData(model.getCategoryList());
                adapter.loadMoreComplete();
            }
            refreshLayout.setEnabled(true);

        }else {
            ToastUtil.showViewToast(this,"暂无数据");
        }
    }

    @Override
    public void onResponseFailed(Response response, Exception e) {

    }


    //请求数据
    private void loadData(){
        Log.e("获取数据","loadData");
        model.requestCategoryData(pageCount,page,"Android","");
    }


    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
