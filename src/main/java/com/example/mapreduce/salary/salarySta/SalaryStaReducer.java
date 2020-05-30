package com.example.mapreduce.salary.salarySta;

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

public class SalaryStaReducer  extends Reducer<Text,MapWritable, Text, BSONWritable>{
    private Text minText=new Text("minSalary");
    private Text maxText=new Text("maxSalary");
    private Text count=new Text("count");

    @Override
    protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
        int companyNumber=0;
        int minSalary=Integer.MAX_VALUE;
        int maxSalary=Integer.MIN_VALUE;

        int minSalCount=0;
        int maxSalCount=0;
        for (MapWritable map : values) {
            IntWritable min = (IntWritable) map.get(minText);
            IntWritable max = (IntWritable) map.get(maxText);
            maxSalary=Math.max(max.get(),maxSalary);
            minSalary= Math.min(min.get(),minSalary);
            maxSalCount+=max.get();
            minSalCount+=min.get();
            companyNumber+=1;
        }
        BasicBSONObject output = new BasicBSONObject();
        output.put("city",new String(key.copyBytes()));//城市
        output.put("companyNumber", companyNumber);//当前城市公司数量
        output.put("minSalary",minSalary);//最低工资
        output.put("maxSalary",maxSalary);//最高工资
        output.put("avgMinSalary",minSalCount/companyNumber);//平均最高工资
        output.put("avgMaxSalary",maxSalCount/companyNumber);//平均最低工资
        output.put("avgSalary",(minSalCount+maxSalCount)/(2*companyNumber));//平均工资
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //当前时间做主键插入集合
        context.write(new Text(sdf.format(new Date()).toString()+"|"+ UUID.randomUUID()), new BSONWritable(output) );
    }
}
