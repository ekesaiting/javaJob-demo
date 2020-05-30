package com.example.mapreduce.salary.salarySta;

import org.apache.hadoop.util.ToolRunner;
import org.springframework.stereotype.Component;

@Component
public class SalarySta {
    public void run() throws Exception {
        System.load("D:\\develop\\tools\\winutils-master\\hadoop-3.2.1\\bin\\hadoop.dll");
        String[] args=null;
        ToolRunner.run(new SalaryStaDriver(),args);
    }
}
