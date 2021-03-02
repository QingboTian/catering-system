package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:34  
 * @version v1.0
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {

    ADMINISTRATOR(1, "管理员"),
    USER(2, "普通用户"),
    VIP(3, "会员");

    private Integer code;
    private String desc;

}
