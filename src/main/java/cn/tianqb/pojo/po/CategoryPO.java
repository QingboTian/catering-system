package cn.tianqb.pojo.po;

import cn.tianqb.pojo.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:22
 */
@Data
public class CategoryPO extends BaseEntity {
    private static final long serialVersionUID = 2990136437783125050L;

    /**
     * 名称
     */
    private String name;

    /**
     * 父Id
     */
    private Integer parentId;

    /**
     * 备注
     */
    private String remark;

    private Integer level;

    private List<CategoryPO> children;
}
