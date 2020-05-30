package com.example.pojo.conditions;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 条件统计结果集合对应的java类
 */
@Data
@Document("conditionsResult")
public class Conditions {
    @Id
    @Field("_id")
    private String  id; //条件结果集合主键
    private String  city; //城市名
    private String  count; //公司总数
    private String  experience; //需要经验的公司数
    private String  noExperience; //不需要经验的公司数
    private String  specialist; //学历要求为大专的公司数
    private String  bachelor; //学历要求为本科的公司数
    private String  master; //学历要求为硕士的公司数
    private String  doctor; //学历要求为博士的公司数
    private String  noCondition; //无学历要求的公司数
}
