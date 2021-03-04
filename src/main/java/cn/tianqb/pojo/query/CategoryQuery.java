package cn.tianqb.pojo.query;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/3 21:01
 */
@Data
public class CategoryQuery extends BaseQuery {
    private static final long serialVersionUID = 5647267494566657593L;

    private Integer parentId;

    private Integer level;
}
