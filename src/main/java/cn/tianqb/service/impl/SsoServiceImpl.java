package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.enums.RoleEnum;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.exception.AppException;
import cn.tianqb.mapper.UserInfoMapper;
import cn.tianqb.pojo.AccessToken;
import cn.tianqb.pojo.example.UserInfoExample;
import cn.tianqb.pojo.po.UserInfo;
import cn.tianqb.pojo.query.UserQuery;
import cn.tianqb.pojo.vo.LoginVO;
import cn.tianqb.service.SsoService;
import cn.tianqb.utils.MD5Utils;
import cn.tianqb.utils.UUIDUtils;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * @author tianqingbo3
 * @date 2021/3/3 0:25
 * @version v1.0
 */
@Slf4j
@Service
public class SsoServiceImpl implements SsoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * H
     */
    private final Long EXPIRE = 2L;

    @Override
    public AccessToken login(HttpServletRequest request, LoginVO loginVO, RoleEnum roleEnum) {
        PageHelper.startPage(1, 1);
        Assert.isNull(loginVO, HttpStatus.UNAUTHORIZED.value(), "login object is null");
        Assert.isNull(loginVO.getUsername(), HttpStatus.UNAUTHORIZED.value(), "username is empty");
        Assert.isNull(loginVO.getPassword(), HttpStatus.UNAUTHORIZED.value(), "password is empty");

        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUsernameEqualTo(loginVO.getUsername());
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new AppException("not registry", HttpStatus.UNAUTHORIZED.value());
        }

        /**
         * 登录用户判断
         */
//        if (roleEnum.equals(RoleEnum.ADMINISTRATOR)) {
//            Assert.notTrue(list.get(0).getRoleId().equals(RoleEnum.ADMINISTRATOR.getCode()),
//                    HttpStatus.UNAUTHORIZED.value(), "Access denied");
//        }
//        if (roleEnum.equals(RoleEnum.USER)) {
//            Assert.notTrue(list.get(0).getRoleId().equals(RoleEnum.USER.getCode()),
//                    HttpStatus.UNAUTHORIZED.value(), "Access denied");
//        }

        Assert.notTrue(StatusEnum.NORMAL.getCode().equals(list.get(0).getStatus()), HttpStatus.UNAUTHORIZED.value(),
                "It is currently on the blacklist");
        String password = MD5Utils.md5(loginVO.getPassword());
        Assert.notTrue(password.equals(list.get(0).getPassword()), HttpStatus.UNAUTHORIZED.value(), "Login password error");

        AccessToken accessToken = buildToken();
        HttpSession session = request.getSession();
        session.setAttribute(accessToken.getToken(), list.get(0));
        session.setMaxInactiveInterval(EXPIRE.intValue() * 60 * 60);
        return accessToken;
    }

    @Override
    public void logout(LoginVO loginVO) {
        // empty
    }

    @Override
    public Boolean registry(LoginVO loginVO) {
        checkParamsByRole(loginVO);
        checkExist(loginVO);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(loginVO, userInfo);
        userInfo.setPassword(MD5Utils.md5(loginVO.getPassword()));
        userInfo.setStatus(StatusEnum.NORMAL.getCode());
        return userInfoMapper.insertSelective(userInfo) == 1;
    }

    @Override
    public PageInfo<UserInfo> list(UserQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        UserInfoExample example = new UserInfoExample();
        example.setOrderByClause("created desc");
        UserInfoExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getStatus())) {
            criteria.andStatusEqualTo(query.getStatus());
        }
        if (!ObjectUtils.isEmpty(query.getId())) {
            criteria.andIdEqualTo(query.getId());
        }
        if (!ObjectUtils.isEmpty(query.getUsername())) {
            criteria.andUsernameEqualTo(query.getUsername());
        }
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public Boolean delete(Integer id) {
        Assert.isNull(id, "id is empty");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        if (!ObjectUtils.isEmpty(userInfo)) {
            userInfo.setModifier(WebHelper.getUsername());
            userInfo.setStatus(StatusEnum.DELETED.getCode());
            return userInfoMapper.updateByPrimaryKey(userInfo) == 1;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验账号是否存在
     * @param loginVO
     */
    private void checkExist(LoginVO loginVO) {
        PageHelper.startPage(1, 1);
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria username = example.createCriteria().andUsernameEqualTo(loginVO.getUsername());
        if (!ObjectUtils.isEmpty(loginVO.getMail())) {
            UserInfoExample.Criteria mail = example.createCriteria().andMailEqualTo(loginVO.getMail());
            example.or(mail);
        }
        if (!ObjectUtils.isEmpty(loginVO.getPhone())) {
            UserInfoExample.Criteria phone = example.createCriteria().andPhoneEqualTo(loginVO.getPhone());
            example.or(phone);
        }
        example.or(username);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        Assert.notTrue(CollectionUtils.isEmpty(list), "The current user already exists");
    }

    /**
     * 校验注册参数 by roleId
     * @param loginVO
     */
    private void checkParamsByRole(LoginVO loginVO) {
        Assert.isNull(loginVO, "login object is null");
        Integer roleId = loginVO.getRoleId();
        Assert.isNull(roleId, "roleId is null");
        Assert.isNull(loginVO.getUsername(), "username is empty");
        Assert.isNull(loginVO.getPassword(), "password is empty");
        if (RoleEnum.ADMINISTRATOR.getCode().equals(roleId)) {
            // empty
        }
        if (RoleEnum.USER.getCode().equals(roleId)) {
            Assert.isNull(loginVO.getPhone(), "phone is empty");
        }
    }

    private AccessToken buildToken() {
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(UUIDUtils.uuid());
        LocalDateTime expire = LocalDateTime.now().plusHours(EXPIRE);
        accessToken.setExpire(expire.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        return accessToken;
    }
}
