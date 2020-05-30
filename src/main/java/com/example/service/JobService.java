package com.example.service;

import com.example.pojo.Job;
import com.example.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    //查找所有job
    public List<Job> findAll(){
        return jobRepository.findAll();
    }
    public void deleteAll(){
        jobRepository.deleteAll();
    }
    //根据id查找招聘信息
    public Job findJobById(String id){
        Optional<Job> optJob = jobRepository.findById(id);
        if (optJob.isPresent()){
            return optJob.get();
        }
        else
            return null;
    }
    //保存job需要事物支持
    @Transactional
    public void saveJob(Job job){
        jobRepository.save(job);
    }

}
