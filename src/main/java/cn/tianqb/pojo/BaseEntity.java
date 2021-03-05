package cn.tianqb.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tianqingbo3
 * @date 2021/3/2 23:51
 * @version v1.0
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1898664848475883610L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 状态
     */
    private Integer status;
}
