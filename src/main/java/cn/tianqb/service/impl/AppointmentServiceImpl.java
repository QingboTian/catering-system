package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.enums.AppointmentDayEnum;
import cn.tianqb.enums.AppointmentTimeAreaEnum;
import cn.tianqb.enums.RoleEnum;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.exception.AppException;
import cn.tianqb.mapper.AppointmentMapper;
import cn.tianqb.mapper.RoomMapper;
import cn.tianqb.pojo.example.AppointmentExample;
import cn.tianqb.pojo.po.AppointmentPO;
import cn.tianqb.pojo.po.RoomPO;
import cn.tianqb.pojo.vo.AppointmentVO;
import cn.tianqb.service.AppointmentService;
import cn.tianqb.utils.DateUtils;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 14:50
 */
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    synchronized public Boolean appointment(AppointmentVO vo) {
        paramsCheck(vo);
        AppointmentPO appointment = build(vo);
        check(appointment);
        return appointmentMapper.insertSelective(appointment) == 1;
    }

    @Override
    public Boolean cancel(Integer id) {
        AppointmentPO appointment = new AppointmentPO();
        appointment.setId(id);
        appointment.setCreator(WebHelper.getUsername());
        appointment.setStatus(StatusEnum.DELETED.getCode());
        return appointmentMapper.updateByPrimaryKeySelective(appointment) == 1;
    }

    @Override
    public List<AppointmentPO> list() {
        AppointmentExample example = new AppointmentExample();
        AppointmentExample.Criteria criteria = example.createCriteria()
                .andStartTimeGreaterThanOrEqualTo(DateUtils.dateBuilder(LocalDate.now(), "00:00:00"))
                .andStatusEqualTo(StatusEnum.NORMAL.getCode());
        if (RoleEnum.USER.getCode().equals(WebHelper.getRoleId())) {
            criteria.andCreatorEqualTo(WebHelper.getUsername());
        }
        return appointmentMapper.selectByExample(example);
    }

    @Override
    public List<AppointmentVO> roomList(Integer day, Integer roomId) {
        return Arrays.stream(AppointmentTimeAreaEnum.values()).map(e -> {
            Integer code = e.getCode();
            AppointmentVO vo = new AppointmentVO();
            vo.setDay(day);
            vo.setAreaCode(code);
            vo.setDesc(e.getStartTime());
            vo.setRoomId(roomId);
            PageHelper.startPage(1, 1);
            AppointmentExample example = new AppointmentExample();
            example.createCriteria()
                    .andRoomIdEqualTo(roomId)
                    .andStartTimeEqualTo(timeProcess(day, vo.getAreaCode()).getStartTime())
                    .andEndTimeEqualTo(timeProcess(day, vo.getAreaCode()).getEndTime());
            List<AppointmentPO> list = appointmentMapper.selectByExample(example);
            if (ObjectUtils.isEmpty(list)) {
                vo.setStatus(false);
            } else {
                vo.setStatus(list.get(0).getStatus() == 1);
            }
            return vo;
        }).collect(Collectors.toList());
    }

    private void check(AppointmentPO appointment) {
        PageHelper.startPage(1, 1);
        AppointmentExample example = new AppointmentExample();
        example.createCriteria()
                .andRoomIdEqualTo(appointment.getRoomId())
                .andStatusEqualTo(StatusEnum.NORMAL.getCode())
                .andStartTimeEqualTo(appointment.getStartTime())
                .andEndTimeEqualTo(appointment.getEndTime());
        List<AppointmentPO> list = appointmentMapper.selectByExample(example);
        if (!ObjectUtils.isEmpty(list)) {
            throw new AppException("当前时段无法预约", HttpStatus.FORBIDDEN.value());
        }
    }

    private AppointmentPO build(AppointmentVO vo) {
        AppointmentPO po = new AppointmentPO();
        po.setCreator(WebHelper.getUsername());
        po.setModifier(WebHelper.getUsername());
        Integer day = vo.getDay();
        po.setStartTime(timeProcess(day, vo.getAreaCode()).getStartTime());
        po.setEndTime(timeProcess(day, vo.getAreaCode()).getEndTime());
        RoomPO room = roomMapper.selectByPrimaryKey(vo.getRoomId());
        if (!ObjectUtils.isEmpty(room)) {
            po.setRoomName(room.getName());
            po.setRoomType(room.getType());
        }
        po.setRoomId(vo.getRoomId());
        return po;
    }

    private AppointmentPO timeProcess(Integer day, Integer areaCode) {
        AppointmentPO po = new AppointmentPO();
        if (AppointmentDayEnum.TODAY.getCode().equals(day)) {
            Date date = DateUtils.dateBuilder(LocalDate.now(), getTime(areaCode));
            LocalDateTime today = DateUtils.date2LocalDateTime(date);
            LocalDateTime later = today.plusHours(2);
            po.setStartTime(date);
            po.setEndTime(DateUtils.localDateTime2Date(later));
        }
        if (AppointmentDayEnum.TOMORROW.getCode().equals(day)) {
            Date date = DateUtils.dateBuilder(LocalDate.now().plusDays(1), getTime(areaCode));
            LocalDateTime tomorrow = DateUtils.date2LocalDateTime(date);
            LocalDateTime later = tomorrow.plusHours(2);
            po.setStartTime(date);
            po.setEndTime(DateUtils.localDateTime2Date(later));
        }
        return po;
    }

    private String getTime(Integer code) {
        return AppointmentTimeAreaEnum.findByCcde(code).getStartTime();
    }

    private void paramsCheck(AppointmentVO vo) {
        Assert.isNull(vo, "appointment object is null");
        Assert.isNull(vo.getAreaCode(), "time area code is null");
        Assert.isNull(vo.getRoomId(), "room id is null");
        Assert.isNull(vo.getDay(), "day is null");
        Assert.notTrue(AppointmentDayEnum.contains(vo.getDay()), "error day data");
    }
}
