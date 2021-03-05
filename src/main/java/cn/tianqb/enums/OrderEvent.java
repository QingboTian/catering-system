package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/5 20:44
 */
@AllArgsConstructor
@Getter
public enum OrderEvent {

    /**
     *
     */
    PAYING(1, "支付中"),
    ORDERING(2, "下单中"),
    CANCEL_ORDER(3, "取消订单"),
    COMMENT(4, "评论订单"),
    DELETE(5, "删除");


    private Integer code;
    private String desc;

}
