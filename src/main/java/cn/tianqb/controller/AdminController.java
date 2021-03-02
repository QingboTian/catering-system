package cn.tianqb.controller;

import cn.tianqb.common.PageBean;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.Administrator;
import cn.tianqb.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 20:53
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public WebResult login(Administrator administrator) {
        return null;
    }

    @PostMapping("/logout")
    public WebResult logout(Administrator administrator) {
        return null;
    }

    @PostMapping("/registry")
    public WebResult registry(Administrator administrator) {
        return null;
    }

    @PostMapping("/delete")
    public WebResult delete(Administrator administrator) {
        return null;
    }

    @GetMapping("/list")
    public WebResult<PageBean> findList() {
        return null;
    }

}
