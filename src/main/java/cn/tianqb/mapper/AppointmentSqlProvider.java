package cn.tianqb.mapper;

import cn.tianqb.pojo.example.AppointmentExample;
import cn.tianqb.pojo.example.AppointmentExample.Criteria;
import cn.tianqb.pojo.example.AppointmentExample.Criterion;
import cn.tianqb.pojo.po.AppointmentPO;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class AppointmentSqlProvider {

    public String countByExample(AppointmentExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("AppointmentPO");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(AppointmentExample example) {
        BEGIN();
        DELETE_FROM("AppointmentPO");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(AppointmentPO record) {
        BEGIN();
        INSERT_INTO("AppointmentPO");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            VALUES("room_id", "#{roomId,jdbcType=INTEGER}");
        }

        if (record.getRoomName() != null) {
            VALUES("room_name", "#{roomName,jdbcType=VARCHAR}");
        }

        if (record.getRoomType() != null) {
            VALUES("room_type", "#{roomType,jdbcType=INTEGER}");
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

        if (record.getStartTime() != null) {
            VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }

        if (record.getEndTime() != null) {
            VALUES("end_time", "#{endTime,jdbcType=TIMESTAMP}");
        }

        return SQL();
    }

    public String selectByExample(AppointmentExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("status");
        SELECT("room_id");
        SELECT("room_name");
        SELECT("room_type");
        SELECT("created");
        SELECT("modified");
        SELECT("creator");
        SELECT("modifier");
        SELECT("start_time");
        SELECT("end_time");
        FROM("AppointmentPO");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AppointmentPO record = (AppointmentPO) parameter.get("record");
        AppointmentExample example = (AppointmentExample) parameter.get("example");

        BEGIN();
        UPDATE("AppointmentPO");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        }

        if (record.getRoomName() != null) {
            SET("room_name = #{record.roomName,jdbcType=VARCHAR}");
        }

        if (record.getRoomType() != null) {
            SET("room_type = #{record.roomType,jdbcType=INTEGER}");
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

        if (record.getStartTime() != null) {
            SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        }

        if (record.getEndTime() != null) {
            SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("AppointmentPO");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        SET("room_name = #{record.roomName,jdbcType=VARCHAR}");
        SET("room_type = #{record.roomType,jdbcType=INTEGER}");
        SET("created = #{record.created,jdbcType=TIMESTAMP}");
        SET("modified = #{record.modified,jdbcType=TIMESTAMP}");
        SET("creator = #{record.creator,jdbcType=VARCHAR}");
        SET("modifier = #{record.modifier,jdbcType=VARCHAR}");
        SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");

        AppointmentExample example = (AppointmentExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(AppointmentPO record) {
        BEGIN();
        UPDATE("AppointmentPO");

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{roomId,jdbcType=INTEGER}");
        }

        if (record.getRoomName() != null) {
            SET("room_name = #{roomName,jdbcType=VARCHAR}");
        }

        if (record.getRoomType() != null) {
            SET("room_type = #{roomType,jdbcType=INTEGER}");
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

        if (record.getStartTime() != null) {
            SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }

        if (record.getEndTime() != null) {
            SET("end_time = #{endTime,jdbcType=TIMESTAMP}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(AppointmentExample example, boolean includeExamplePhrase) {
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
