package cn.tianqb.service.impl;

import cn.tianqb.pojo.po.Administrator;
import cn.tianqb.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 20:56
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public String login(Administrator administrator) {
        return null;
    }

    @Override
    public Boolean logout(Administrator administrator) {
        return null;
    }

    @Override
    public List<Administrator> findList() {
        return null;
    }

    @Override
    public Boolean registry(Administrator administrator) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Administrator administrator) {
        return null;
    }
}
