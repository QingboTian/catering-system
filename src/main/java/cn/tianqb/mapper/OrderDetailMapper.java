package cn.tianqb.mapper;

import cn.tianqb.pojo.example.OrderDetailExample;
import cn.tianqb.pojo.po.OrderDetailPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @SelectProvider(type=OrderDetailSqlProvider.class, method="countByExample")
    int countByExample(OrderDetailExample example);

    @DeleteProvider(type=OrderDetailSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderDetailExample example);

    @Delete({
        "delete from order_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into order_detail (id, dishes_id, ",
        "total, total_price, ",
        "dishes_price, order_id, ",
        "dishes_name, url, ",
        "created, modified, ",
        "creator, modifier, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{dishesId,jdbcType=INTEGER}, ",
        "#{total,jdbcType=INTEGER}, #{totalPrice,jdbcType=DECIMAL}, ",
        "#{dishesPrice,jdbcType=DECIMAL}, #{orderId,jdbcType=VARCHAR}, ",
        "#{dishesName,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=TIMESTAMP}, #{modified,jdbcType=TIMESTAMP}, ",
        "#{creator,jdbcType=VARCHAR}, #{modifier,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(OrderDetailPO record);

    @InsertProvider(type=OrderDetailSqlProvider.class, method="insertSelective")
    int insertSelective(OrderDetailPO record);

    @SelectProvider(type=OrderDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dishes_id", property="dishesId", jdbcType=JdbcType.INTEGER),
        @Result(column="total", property="total", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="dishes_price", property="dishesPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="dishes_name", property="dishesName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<OrderDetailPO> selectByExample(OrderDetailExample example);

    @Select({
        "select",
        "id, dishes_id, total, total_price, dishes_price, order_id, dishes_name, url, ",
        "created, modified, creator, modifier, status",
        "from order_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dishes_id", property="dishesId", jdbcType=JdbcType.INTEGER),
        @Result(column="total", property="total", jdbcType=JdbcType.INTEGER),
        @Result(column="total_price", property="totalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="dishes_price", property="dishesPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="dishes_name", property="dishesName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    OrderDetailPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderDetailPO record, @Param("example") OrderDetailExample example);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderDetailPO record, @Param("example") OrderDetailExample example);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderDetailPO record);

    @Update({
        "update order_detail",
        "set dishes_id = #{dishesId,jdbcType=INTEGER},",
          "total = #{total,jdbcType=INTEGER},",
          "total_price = #{totalPrice,jdbcType=DECIMAL},",
          "dishes_price = #{dishesPrice,jdbcType=DECIMAL},",
          "order_id = #{orderId,jdbcType=VARCHAR},",
          "dishes_name = #{dishesName,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderDetailPO record);
}
