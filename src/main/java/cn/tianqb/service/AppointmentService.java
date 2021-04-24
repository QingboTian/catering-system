package cn.tianqb.service;

import cn.tianqb.pojo.po.AppointmentPO;
import cn.tianqb.pojo.vo.AppointmentVO;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 14:49
 */
public interface AppointmentService {
    Boolean appointment(AppointmentVO vo);

    Boolean cancel(Integer id);

    List<AppointmentPO> list();

    List<AppointmentVO> roomList(Integer day, Integer roomId);
}
