package cn.tianqb.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/4 20:25
 */
@Data
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = -3590555836962327713L;

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer id;

    private String name;
}
