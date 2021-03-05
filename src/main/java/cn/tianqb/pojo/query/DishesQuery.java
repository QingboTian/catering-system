package cn.tianqb.pojo.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/3 21:05
 */
@Data
public class DishesQuery extends BaseQuery {

    private static final long serialVersionUID = -5333752285339624510L;

    private BigDecimal lowPrice;

    private BigDecimal highPrice;

    private Integer categoryId;

}
