package xue.myapp.module.demo.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xue.myapp.R;
import xue.myapp.common.adapter.recycleviewadapter.CommonRecyclerViewAdapter;
import xue.myapp.module.demo.bean.CategoryData;
import xue.myapp.utils.TimeUtil;

import static xue.myapp.MyAPP.context;

/**
 * 作者：薛
 * 时间：2017/5/10 14:28
 */
public class CategoryAdapter extends CommonRecyclerViewAdapter<CategoryData.Category> {
    public CategoryAdapter(int layoutResId, List<CategoryData.Category> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder h, CategoryData.Category o) {
        super.convert(h, o);
        h.setText(R.id.desTv,o.getDesc());
        h.setText(R.id.androidWhoTv,o.getWho());
        h.setText(R.id.timeTv, TimeUtil.getTranslateTime(o.getPublishedAt()));
        ImageView img=h.getView(R.id.androidImg);
        if (o.getImages()!=null) {
            img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(o.getImages().get(0)).asBitmap()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.icon_no_data)
                    .into(img);
        }else{
            img.setVisibility(View.GONE);
        }
    }

}
