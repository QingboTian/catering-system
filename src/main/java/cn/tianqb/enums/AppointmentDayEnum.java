package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 13:26
 */
@AllArgsConstructor
@Getter
public enum AppointmentDayEnum {

    /**
     *
     */
    TODAY(1, "今天"),
    TOMORROW(2, "明天");

    private Integer code;
    private String desc;

    public static boolean contains(Integer code) {
        return Arrays.stream(AppointmentDayEnum.values())
                .map(AppointmentDayEnum::getCode)
                .collect(Collectors.toList())
                .contains(code);
    }
}
