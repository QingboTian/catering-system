package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.enums.RoleEnum;
import cn.tianqb.pojo.query.UserQuery;
import cn.tianqb.pojo.vo.LoginVO;
import cn.tianqb.service.SsoService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private SsoService ssoService;

    /**
     * 拉黑
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public WebResult delete(Integer id) {
        return WebResult.ok(ssoService.delete(id));
    }

    @GetMapping("/list")
    public WebResult<PageInfo> findList(UserQuery query) {
        return WebResult.ok(ssoService.list(query));
    }

    @GetMapping("/create")
    public WebResult<Boolean> create(LoginVO loginVO) {
        loginVO.setRoleId(RoleEnum.ADMINISTRATOR.getCode());
        return WebResult.ok(ssoService.registry(loginVO));
    }

}
