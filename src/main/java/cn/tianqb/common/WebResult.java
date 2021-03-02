package cn.tianqb.common;

import lombok.Data;

@Data
public class WebResult<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    //构建其他状态的result对象
    public static <T> WebResult<T> build(Integer status, String msg, T data) {
        return new WebResult(status, msg, data);
    }

    public static <T> WebResult<T> ok(T data) {
        return new WebResult(data);
    }

    public static WebResult ok() {
        return new WebResult(null);
    }

    public WebResult() {

    }

    public static WebResult build(Integer status, String msg) {
        return new WebResult(status, msg, null);
    }

    public WebResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public WebResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
