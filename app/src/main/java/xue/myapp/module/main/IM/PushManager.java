package xue.myapp.module.main.IM;

import android.content.Context;

import xue.myapp.module.main.IM.bean.Message;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class PushManager {
    public  Context context;
    private static PushManager mInstance;

    public PushManager(Context context) {
        this.context=context;
    }

    public static PushManager getInstance(Context context){
        if (mInstance == null) {
            mInstance=new PushManager(context);
        }
        return mInstance;
    }


    public void handlerPush(String content){
        //TODO 解析content
        Message message=new Message();
        message.setStatus("sssssssssssssssssssssssssssssssss");
        PushChanaer.getInstance().notifyChanged(message);
    }

    public void sendMessage(Message message){
       /* Intent intent=new Intent(context,PushServers.class);
        intent.putExtra(Constants.MESSAGE,message);
        context.startService(intent);*/
        message.setType(MessageStatusType.ING);
        PushChanaer.getInstance().notifyChanged(message);
        message.setType(MessageStatusType.DONE);
        PushChanaer.getInstance().notifyChanged(message);
    }

    public void addObserver(PushWatcher pushWatcher){
        PushChanaer.getInstance().addObserver(pushWatcher);
    }


    public void removeObserver(PushWatcher pushWatcher){
        PushChanaer.getInstance().deleteObserver(pushWatcher);
    }

    public void removeObservers(){
        PushChanaer.getInstance().deleteObservers();
    }

}
