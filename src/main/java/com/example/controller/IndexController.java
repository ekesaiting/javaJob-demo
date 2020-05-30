package com.example.controller;

import com.example.pojo.Job;
import com.example.pojo.conditions.Conditions;
import com.example.pojo.salary.AllSalary;
import com.example.pojo.salary.Salary;
import com.example.service.JobService;
import com.example.service.conditions.ConditionsService;
import com.example.service.salary.AllSalaryService;
import com.example.service.salary.SalaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private JobService jobService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private  AllSalaryService allSalaryService;
    @Autowired
    private ConditionsService conditionsService;

    private ObjectMapper objectMapper=new ObjectMapper();
    @GetMapping(value = {"/index","/"})
    public String index(){
        return "index";
    }
    @GetMapping("/getAllJobs")
    @ResponseBody
    public String getAllJobs() throws JsonProcessingException {
        List<Job> jobs = jobService.findAll();
        String jobsJson = objectMapper.writeValueAsString(jobs);
        return jobsJson;
    }
    @GetMapping("/getSalaryRes")
    @ResponseBody
    public String getSalaryRes() throws JsonProcessingException {
        List<Salary> salaries = salaryService.findAll();
        String salariesJson = objectMapper.writeValueAsString(salaries);
        return salariesJson;
    }

    @GetMapping("/getAllSalaryRes")
    @ResponseBody
    public String getAllSalaryRes() throws JsonProcessingException {
        List<AllSalary> allSalaries = allSalaryService.findAll();
        String allSalariesJson = objectMapper.writeValueAsString(allSalaries);
        return allSalariesJson;
    }

    @GetMapping("/getConditionsRes")
    @ResponseBody
    public String getConditionsRes() throws JsonProcessingException {
        List<Conditions> conditions = conditionsService.findAll();
        String conditionsJson = objectMapper.writeValueAsString(conditions);
        return conditionsJson;
    }
    @GetMapping("/deleteAll")
    public  void deleteAll(){
        jobService.deleteAll();
        salaryService.deleteAll();
        allSalaryService.deleteAll();
        conditionsService.deleteAll();
    }
    @GetMapping("/deleteStaRes")
    public void deleteStaRes(){
        salaryService.deleteAll();
        allSalaryService.deleteAll();
        conditionsService.deleteAll();
    }
}
