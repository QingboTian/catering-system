package cn.tianqb.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:22
 * @version v1.0
 */
@Data
public class LoginVO {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 生日
     */
    private Date birthday;
}
