package cn.tianqb.mapper;

import cn.tianqb.pojo.example.RoomExample;
import cn.tianqb.pojo.example.RoomExample.Criteria;
import cn.tianqb.pojo.example.RoomExample.Criterion;
import cn.tianqb.pojo.po.RoomPO;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class RoomSqlProvider {

    public String countByExample(RoomExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("room");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(RoomExample example) {
        BEGIN();
        DELETE_FROM("room");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(RoomPO record) {
        BEGIN();
        INSERT_INTO("room");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            VALUES("type", "#{type,jdbcType=INTEGER}");
        }

        if (record.getNo() != null) {
            VALUES("no", "#{no,jdbcType=INTEGER}");
        }

        if (record.getHumanNum() != null) {
            VALUES("human_num", "#{humanNum,jdbcType=INTEGER}");
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

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        return SQL();
    }

    public String selectByExample(RoomExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("type");
        SELECT("no");
        SELECT("human_num");
        SELECT("created");
        SELECT("modified");
        SELECT("creator");
        SELECT("modifier");
        SELECT("status");
        FROM("room");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RoomPO record = (RoomPO) parameter.get("record");
        RoomExample example = (RoomExample) parameter.get("example");

        BEGIN();
        UPDATE("room");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            SET("type = #{record.type,jdbcType=INTEGER}");
        }

        if (record.getNo() != null) {
            SET("no = #{record.no,jdbcType=INTEGER}");
        }

        if (record.getHumanNum() != null) {
            SET("human_num = #{record.humanNum,jdbcType=INTEGER}");
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

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("room");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("type = #{record.type,jdbcType=INTEGER}");
        SET("no = #{record.no,jdbcType=INTEGER}");
        SET("human_num = #{record.humanNum,jdbcType=INTEGER}");
        SET("created = #{record.created,jdbcType=TIMESTAMP}");
        SET("modified = #{record.modified,jdbcType=TIMESTAMP}");
        SET("creator = #{record.creator,jdbcType=VARCHAR}");
        SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        SET("status = #{record.status,jdbcType=INTEGER}");

        RoomExample example = (RoomExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(RoomPO record) {
        BEGIN();
        UPDATE("room");

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            SET("type = #{type,jdbcType=INTEGER}");
        }

        if (record.getNo() != null) {
            SET("no = #{no,jdbcType=INTEGER}");
        }

        if (record.getHumanNum() != null) {
            SET("human_num = #{humanNum,jdbcType=INTEGER}");
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

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(RoomExample example, boolean includeExamplePhrase) {
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
