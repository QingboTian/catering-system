package cn.tianqb.pojo.query;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/7 0:43
 */
@Data
public class UserQuery extends BaseQuery {
    private static final long serialVersionUID = 7940006800896994015L;

    private Integer status;

    private String username;
}
