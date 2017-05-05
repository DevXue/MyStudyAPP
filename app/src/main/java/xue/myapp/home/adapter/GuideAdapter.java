package xue.myapp.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import xue.myapp.utils.ArrayUtil;


public class GuideAdapter extends PagerAdapter {
	
	private List<View> viewList;
	
	public GuideAdapter(List<View> viewList) {
		this.viewList = viewList;
	}

	@Override
	public int getCount() {
		return ArrayUtil.isEmptyList(viewList) ? 0 : viewList.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object obj) {
		return v == obj;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(viewList.get(position));
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(viewList.get(position));
		return viewList.get(position);
	}

}
