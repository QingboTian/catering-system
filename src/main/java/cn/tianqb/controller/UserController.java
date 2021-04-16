package cn.tianqb.controller;

import cn.tianqb.common.LoginContext;
import cn.tianqb.common.WebResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 20:54
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/getUserInfo")
    public WebResult<User> getUserInfo() {
        return WebResult.ok(new User());
    }


}

@Data
class User implements Serializable {
    private static final long serialVersionUID = 521193560999546734L;
    private String username;
    private String phone;
    private String mail;
    private Integer roleId;

    User() {
        LoginContext loginContext = LoginContext.get();
        this.username = loginContext.getUsername();
        this.phone = loginContext.getPhone();
        this.mail = loginContext.getMail();
        this.roleId = loginContext.getRoleId();
    }
}
