package cn.tianqb.mapper;

import cn.tianqb.pojo.example.CategoryExample;
import cn.tianqb.pojo.example.CategoryExample.Criteria;
import cn.tianqb.pojo.example.CategoryExample.Criterion;
import cn.tianqb.pojo.po.CategoryPO;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class CategorySqlProvider {

    public String countByExample(CategoryExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("category");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(CategoryExample example) {
        BEGIN();
        DELETE_FROM("category");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(CategoryPO record) {
        BEGIN();
        INSERT_INTO("category");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getParentId() != null) {
            VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }

        if (record.getRemark() != null) {
            VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }

        if (record.getLevel() != null) {
            VALUES("level", "#{level,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getCreated() != null) {
            VALUES("created", "#{created,jdbcType=TIMESTAMP}");
        }

        if (record.getModified() != null) {
            VALUES("modified", "#{modified,jdbcType=TIMESTAMP}");
        }

        if (record.getCreator() != null) {
            VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }

        if (record.getModifier() != null) {
            VALUES("modifier", "#{modifier,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    public String selectByExample(CategoryExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("parent_id");
        SELECT("remark");
        SELECT("level");
        SELECT("status");
        SELECT("created");
        SELECT("modified");
        SELECT("creator");
        SELECT("modifier");
        FROM("category");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CategoryPO record = (CategoryPO) parameter.get("record");
        CategoryExample example = (CategoryExample) parameter.get("example");

        BEGIN();
        UPDATE("category");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getParentId() != null) {
            SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        }

        if (record.getRemark() != null) {
            SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }

        if (record.getLevel() != null) {
            SET("level = #{record.level,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getCreated() != null) {
            SET("created = #{record.created,jdbcType=TIMESTAMP}");
        }

        if (record.getModified() != null) {
            SET("modified = #{record.modified,jdbcType=TIMESTAMP}");
        }

        if (record.getCreator() != null) {
            SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }

        if (record.getModifier() != null) {
            SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("category");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        SET("remark = #{record.remark,jdbcType=VARCHAR}");
        SET("level = #{record.level,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("created = #{record.created,jdbcType=TIMESTAMP}");
        SET("modified = #{record.modified,jdbcType=TIMESTAMP}");
        SET("creator = #{record.creator,jdbcType=VARCHAR}");
        SET("modifier = #{record.modifier,jdbcType=VARCHAR}");

        CategoryExample example = (CategoryExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(CategoryPO record) {
        BEGIN();
        UPDATE("category");

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getParentId() != null) {
            SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }

        if (record.getRemark() != null) {
            SET("remark = #{remark,jdbcType=VARCHAR}");
        }

        if (record.getLevel() != null) {
            SET("level = #{level,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getCreated() != null) {
            SET("created = #{created,jdbcType=TIMESTAMP}");
        }

        if (record.getModified() != null) {
            SET("modified = #{modified,jdbcType=TIMESTAMP}");
        }

        if (record.getCreator() != null) {
            SET("creator = #{creator,jdbcType=VARCHAR}");
        }

        if (record.getModifier() != null) {
            SET("modifier = #{modifier,jdbcType=VARCHAR}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(CategoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}
