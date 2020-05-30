package com.example.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 从前程无忧抓取的原始数据
 */
@Data
@Document(collection="javaJob")
public class Job {
    @Id
    @Field("_id")
    private String JobId;//主键id
    private String jobUrl;//主页url
    private String jobName;//工作名称
    private String companyName;//公司名称
    private String companyInfo;//公司详情
    private String jobAddress;//公司所在城市
    private String jobDetailAddress;//工作详细地址
    private String[] conditions;//基本招聘条件
    private String jobInfo;//工作详情
    private Integer minSalary;//最低薪水，以年为单位
    private Integer maxSalary;//最高薪水，以年为单位
    private String date;//发布日期
}
