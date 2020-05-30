package com.example.repository.salary;

import com.example.pojo.salary.AllSalary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllSalaryRepository extends MongoRepository<AllSalary,String> {
}
