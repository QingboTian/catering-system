package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }
}
