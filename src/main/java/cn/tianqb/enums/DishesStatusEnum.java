package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/20 15:59
 */
@AllArgsConstructor
@Getter
public enum DishesStatusEnum {

    /**
     *
     */
    DELETED(-1, "已删除"),
    NORMAL(1, "正常，未上架"),
    ONLINE(2, "已上架"),
    OFFLINE(3, "已下架");


    private Integer code;
    private String desc;
}
