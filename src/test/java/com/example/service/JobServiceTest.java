package com.example.service;

import com.example.pojo.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobServiceTest {
    @Autowired
    private JobService jobService;

    @Test
    public void testFindAll() {
        List<Job> jobs = jobService.findAll();
        for (Job job : jobs) {
            System.out.println(job);
        }
    }
}