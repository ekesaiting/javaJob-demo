package com.example.mapreduce.salary.allSalarySta;

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

class AllSalaryStaReducer extends Reducer<Text, MapWritable, Text, BSONWritable> {

    private Text minText = new Text("minSalary");
    private Text maxText = new Text("maxSalary");
    private Text companyCountText = new Text("companyNumber");
    private Text avgMinSalText = new Text("avgMinSalary");
    private Text avgMaxSalText = new Text("avgMaxSalary");

    @Override
    protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
        int minSalary = Integer.MAX_VALUE;
        int maxSalary = Integer.MIN_VALUE;
        int companyNumber = 0;//公司总数
        int minSalCount = 0;//最低工资总和
        int maxSalCount = 0;//最高工资总和
        for (MapWritable map : values) {
            IntWritable min = (IntWritable) map.get(minText);
            IntWritable max = (IntWritable) map.get(maxText);
            IntWritable count = (IntWritable) map.get(companyCountText);
            IntWritable avgMinSal=(IntWritable) map.get(avgMinSalText);
            IntWritable avgMaxSal=(IntWritable) map.get(avgMaxSalText);
            //找出全国最高/低工资
            maxSalary = Math.max(max.get(), maxSalary);
            minSalary = Math.min(min.get(), minSalary);
            minSalCount += avgMinSal.get();
            maxSalCount += avgMaxSal.get();
            companyNumber = companyNumber + count.get();//统计全国所有公司数
        }
        BasicBSONObject output = new BasicBSONObject();
        //output.put("city", new String(key.copyBytes()));//全国
        output.put("companyNumber", companyNumber);//全国公司数量
        output.put("maxSalary", maxSalary);//最高工资
        output.put("minSalary", minSalary);//最低工资
        output.put("avgMinSalary", minSalCount / companyNumber);//平均最高工资
        output.put("avgMaxSalary", maxSalCount / companyNumber);//平均最低工资
        output.put("avgSalary", (minSalCount + maxSalCount) / ( 2*companyNumber));//平均工资
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //当前时间做主键插入集合
        context.write(new Text(sdf.format(new Date()).toString() + "|" + UUID.randomUUID()), new BSONWritable(output));
    }
}