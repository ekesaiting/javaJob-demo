package com.example.pojo.salary;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 各个城市工资结果的统计集合对应的java类
 */
@Data
@Document("salaryResult")
public class Salary {
    @Id
    @Field("_id")
    private String salaryId;//工资集合主键
    private String city;//城市名
    private String companyNumber;//公司总数
    private String minSalary;//最小工资
    private String maxSalary;//最大工资
    private String avgMinSalary;//平均最小工资
    private String avgMaxSalary;//平均最大工资
    private String avgSalary;//平均工资

}
