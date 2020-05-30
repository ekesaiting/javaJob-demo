package com.example.repository.conditions;

import com.example.pojo.conditions.Conditions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionsRepository extends MongoRepository<Conditions,String> {
}
