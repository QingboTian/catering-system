package cn.tianqb.constant;

import cn.tianqb.utils.WebHelper;

import java.util.StringJoiner;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 13:17
 */
public class CacheKey {

    public final static String SEPARATOR = ":";

    public static final String APPOINTMENT = "appointment";

    public static String buildAppointmentKey(String day, Integer roomId, String timeArea, Boolean user) {
        StringJoiner key = new StringJoiner(SEPARATOR);
        key.add(APPOINTMENT).add(day).add(roomId.toString()).add(timeArea);
        if (user) {
            key.add(WebHelper.getUsername());
        }
        return key.toString();
    }
}
