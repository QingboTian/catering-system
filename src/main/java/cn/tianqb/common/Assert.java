package cn.tianqb.common;

import cn.tianqb.exception.AppException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/16 10:56
*/
public class Assert {

    public static void isNull(Object obj, String message) throws AppException {
        if (ObjectUtils.isEmpty(obj)) {
            throw new AppException(message, HttpStatus.FORBIDDEN.value());
        }
    }

    public static void isNull(Object obj, Integer code, String message) throws AppException {
        if (ObjectUtils.isEmpty(obj)) {
            throw new AppException(message, code);
        }
    }

    public static void isTrue(Boolean exp, Integer code, String message) throws AppException {
        if (exp) {
            throw new AppException(message, code);
        }
    }

    public static void isTrue(Boolean exp, String message) throws AppException {
        if (exp) {
            throw new AppException(message, HttpStatus.FORBIDDEN.value());
        }
    }

    public static void notTrue(Boolean exp, Integer code, String message) throws AppException {
        if (!exp) {
            throw new AppException(message, code);
        }
    }

    public static void isEmpty(String str, String message) throws AppException {
        if (StringUtils.isEmpty(str)) {
            throw new AppException(message, HttpStatus.FORBIDDEN.value());
        }
    }

}
