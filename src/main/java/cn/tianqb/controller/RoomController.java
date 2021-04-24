package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.RoomPO;
import cn.tianqb.pojo.query.RoomQuery;
import cn.tianqb.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 14:31
 */
@RestController
@RequestMapping("/api/room")
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public WebResult<Boolean> create(@RequestBody RoomPO room) {
        return WebResult.ok(roomService.create(room));
    }

    @GetMapping("/list")
    public WebResult<List<RoomPO>> list(RoomQuery query) {
        return WebResult.ok(roomService.list(query));
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return WebResult.ok(roomService.delete(id));
    }

    @GetMapping("/get")
    public WebResult<RoomPO> findOne(RoomQuery query) {
        return WebResult.ok(roomService.findOne(query));
    }
}
