package cn.tianqb.service;

import cn.tianqb.pojo.po.Administrator;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 20:55
 */
public interface AdminService {

    String login(Administrator administrator);

    Boolean logout(Administrator administrator);

    List<Administrator> findList();

    Boolean registry(Administrator administrator);

    Boolean delete(Integer id);

    Boolean update(Administrator administrator);
}
