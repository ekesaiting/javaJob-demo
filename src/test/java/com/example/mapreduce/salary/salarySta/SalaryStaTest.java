package com.example.mapreduce.salary.salarySta;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryStaTest {

    @Test
    public void test01() throws Exception {
        SalarySta salarySta = new SalarySta();
        salarySta.run();
    }
}