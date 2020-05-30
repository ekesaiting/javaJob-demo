package com.example.mapreduce.conditions;

import com.example.utils.MyStringUtils;
import com.mongodb.BasicDBList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.bson.BSONObject;

import java.io.IOException;

public class ConditionsMapper extends Mapper<Object, BSONObject, Text, MapWritable> {
    //学历判断
    private Text educationText=new Text("education");
    private Text experienceText=new Text("experienceText");//是否要求经验，0否，1是
    private IntWritable zero=new IntWritable(0); // 0，无学历要求
    private IntWritable one=new IntWritable(1);// 1，大专
    private IntWritable two=new IntWritable(2);  // 2,本科
    private IntWritable three=new IntWritable(3); // 3,研究生
    private IntWritable four=new IntWritable(4);// 4,博士
    @Override
    protected void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
        String jobAddress = (String)value.get("jobAddress");
        //获取城市名
        String[] cityName = jobAddress.split("-");
        //获取条件
        BasicDBList conditions = (BasicDBList) value.get("conditions");

        MapWritable conditionsMap = new MapWritable();
        for (Object conditionTmp : conditions) {
            String condition=(String)conditionTmp;
            if (condition.contains(cityName[0]))
                continue;
            if(condition.contains(new StringBuilder("年经验"))){
                conditionsMap.put(experienceText,one);//要求有经验
                continue;
            }
            if (condition.contains("大专")||condition.contains("专科")) //是否是专科
                conditionsMap.put(educationText,one);
            else if (condition.contains("本科"))//是否是本科
                conditionsMap.put(educationText,two);
            else if (condition.contains("硕士")||condition.contains("研究生"))//是否是硕士
                conditionsMap.put(educationText,three);
            else if (condition.contains("博士"))//是否是博士
                conditionsMap.put(educationText,four);
        }
        if (conditionsMap.get(experienceText)==null)
            conditionsMap.put(experienceText,zero);//不要求要求有经验
        if (conditionsMap.get(educationText)==null)
            conditionsMap.put(educationText,zero);//不要求学历

        context.write(new Text(cityName[0]),conditionsMap);

    }
}
