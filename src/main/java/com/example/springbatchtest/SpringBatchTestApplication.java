package com.example.springbatchtest;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableBatchProcessing
//@EnableScheduling
@ComponentScan(basePackages = "com.example.springbatchtest.*")
public class SpringBatchTestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBatchTestApplication.class, args);

    }
}
