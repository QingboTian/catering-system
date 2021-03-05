package cn.tianqb.pojo.query;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/3 21:07
 */
@Data
public class OrderQuery extends BaseQuery{
    private static final long serialVersionUID = -7151243640130642140L;

    private Integer status;

    private String orderId;



}
