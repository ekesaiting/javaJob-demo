package com.example.pojo.salary;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 所有工资统计结果集合对应的java类
 */
@Data
@Document("allSalaryResult")
public class AllSalary {

    @Id
    @Field("_id")
    private String   id;//所有工资结果集合的主键
    @Field("companyNumber")
    private String   count;//公司总数
    private String   maxSalary;//该城市最大工资
    private String   minSalary;//该城市最小工资
    private String   avgMinSalary;//平均最小工资
    private String   avgMaxSalary;//平均最大工资
    private String   avgSalary;//平均工资

}
