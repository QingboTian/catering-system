package cn.tianqb.service;

import cn.tianqb.pojo.AccessToken;
import cn.tianqb.pojo.vo.LoginVO;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:25  
 * @version v1.0
 */
public interface SsoService {

    AccessToken login(LoginVO loginVO);

    void logout(LoginVO loginVO);

    Boolean registry(LoginVO loginVO);
}
