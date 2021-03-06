package cn.tianqb.mapper;

import cn.tianqb.pojo.po.OrderPO;
import cn.tianqb.pojo.example.OrderExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OrderMapper {
    @SelectProvider(type=OrderSqlProvider.class, method="countByExample")
    int countByExample(OrderExample example);

    @DeleteProvider(type=OrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderExample example);

    @Delete({
        "delete from order_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into order_table (id, order_id, ",
        "total_price, phone, ",
        "address, remark, ",
        "created, modified, ",
        "creator, modifier, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{orderId,jdbcType=VARCHAR}, ",
        "#{totalPrice,jdbcType=DOUBLE}, #{phone,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=TIMESTAMP}, #{modified,jdbcType=TIMESTAMP}, ",
        "#{creator,jdbcType=VARCHAR}, #{modifier,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(OrderPO record);

    @InsertProvider(type=OrderSqlProvider.class, method="insertSelective")
    int insertSelective(OrderPO record);

    @SelectProvider(type=OrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DOUBLE),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<OrderPO> selectByExample(OrderExample example);

    @Select({
        "select",
        "id, order_id, total_price, phone, address, remark, created, modified, creator, ",
        "modifier, status",
        "from order_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DOUBLE),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    OrderPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderPO record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderPO record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderPO record);

    @Update({
        "update order_table",
        "set order_id = #{orderId,jdbcType=VARCHAR},",
          "total_price = #{totalPrice,jdbcType=DOUBLE},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderPO record);
}
