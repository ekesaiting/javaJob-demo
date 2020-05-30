package com.example.service.conditions;

import com.example.pojo.conditions.Conditions;
import com.example.repository.conditions.ConditionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditionsService {
    @Autowired
    private ConditionsRepository conditionsRepository;
    public List<Conditions> findAll(){
        return  conditionsRepository.findAll();
    }
    public void deleteAll(){
        conditionsRepository.deleteAll();
    }
}
