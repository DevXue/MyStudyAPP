package xue.myapp.common.ui;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 作者：薛
 * 时间：2017/5/5 15:10
 */
public abstract class CommonFragment extends Fragment{


    // fragment是否显示/可见了
    protected boolean isVisible = false;



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
