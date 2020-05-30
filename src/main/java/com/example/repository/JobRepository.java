package com.example.repository;

import com.example.pojo.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 持久层接口
 */
@Repository
public interface JobRepository extends MongoRepository<Job,String> {
}
