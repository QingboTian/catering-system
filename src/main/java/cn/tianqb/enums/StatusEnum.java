package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianqingbo3
 * @date 2021/3/2 23:55  
 * @version v1.0
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    NORMAL(1, "正常"),
    DELETED(-1, "已删除");

    private Integer code;
    private String desc;
}
