package cn.tianqb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:10
 * @version v1.0
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    /**
     *
     */
    DELETED(-1, "已删除"),
    NOT_PAY(1, "未支付"),
    PAID(2, "已支付"),
    ONGOING(3, "进行中"),
    DONE(5, "已完成"),
    NOT_COMMENT(4, "待评价");

    private Integer code;
    private String desc;

    private static final Map<Integer, OrderStatusEnum> MAP = new HashMap<>(8);

    static {
        Arrays.stream(OrderStatusEnum.values()).forEach(orderStatusEnum -> MAP.put(orderStatusEnum.getCode(), orderStatusEnum));
    }

    public static OrderStatusEnum fromCode(Integer code) {
        return MAP.get(code);
    }
}
