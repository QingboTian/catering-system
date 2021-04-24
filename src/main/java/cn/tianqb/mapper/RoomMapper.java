package cn.tianqb.mapper;

import cn.tianqb.pojo.example.RoomExample;
import cn.tianqb.pojo.po.RoomPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RoomMapper {
    @SelectProvider(type=RoomSqlProvider.class, method="countByExample")
    int countByExample(RoomExample example);

    @DeleteProvider(type=RoomSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoomExample example);

    @Delete({
        "delete from room",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into room (id, name, ",
        "type, no, human_num, ",
        "created, modified, ",
        "creator, modifier, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{no,jdbcType=INTEGER}, #{humanNum,jdbcType=INTEGER}, ",
        "#{created,jdbcType=TIMESTAMP}, #{modified,jdbcType=TIMESTAMP}, ",
        "#{creator,jdbcType=VARCHAR}, #{modifier,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(RoomPO record);

    @InsertProvider(type=RoomSqlProvider.class, method="insertSelective")
    int insertSelective(RoomPO record);

    @SelectProvider(type=RoomSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="no", property="no", jdbcType=JdbcType.INTEGER),
        @Result(column="human_num", property="humanNum", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<RoomPO> selectByExample(RoomExample example);

    @Select({
        "select",
        "id, name, type, no, human_num, created, modified, creator, modifier, status",
        "from room",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="no", property="no", jdbcType=JdbcType.INTEGER),
        @Result(column="human_num", property="humanNum", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    RoomPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=RoomSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoomPO record, @Param("example") RoomExample example);

    @UpdateProvider(type=RoomSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RoomPO record, @Param("example") RoomExample example);

    @UpdateProvider(type=RoomSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoomPO record);

    @Update({
        "update room",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "no = #{no,jdbcType=INTEGER},",
          "human_num = #{humanNum,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoomPO record);
}
