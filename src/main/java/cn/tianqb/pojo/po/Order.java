package cn.tianqb.pojo.po;

import cn.tianqb.pojo.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:23
 */
@Data
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1403793888331845973L;

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 订单电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    private List<OrderDetailPO> orderDetails;
}
