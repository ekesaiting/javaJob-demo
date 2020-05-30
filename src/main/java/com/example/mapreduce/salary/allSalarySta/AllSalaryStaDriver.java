package com.example.mapreduce.salary.allSalarySta;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;
import com.mongodb.hadoop.util.MongoTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;

import java.net.UnknownHostException;

public class AllSalaryStaDriver extends MongoTool {
    public AllSalaryStaDriver() throws UnknownHostException {
        Configuration conf = new Configuration();
        setConf(conf);
        setJobName("AllSalarySta");
        //windows本地测试时，开启远程提交
        conf.set("mapreduce.app-submission.cross-platform", "true");
        //本地测试，方便调试，集群运行时需要删除，或修改对应mapred-site中的属性
        conf.set("mapreduce.framework.name", "local");
        MongoConfigUtil.setInputFormat(getConf(), MongoInputFormat.class);
        MongoConfigUtil.setOutputFormat(getConf(), MongoOutputFormat.class);
        MongoConfigUtil.setInputURI(getConf(), "mongodb://shf:123456@server:27017/mydb.salaryResult?authSource=mydb");
        MongoConfigUtil.setOutputURI(getConf(), "mongodb://shf:123456@server:27017/mydb.allSalaryResult?authSource=mydb");
        MongoConfigUtil.setAuthURI(getConf(), "mongodb://shf:123456@server:27017/mydb");
        MongoConfigUtil.setMapper(getConf(), AllSalaryStaMapper.class);
        MongoConfigUtil.setReducer(getConf(), AllSalaryStaReducer.class);
        MongoConfigUtil.setOutputKey(getConf(), Text.class);
        MongoConfigUtil.setOutputValue(getConf(), MapWritable.class);
    }
}