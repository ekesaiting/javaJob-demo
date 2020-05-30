package com.example.mapreduce.conditions;

import com.mongodb.hadoop.io.BSONWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.bson.BasicBSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ConditionsReducer extends Reducer<Text, MapWritable, Text, BSONWritable> {

    private Text educationText = new Text("education");
    private Text experienceText = new Text("experienceText");//是否要求经验，0否，1是

    @Override
    protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
        int noCondition = 0;//无学历要求公司数
        int specialist = 0;//学历要求专科公司数
        int bachelor = 0;//学历要求本科公司数
        int master = 0;//学历要求硕士公司数
        int doctor = 0;//学历要求博士公司数
        int experienceCount = 0;//有经验要求公司数
        int noExperienceCount = 0;//无经验要求公司数
        for (MapWritable map : values) {
            IntWritable education = (IntWritable) map.get(educationText);
            IntWritable experience = (IntWritable) map.get(experienceText);

            if (education.get() == 1)
                specialist++;
            else if (education.get() == 2)
                bachelor++;
            else if (education.get() == 3)
                master++;
            else if (education.get() == 4)
                doctor++;
            else
                noCondition++;

            if (experience.get() == 0)//无经验要求
                noExperienceCount++;
            else
                experienceCount++;//有经验要求
        }
        BasicBSONObject output = new BasicBSONObject();
        output.put("city",new String(key.copyBytes()));//城市
        output.put("count",experienceCount+noExperienceCount);//总公司数
        output.put("experience", experienceCount);//有经验要求的公司数
        output.put("noExperience", noExperienceCount);//无经验要求的公司数
        output.put("specialist", specialist);//专科要求的公司数
        output.put("bachelor", bachelor);//本科要求的公司数
        output.put("master", master);//硕士要求的公司数
        output.put("doctor", doctor);//博士要求的公司数
        output.put("noCondition", noCondition);//无要求的公司数
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //当前时间做主键插入集合
        context.write(new Text(sdf.format(new Date()).toString()+"|"+ UUID.randomUUID()), new BSONWritable(output) );
    }
}
