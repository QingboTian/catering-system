package cn.tianqb.service.impl;

import cn.tianqb.enums.StatusEnum;
import cn.tianqb.mapper.RoomMapper;
import cn.tianqb.pojo.example.RoomExample;
import cn.tianqb.pojo.po.RoomPO;
import cn.tianqb.pojo.query.RoomQuery;
import cn.tianqb.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 14:32
 */
@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Boolean create(RoomPO room) {
        return roomMapper.insertSelective(room) == 1;
    }

    @Override
    public List<RoomPO> list(RoomQuery query) {
        RoomExample example = new RoomExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.NORMAL.getCode());
        return roomMapper.selectByExample(example);
    }

    @Override
    public Boolean delete(Integer id) {
        RoomPO room = new RoomPO();
        room.setId(id);
        room.setStatus(StatusEnum.DELETED.getCode());
        return roomMapper.updateByPrimaryKeySelective(room) == 1;
    }

    @Override
    public RoomPO findOne(RoomQuery query) {
        return roomMapper.selectByPrimaryKey(query.getId());
    }
}
