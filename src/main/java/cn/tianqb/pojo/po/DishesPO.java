package cn.tianqb.pojo.po;

import cn.tianqb.pojo.BaseEntity;
import lombok.Data;

/**
 * @author tianqingbo3
 * @date 2021/3/2 23:59
 * @version v1.0
 */
@Data
public class DishesPO extends BaseEntity {

    private static final long serialVersionUID = 6841812007740358176L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private Double price;

    /**
     * 味道
     * 逗号分隔
     */
    private String taste;

    /**
     * 菜品图片Url
     */
    private String url;

    /**
     * 分类Id
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String category;

    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 单位
     */
    private String unit;
}
