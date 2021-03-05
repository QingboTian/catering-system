package cn.tianqb.mapper;

import cn.tianqb.pojo.example.DishesExample;
import cn.tianqb.pojo.po.DishesPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


@Mapper
public interface DishesMapper {
    @SelectProvider(type=DishesSqlProvider.class, method="countByExample")
    int countByExample(DishesExample example);

    @DeleteProvider(type=DishesSqlProvider.class, method="deleteByExample")
    int deleteByExample(DishesExample example);

    @Delete({
        "delete from dishes",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into dishes (id, name, ",
        "description, price, ",
        "taste, url, status, ",
        "created, modified, ",
        "creator, modifier, ",
        "category_id, category)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{price,jdbcType=DECIMAL}, ",
        "#{taste,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{created,jdbcType=TIMESTAMP}, #{modified,jdbcType=TIMESTAMP}, ",
        "#{creator,jdbcType=VARCHAR}, #{modifier,jdbcType=VARCHAR}, ",
        "#{categoryId,jdbcType=INTEGER}, #{category,jdbcType=VARCHAR})"
    })
    int insert(DishesPO record);

    @InsertProvider(type=DishesSqlProvider.class, method="insertSelective")
    int insertSelective(DishesPO record);

    @SelectProvider(type=DishesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="taste", property="taste", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR)
    })
    List<DishesPO> selectByExample(DishesExample example);

    @Select({
        "select",
        "id, name, description, price, taste, url, status, created, modified, creator, ",
        "modifier, category_id, category",
        "from dishes",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="taste", property="taste", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR)
    })
    DishesPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DishesSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DishesPO record, @Param("example") DishesExample example);

    @UpdateProvider(type=DishesSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DishesPO record, @Param("example") DishesExample example);

    @UpdateProvider(type=DishesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DishesPO record);

    @Update({
        "update dishes",
        "set name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "taste = #{taste,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR},",
          "category_id = #{categoryId,jdbcType=INTEGER},",
          "category = #{category,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DishesPO record);
}
