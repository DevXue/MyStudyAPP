package xue.myapp.module.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;
import xue.myapp.Constants;
import xue.myapp.R;
import xue.myapp.common.adapter.recycleviewadapter.CommonRecyclerViewAdapter;
import xue.myapp.common.interfaces.ResponseLintener;
import xue.myapp.common.ui.CommonFragment;
import xue.myapp.module.demo.bean.CategoryData;
import xue.myapp.module.demo.model.CategoryModel;
import xue.myapp.utils.ArrayUtil;
import xue.myapp.utils.LogUtil;
import xue.myapp.utils.TimeUtil;
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
    private String title;
    private Context context;
    private CommonRecyclerViewAdapter<CategoryData.Category> adapter;
    private CategoryData.Category category;
    private CategoryModel model;

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
        setRefreshing(true); //开启下拉刷新
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout.setOnRefreshListener(this);
        adapter=new CommonRecyclerViewAdapter(null);
        adapter.setOnLoadMoreListener(this,recyclerView);
        model=new CategoryModel(context);
        model.addResponseListener(this);
        loadData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //下拉刷新
    @Override
    public void onRefresh() {

    }

    //上拉加载
    @Override
    public void onLoadMoreRequested() {

    }


    private void loadData(){
        model.getCategoryData(title,"");
    }


    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }


    @Override
    public void onResponseSuccess(String code, Object result) {
        LogUtil.e(result);
        setRefreshing(false);
        if (!ArrayUtil.isEmptyList(model.getCategoryList())) {
            adapter=new CommonRecyclerViewAdapter<CategoryData.Category>(R.layout.item_category_fragment,model.getCategoryList()){
                @Override
                protected void convert(BaseViewHolder h, CategoryData.Category o) {
                    super.convert(h, o);
                    h.setText(R.id.desTv,o.getDesc());
                    h.setText(R.id.androidWhoTv,o.getWho());
                    h.setText(R.id.timeTv, TimeUtil.getTranslateTime(o.getPublishedAt()));
                    ImageView img=h.getView(R.id.androidImg);
                    if (o.getImages()!=null) {
                        Glide.with(context)
                                .load(o.getImages().get(0))
                                .placeholder(R.mipmap.icon_no_data)
                                .into(img);
                    }
                }
            };
            recyclerView.setAdapter(adapter);
        }else {
            ToastUtil.showViewToast(context,"暂无数据");
        }
    }

    @Override
    public void onResponseFailed(Response response, Exception e) {
        LogUtil.e(e.getMessage());
    }
}
