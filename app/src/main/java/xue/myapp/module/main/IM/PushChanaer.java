package xue.myapp.module.main.IM;


import java.util.Observable;

import xue.myapp.module.main.IM.bean.Message;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class PushChanaer extends Observable {
    private static PushChanaer mInstance;

    public static PushChanaer getInstance(){
        if (mInstance == null) {
            mInstance=new PushChanaer();
        }
        return mInstance;
    }


    public void notifyChanged(Message message) {
        setChanged();
        notifyObservers(message);
    }
}
