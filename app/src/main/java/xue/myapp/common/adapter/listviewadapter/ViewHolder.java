package xue.myapp.common.adapter.listviewadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用ViewHolder
 */
public class ViewHolder {

	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	// 创建ViewHolder
	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

		if (convertView == null) {

			return new ViewHolder(context, parent, layoutId, position);

		} else {
			// 加入队列
			ViewHolder holder = (ViewHolder) convertView.getTag();

			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 
	 * 通过ViewID获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);

		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 设置TextView的背景
	 * 
	 * @param viewId
	 * @return
	 */
	public ViewHolder setTextbackground(int viewId, String color) {
		TextView tv = getView(viewId);
		tv.setBackgroundColor(Color.parseColor(color));
		return this;
	}

	/**
	 * 设置TextView的值
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * 设置TextView的值
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, Spanned text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * 设置TextView的值
	 * 
	 * @param text
	 * @return
	 */
	public ViewHolder setTextView(TextView textview, String text) {
		textview.setText(text);
		return this;
	}

	public ViewHolder setTextViewLine(TextView textview, String text) {
		textview.setText(text);
		textview.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return this;
	}

	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView iv = getView(viewId);
		iv.setImageResource(resId);
		return this;
	}

	/**
	 * 设置BitMap
	 * 
	 * @param viewId
	 * @param bitmap
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView iv = getView(viewId);
		iv.setImageBitmap(bitmap);
		return this;
	}

	public ViewHolder setImageDrawable(int viewId, Drawable dble) {
		ImageView iv = getView(viewId);
		iv.setImageDrawable(dble);
		return this;
	}

	/**
	 * 设置颜色
	 * 
	 * @param viewId
	 * @param color
	 * @return
	 */
	public ViewHolder setTextColor(int viewId, String color) {
		TextView iv = getView(viewId);
		iv.setTextColor(Color.parseColor(color));
		return this;
	}

	/**
	 * 设置CheckBox的
	 * 
	 * @param viewId
	 * @return
	 */
	public ViewHolder setChecked(int viewId, Boolean checked) {
		CheckBox cb = getView(viewId);
		cb.setChecked(checked);
		return this;
	}

	/**
	 * 设置CheckBox的
	 * 
	 * @param viewId
	 * @return
	 */
	public ViewHolder setCheckeable(int viewId, Boolean able) {
		CheckBox cb = getView(viewId);
		cb.setClickable(able);
		return this;
	}

}
