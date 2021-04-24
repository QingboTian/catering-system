package cn.tianqb.pojo.vo;

import lombok.Data;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/4/24 13:08
 */
@Data
public class AppointmentVO {

    /**
     * 时间区间id
     */
    private Integer areaCode;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 包间id
     */
    private Integer roomId;

    /**
     * 包间类型
     */
    private Integer roomType;

    /**
     * 今天（1）
     * 明天（2）
     */
    private Integer day;

    private String desc;
}
