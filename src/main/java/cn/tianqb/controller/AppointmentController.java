package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.AppointmentPO;
import cn.tianqb.pojo.vo.AppointmentVO;
import cn.tianqb.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约
 *
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:25
 */
@RestController
@Slf4j
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public WebResult<Boolean> appointment(@RequestBody AppointmentVO vo) {
        return WebResult.ok(appointmentService.appointment(vo));
    }

    @PostMapping("/cancel")
    public WebResult<Boolean> cancel(Integer id) {
        return WebResult.ok(appointmentService.cancel(id));
    }

    @GetMapping("/list")
    public WebResult<List<AppointmentPO>> list() {
        return WebResult.ok(appointmentService.list());
    }

    /**
     * 预约列表
     * @return
     */
    @GetMapping("/room/list")
    public WebResult<List<AppointmentVO>> roomList(Integer day, Integer roomId) {
        return WebResult.ok(appointmentService.roomList(day, roomId));
    }
}
