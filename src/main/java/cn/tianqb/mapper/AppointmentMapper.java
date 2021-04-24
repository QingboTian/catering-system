package cn.tianqb.mapper;

import cn.tianqb.pojo.example.AppointmentExample;
import cn.tianqb.pojo.po.AppointmentPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    @SelectProvider(type=AppointmentSqlProvider.class, method="countByExample")
    int countByExample(AppointmentExample example);

    @DeleteProvider(type=AppointmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(AppointmentExample example);

    @Delete({
        "delete from AppointmentPO",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into AppointmentPO (id, status, ",
        "room_id, room_name, ",
        "room_type, created, ",
        "modified, creator, ",
        "modifier, start_time, ",
        "end_time)",
        "values (#{id,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{roomId,jdbcType=INTEGER}, #{roomName,jdbcType=VARCHAR}, ",
        "#{roomType,jdbcType=INTEGER}, #{created,jdbcType=TIMESTAMP}, ",
        "#{modified,jdbcType=TIMESTAMP}, #{creator,jdbcType=VARCHAR}, ",
        "#{modifier,jdbcType=VARCHAR}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP})"
    })
    int insert(AppointmentPO record);

    @InsertProvider(type=AppointmentSqlProvider.class, method="insertSelective")
    int insertSelective(AppointmentPO record);

    @SelectProvider(type=AppointmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="room_id", property="roomId", jdbcType=JdbcType.INTEGER),
        @Result(column="room_name", property="roomName", jdbcType=JdbcType.VARCHAR),
        @Result(column="room_type", property="roomType", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AppointmentPO> selectByExample(AppointmentExample example);

    @Select({
        "select",
        "id, status, room_id, room_name, room_type, created, modified, creator, modifier, ",
        "start_time, end_time",
        "from AppointmentPO",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="room_id", property="roomId", jdbcType=JdbcType.INTEGER),
        @Result(column="room_name", property="roomName", jdbcType=JdbcType.VARCHAR),
        @Result(column="room_type", property="roomType", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AppointmentPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AppointmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AppointmentPO record, @Param("example") AppointmentExample example);

    @UpdateProvider(type=AppointmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AppointmentPO record, @Param("example") AppointmentExample example);

    @UpdateProvider(type=AppointmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppointmentPO record);

    @Update({
        "update AppointmentPO",
        "set status = #{status,jdbcType=INTEGER},",
          "room_id = #{roomId,jdbcType=INTEGER},",
          "room_name = #{roomName,jdbcType=VARCHAR},",
          "room_type = #{roomType,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AppointmentPO record);
}
