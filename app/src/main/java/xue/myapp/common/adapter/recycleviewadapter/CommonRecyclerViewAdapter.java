package xue.myapp.common.adapter.recycleviewadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 作者：薛
 * 时间：2017/4/11 10:57
 */
public class CommonRecyclerViewAdapter<T>  extends BaseQuickAdapter<T,BaseViewHolder> {

    public CommonRecyclerViewAdapter(int layoutResId, List<T> data)
    {
        super(layoutResId, data);
        initAdapter();
    }

    public CommonRecyclerViewAdapter(List<T> data)
    {
        super(data);
        initAdapter();
    }

    protected void convert(BaseViewHolder h, T o)
    {

    }

    //这里设置了不让某几个item不显示动画以及设置动画效果，避免了在每一个类都写重复操作！
    private void initAdapter(){
        setNotDoAnimationCount(7);//设置前接个Item不显示动画
        openLoadAnimation(BaseQuickAdapter.EMPTY_VIEW);//设置动画样式
    }

}
