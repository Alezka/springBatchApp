package com.example.springbatchtest.listener;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component("HoldingExceptionJobListener")

public class HoldingExceptionJobListener extends JobExecutionListenerSupport implements ItemReadListener
{

    public AtomicLong counter = new AtomicLong(0);

    private static final Logger log = LoggerFactory.getLogger(HoldingExceptionJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution)
    {
        log.info("Job is Started with status - {}.\n StartTime {}", jobExecution.getStatus(), jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution)
    {
        final Date startTime = jobExecution.getStartTime();
        final Date endTime = jobExecution.getEndTime();

        long executionTime = endTime.getTime() - startTime.getTime();
        log.info("Job is completed with status - {}. Total time to execute the job - {}.\n StartTime - {}\n EbdTime - {} ",
                jobExecution.getStatus(), executionTime, startTime, endTime);
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        log.info(String.valueOf(stepExecutions));
    }

    @Override
    public void beforeRead()
    {
        log.info("beforeRead()");
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(Object item)
    {

    }

    @Override
    public void onReadError(Exception ex)
    {
        System.out.println(ex);
    }

}