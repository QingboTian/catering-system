package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/6 15:22
 */
@RestController
@Slf4j
@RequestMapping("/index")
public class IndexController {

    @GetMapping({"/index", "/"})
    public WebResult index() {
        return WebResult.ok("Welcome to the system");
    }

}
