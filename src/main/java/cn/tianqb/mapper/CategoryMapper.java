package cn.tianqb.mapper;

import cn.tianqb.pojo.example.CategoryExample;
import cn.tianqb.pojo.po.CategoryPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @SelectProvider(type=CategorySqlProvider.class, method="countByExample")
    int countByExample(CategoryExample example);

    @DeleteProvider(type=CategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into category (id, name, ",
        "parent_id, remark, ",
        "level, status, created, ",
        "modified, creator, ",
        "modifier)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, #{created,jdbcType=TIMESTAMP}, ",
        "#{modified,jdbcType=TIMESTAMP}, #{creator,jdbcType=VARCHAR}, ",
        "#{modifier,jdbcType=VARCHAR})"
    })
    int insert(CategoryPO record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    int insertSelective(CategoryPO record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<CategoryPO> selectByExample(CategoryExample example);

    @Select({
        "select",
        "id, name, parent_id, remark, level, status, created, modified, creator, modifier",
        "from category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    CategoryPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CategoryPO record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CategoryPO record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CategoryPO record);

    @Update({
        "update category",
        "set name = #{name,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "modifier = #{modifier,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CategoryPO record);
}
