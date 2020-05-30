package com.example.mapreduce.salary.allSalarySta;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.bson.BSONObject;

import java.io.IOException;

public class AllSalaryStaMapper extends Mapper<Object, BSONObject, Text, MapWritable> {

    private Text minText = new Text("minSalary");
    private Text maxText = new Text("maxSalary");
    private Text companyCountText = new Text("companyNumber");
    private Text avgMinSalText = new Text("avgMinSalary");
    private Text avgMaxSalText = new Text("avgMaxSalary");
    private IntWritable minWritable = new IntWritable();
    private IntWritable maxWritable = new IntWritable();


    @Override
    protected void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
        //String city = (String)value.get("city");
        Integer companyNumber = (Integer) value.get("companyNumber");
        Integer minSalary = (Integer) value.get("minSalary");
        Integer maxSalary = (Integer) value.get("maxSalary");
        Integer avgMinSalary = (Integer) value.get("avgMinSalary");
        Integer avgMaxSalary = (Integer) value.get("avgMaxSalary");
        Integer avgSalary = (Integer) value.get("avgSalary");
        MapWritable salary = new MapWritable();
        minWritable.set(minSalary);
        maxWritable.set(maxSalary);
        salary.put(minText, minWritable);
        salary.put(maxText, maxWritable);
        salary.put(companyCountText, new IntWritable(companyNumber));
        salary.put(avgMinSalText, new IntWritable(avgMinSalary));
        salary.put(avgMaxSalText, new IntWritable(avgMaxSalary));
        context.write(new Text("全国"), salary);
    }
}