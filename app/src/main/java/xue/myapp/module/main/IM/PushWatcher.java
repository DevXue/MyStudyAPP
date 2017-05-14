package xue.myapp.module.main.IM;

import java.util.Observable;
import java.util.Observer;

import xue.myapp.module.main.IM.bean.Message;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class PushWatcher implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Message){
             messageUpdate((Message)o);
        }
    }


    public void messageUpdate(Message message){

    }

}
