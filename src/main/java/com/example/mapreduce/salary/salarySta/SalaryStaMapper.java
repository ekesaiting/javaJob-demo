package com.example.mapreduce.salary.salarySta;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.bson.BSONObject;

import java.io.IOException;

public class SalaryStaMapper extends Mapper<Object, BSONObject, Text, MapWritable> {

    private Text minText=new Text("minSalary");
    private Text maxText=new Text("maxSalary");
    private Text count=new Text("count");
    private IntWritable minWritable=new IntWritable();
    private IntWritable maxWritable=new IntWritable();
    private IntWritable countWritable=new IntWritable(1);


    @Override
    protected void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
        String jobAddress = (String)value.get("jobAddress");
        Integer minSalary=((Integer)value.get("minSalary")) ;
        Integer maxSalary=((Integer)value.get("maxSalary")) ;

        //获取城市名
        String[] cityName = jobAddress.split("-");
        MapWritable salary = new MapWritable();
        minWritable.set(minSalary);
        maxWritable.set(maxSalary);
        salary.put(minText,minWritable);
        salary.put(maxText,maxWritable);
        salary.put(count,countWritable);
        context.write(new Text(cityName[0]),salary);
    }
}
