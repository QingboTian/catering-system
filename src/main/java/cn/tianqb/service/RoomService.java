package cn.tianqb.service;

import cn.tianqb.pojo.po.RoomPO;
import cn.tianqb.pojo.query.RoomQuery;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 14:32
 */
public interface RoomService {
    Boolean create(RoomPO room);

    List<RoomPO> list(RoomQuery query);

    Boolean delete(Integer id);

    RoomPO findOne(RoomQuery query);
}
