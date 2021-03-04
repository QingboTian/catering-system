package cn.tianqb.exception;/**
 * @Description:
 * @Author tianqb
 * @Mail tianqingbo@tianqb.cn
 * @Date 2021/3/4 22:01
 * @Version v1.0
 */

/**
 * @author tianqingbo3
 * @date 2021/3/4 22:01  
 * @version v1.0
 */
public class NotLoginException extends RuntimeException {

    private Integer code;

    public NotLoginException() {
        super();
    }

    public NotLoginException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    protected NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
