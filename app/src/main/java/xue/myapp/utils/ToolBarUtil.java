package xue.myapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import xue.myapp.R;


public class ToolBarUtil
{

    /*上下文，创建view的时候需要用到*/
    private Context mContext;

    /*base view*/
    private FrameLayout mContentView;

    /*用户定义的view*/
    private View mUserView;

    /*toolbar*/
    private Toolbar mToolBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    private TextView rightText;//右边的文字
    private TextView centerText;//中间的文字
    private ImageView rightImg;//右边的图片

    /*
    * 两个属性
    * 1、toolbar是否悬浮在窗口之上
    * 2、toolbar的高度获取
    * */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolBarUtil(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
        /*初始化toolbar*/
        initToolBar();
    }

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);

    }

    private void initToolBar() {
        /*通过inflater获取toolbar的布局文件*/
        View toolbar = mInflater.inflate(R.layout.common_toolbar, mContentView);
        mToolBar = (Toolbar) toolbar.findViewById(R.id.toolbar);
        rightText= (TextView) toolbar.findViewById(R.id.right_text);
        centerText= (TextView) toolbar.findViewById(R.id.center_text);
        rightImg= (ImageView) toolbar.findViewById(R.id.right_img);
    }

    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
        /*获取主题中定义的toolbar的高度*/
        int toolBarSize = (int) typedArray.getDimension(1,(int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
        /*如果是悬浮状态，则不需要设置间距*/
        params.topMargin = overly ? 0 : toolBarSize;
        mContentView.addView(mUserView, params);

    }

    //设置右边的文字
    public void setRightText(String text){
        if (text !=null && !text.equals(""))
        {
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(text);
        }
    }

    //设置右边的图片
    public void setRightImg(Integer resId){
        if (resId!=null && !resId.equals(""))
        {
            rightImg.setVisibility(View.VISIBLE);
            rightImg.setImageResource(resId);
        }

    }

    //设置中间的文字
    public void setCenterText(String text){
        if (text != null && !text.equals("") )
        {
            centerText.setVisibility(View.VISIBLE);
            centerText.setText(text);
        }
    }

    //设置右侧文字单击事件
    public void setRightTextClick(View.OnClickListener rightTextClick){
        rightText.setOnClickListener(rightTextClick);
    }

    //设置右侧图片单击事件
    public void setRightImgClick(View.OnClickListener rightImgClick){
        rightImg.setOnClickListener(rightImgClick);
    }


    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}
