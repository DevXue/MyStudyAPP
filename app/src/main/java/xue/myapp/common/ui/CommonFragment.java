package xue.myapp.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 作者：薛
 * 时间：2017/5/5 15:10
 */
public abstract class CommonFragment extends Fragment{

    protected LinearLayout loadDataAnim;//加载数据动画
    protected LinearLayout loadDataFaild; //加载失败


    // fragment是否显示/可见了
    protected boolean isVisible = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(setContentLayout(), null);
        return ll;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();//可见
        } else {
            isVisible = false;
            onInvisible();//不可见
        }
    }


    /**
     * 设置布局布局
     */
    public abstract int setContentLayout();

    protected void onInvisible() {}

    /**
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {}
    protected void onVisible() {loadData();}
    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

}
