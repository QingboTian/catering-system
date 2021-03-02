package cn.tianqb.service.impl;/**
 * @Description:
 * @Author tianqb
 * @Mail tianqingbo@tianqb.cn
 * @Date 2021/3/3 0:25
 * @Version v1.0
 */

import cn.tianqb.pojo.AccessToken;
import cn.tianqb.pojo.vo.LoginVO;
import cn.tianqb.service.SsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:25  
 * @version v1.0
 */
@Slf4j
@Service
public class SsoServiceImpl implements SsoService {

    @Override
    public AccessToken login(LoginVO loginVO) {
        return null;
    }

    @Override
    public void logout(LoginVO loginVO) {

    }

    @Override
    public Boolean registry(LoginVO loginVO) {
        return null;
    }
}
