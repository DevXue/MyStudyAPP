package xue.myapp.common.adapter.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：薛
 * 时间：2017/4/11 09:53
 */
public abstract class CommonListViewAdapter<T> extends BaseAdapter{


    // 子类调用
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    public CommonListViewAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    @Override
    public T getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position - 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);

        convent(holder, getItem(position), position);

        return holder.getConvertView();

    };

    public abstract void convent(ViewHolder holder, T t, int position);
}
