package cn.tianqb.service;

import cn.tianqb.enums.RoleEnum;
import cn.tianqb.pojo.AccessToken;
import cn.tianqb.pojo.po.UserInfo;
import cn.tianqb.pojo.query.UserQuery;
import cn.tianqb.pojo.vo.LoginVO;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:25
 * @version v1.0
 */
public interface SsoService {

    AccessToken login(HttpServletRequest request, LoginVO loginVO, RoleEnum roleEnum);

    void logout(LoginVO loginVO);

    Boolean registry(LoginVO loginVO);

    PageInfo<UserInfo> list(UserQuery query);

    Boolean delete(Integer id);
}
