package cn.tianqb.utils;

import cn.tianqb.common.LoginContext;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/3 21:14
 */
public class WebHelper {
    public static String getUsername() {
        return LoginContext.get().getUsername();
    }
}
