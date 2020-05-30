package com.example.mapreduce.conditions;

import org.apache.hadoop.util.ToolRunner;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Component
public class ConditionsSta {
    public void run() throws Exception {
        System.load("D:\\develop\\tools\\winutils-master\\hadoop-3.2.1\\bin\\hadoop.dll");
        String[] args=null;
        ToolRunner.run(new ConditionsDriver(), args);
    }
    }

