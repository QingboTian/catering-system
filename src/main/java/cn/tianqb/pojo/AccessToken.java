package cn.tianqb.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:30  
 * @version v1.0
 */
@Data
public class AccessToken implements Serializable {

    private static final long serialVersionUID = -2234759499980712452L;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Long expire;
}
