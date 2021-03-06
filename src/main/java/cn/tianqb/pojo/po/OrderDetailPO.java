package cn.tianqb.pojo.po;

import cn.tianqb.pojo.BaseEntity;
import lombok.Data;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:11
 * @version v1.0
 */
@Data
public class OrderDetailPO extends BaseEntity {
    private static final long serialVersionUID = -2228220604089680014L;

    /**
     * 菜品Id
     */
    private Integer dishesId;

    /**
     * 菜品个数
     */
    private Integer total;

    /**
     * 明细总价格
     */
    private Double totalPrice;

    /**
     * 明细总价
     */
    private Double dishesPrice;

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 菜品名称
     */
    private String dishesName;

    /**
     * 菜品图片URL
     */
    private String url;
}
