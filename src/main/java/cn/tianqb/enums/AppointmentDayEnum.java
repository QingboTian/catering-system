package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}
