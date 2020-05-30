package com.example.task;

import com.example.mapreduce.conditions.ConditionsSta;
import com.example.mapreduce.salary.allSalarySta.AllSalarySta;
import com.example.mapreduce.salary.salarySta.SalarySta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainTask {
    @Autowired
    private JobTask jobTask;
    @Autowired
    private SalarySta salarySta;
    @Autowired
    private AllSalarySta allSalarySta;
    @Autowired
    private ConditionsSta conditionsSta;
    //每24个小时执行一次
    @Scheduled(fixedDelay = 24*60*1000*60)
    public void run() throws Exception {
        jobTask.getJobInfo();
        salarySta.run();
        allSalarySta.run();
        conditionsSta.run();
    }
}
