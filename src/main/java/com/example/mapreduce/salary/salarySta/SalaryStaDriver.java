package com.example.mapreduce.salary.salarySta;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;
import com.mongodb.hadoop.util.MongoTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

public class SalaryStaDriver extends MongoTool {
    public SalaryStaDriver() throws UnknownHostException {
        Configuration conf = new Configuration();
        setConf(conf);
        setJobName("SalarySta");
        //windows本地测试时，开启远程提交
        conf.set("mapreduce.app-submission.cross-platform", "true");
        //本地测试，方便调试，集群运行时需要删除，或修改对应mapred-site中的属性
        conf.set("mapreduce.framework.name", "local");
        MongoConfigUtil.setInputFormat(getConf(), MongoInputFormat.class);
        MongoConfigUtil.setOutputFormat(getConf(), MongoOutputFormat.class);
        MongoConfigUtil.setInputURI(getConf(), "mongodb://shf:123456@server:27017/mydb.javaJob?authSource=mydb");
        MongoConfigUtil.setOutputURI(getConf(), "mongodb://shf:123456@server:27017/mydb.salaryResult?authSource=mydb");
        MongoConfigUtil.setAuthURI(getConf(), "mongodb://shf:123456@server:27017/mydb");
        MongoConfigUtil.setMapper(getConf(), SalaryStaMapper.class);
        MongoConfigUtil.setReducer(getConf(), SalaryStaReducer.class);
        MongoConfigUtil.setOutputKey(getConf(), Text.class);
        MongoConfigUtil.setOutputValue(getConf(), MapWritable.class);
    }
}
