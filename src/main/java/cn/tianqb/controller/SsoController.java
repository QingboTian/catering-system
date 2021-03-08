package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.enums.RoleEnum;
import cn.tianqb.exception.AppException;
import cn.tianqb.pojo.po.UserInfo;
import cn.tianqb.pojo.vo.LoginVO;
import cn.tianqb.service.SsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public WebResult login(HttpServletRequest request, @RequestBody LoginVO loginVO) {
        String token = request.getHeader("token");
        if (!ObjectUtils.isEmpty(token)) {
            Object object = request.getSession().getAttribute(token);
            if (!ObjectUtils.isEmpty(object)) {
                return WebResult.ok();
            }
        }
        return WebResult.ok(ssoService.login(request, loginVO, RoleEnum.USER));
    }

    @PostMapping("/admin/login")
    public WebResult adminLogin(HttpServletRequest request, @RequestBody LoginVO loginVO) {
        String token = request.getHeader("token");
        if (!ObjectUtils.isEmpty(token)) {
            Object object = request.getSession().getAttribute(token);
            if (!ObjectUtils.isEmpty(object)) {
                UserInfo userInfo = (UserInfo)object;
                if (RoleEnum.ADMINISTRATOR.getCode().equals(userInfo.getRoleId())) {
                    return WebResult.ok();
                } else {
                    throw new AppException("Access denied", HttpStatus.UNAUTHORIZED.value());
                }
            }
        }
        return WebResult.ok(ssoService.login(request, loginVO, RoleEnum.ADMINISTRATOR));
    }

    @PostMapping("/logout")
    public WebResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        request.getSession().removeAttribute(token);
        return WebResult.ok();
    }

    /**
     * 注册功能只针对用户开放
     * @param loginVO
     * @return
     */
    @PostMapping("/registry")
    public WebResult<Boolean> registry(@RequestBody LoginVO loginVO) {
        loginVO.setRoleId(RoleEnum.USER.getCode());
        return WebResult.ok(ssoService.registry(loginVO));
    }
}
