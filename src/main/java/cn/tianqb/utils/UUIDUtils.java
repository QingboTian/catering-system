package cn.tianqb.utils;

public class UUIDUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

}
