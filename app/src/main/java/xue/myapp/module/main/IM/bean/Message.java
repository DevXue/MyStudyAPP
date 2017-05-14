package xue.myapp.module.main.IM.bean;

import java.io.Serializable;

/**
 * 作者： 薛
 * 创建时间:2017/5/14
 * 功能描述：
 */

public class Message implements Serializable{
    private String id;
    private String type;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
