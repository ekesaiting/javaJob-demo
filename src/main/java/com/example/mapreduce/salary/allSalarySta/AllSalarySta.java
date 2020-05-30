package com.example.mapreduce.salary.allSalarySta;

import org.apache.hadoop.util.ToolRunner;
import org.springframework.stereotype.Component;

@Component
public class AllSalarySta {
    public void run() throws Exception {
        System.load("D:\\develop\\tools\\winutils-master\\hadoop-3.2.1\\bin\\hadoop.dll");
        String[] args=null;
        ToolRunner.run(new AllSalaryStaDriver(), args);
    }
}
