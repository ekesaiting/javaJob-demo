package com.example.service.salary;

import com.example.pojo.salary.Salary;
import com.example.repository.salary.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> findAll(){
        return salaryRepository.findAll();
    }
    public void deleteAll(){
        salaryRepository.deleteAll();
    }
}
