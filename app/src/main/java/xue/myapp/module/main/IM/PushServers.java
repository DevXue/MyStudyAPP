package xue.myapp.module.main.IM;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import xue.myapp.Constants;
import xue.myapp.module.main.IM.bean.Message;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class PushServers extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message= (Message) intent.getSerializableExtra(Constants.MESSAGE);
        switch (message.getType()){//判断是什么类型
            case "文字":
                sendMessage(message);
                break;
            case "语音":
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage(Message message){

    }
}
