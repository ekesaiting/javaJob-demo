package com.example.service.salary;

import com.example.pojo.salary.AllSalary;
import com.example.repository.salary.AllSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllSalaryService {
    @Autowired
    private AllSalaryRepository allSalaryRepository;
    public List<AllSalary> findAll(){
        return allSalaryRepository.findAll();
    }
    public void deleteAll(){
        allSalaryRepository.deleteAll();
    }
}
