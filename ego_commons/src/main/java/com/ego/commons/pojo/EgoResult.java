package com.ego.commons.pojo;

public class EgoResult {
    // 页面 200 成功，400，500失败
    private int status;
    // 服务器向客户端发送的数据
    private Object data;
    // 服务端向客户端发送的消息
    private String msg;

    public static EgoResult ok(){
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setMsg("ok");
        return er;
    }

    public static EgoResult ok(Object data) {
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setData(data);
        er.setMsg("ok");
        return er;
    }

    public static EgoResult ok(String msg) {
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setMsg(msg);
        return er;
    }
    public static EgoResult error(String msg) {
        EgoResult er = new EgoResult();
        er.setStatus(400);
        er.setMsg(msg);
        return er;
    }

    @Override
    public String toString() {
        return "EgoResult{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EgoResult egoResult = (EgoResult) o;

        if (status != egoResult.status) return false;
        if (data != null ? !data.equals(egoResult.data) : egoResult.data != null) return false;
        return msg != null ? msg.equals(egoResult.msg) : egoResult.msg == null;
    }

    @Override
    public int hashCode() {
        int result = status;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
