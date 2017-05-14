package xue.myapp.module.main.IM;

import android.test.AndroidTestCase;

import xue.myapp.module.main.IM.bean.Message;
import xue.myapp.utils.LogUtil;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class Test extends AndroidTestCase{
    PushWatcher watcher1=new PushWatcher(){
        @Override
        public void messageUpdate(Message message) {
            LogUtil.e(message.toString());
        }
    };


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    PushManager.getInstance(getContext()).addObserver(watcher1);
    }

    public void testMsg() throws Exception{
        Message message=new Message();
        message.setId("11");
        message.setType("ok");
        message.setStatus("ing");
        PushManager.getInstance(getContext()).sendMessage(message);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        PushManager.getInstance(getContext()).removeObserver(watcher1);
    }
}
