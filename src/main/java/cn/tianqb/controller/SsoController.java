package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.AccessToken;
import cn.tianqb.pojo.vo.LoginVO;
import cn.tianqb.service.SsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:20
 * @version v1.0
 */
@RestController
@Slf4j
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @PostMapping("/login")
    public WebResult<AccessToken> login(LoginVO loginVO) {
        return WebResult.ok(ssoService.login(loginVO));
    }

    @PostMapping("/logout")
    public WebResult logout(LoginVO loginVO) {
        ssoService.logout(loginVO);
        return WebResult.ok();
    }

    @PostMapping("/registry")
    public WebResult<Boolean> registry(LoginVO loginVO) {
        return WebResult.ok(ssoService.registry(loginVO));
    }
}
