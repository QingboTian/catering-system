package cn.tianqb.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 20:48
 */
public class AppException extends RuntimeException {

    private String message;

    @Getter
    @Setter
    private Integer code;


    public AppException() {
        super();
    }

    public AppException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
