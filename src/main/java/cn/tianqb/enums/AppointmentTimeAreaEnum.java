package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 12:58
 */
@AllArgsConstructor
@Getter
public enum AppointmentTimeAreaEnum {

    /**
     *
     */
    TIME_8_10(1, "08:00:00"),
    TIME_10_12(2, "10:00:00"),
    TIME_12_14(3, "12:00:00"),
    TIME_14_16(4, "14:00:00"),
    TIME_16_18(5, "16:00:00"),
    TIME_18_20(6, "18:00:00"),
    TIME_20_22(7, "20:00:00");

    private Integer code;
    /**
     * 时间区间
     */
    private String startTime;

    private static Map<Integer, AppointmentTimeAreaEnum> MAP = new HashMap<>(8);

    static {
        Arrays.stream(AppointmentTimeAreaEnum.values()).forEach(e -> MAP.put(e.getCode(), e));
    }

    public static AppointmentTimeAreaEnum findByCode(Integer code) {
        return MAP.get(code);
    }
}
