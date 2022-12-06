//package com.example.springbatchtest.listener;
//
//import com.example.springbatchtest.controller.HoldingReconExceptionsJobController;
//import com.example.springbatchtest.entity.HoldingsReconExceptionEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//
//import javax.batch.api.chunk.listener.ChunkListener;
//import javax.batch.api.chunk.listener.ItemReadListener;
//import javax.batch.api.chunk.listener.ItemWriteListener;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class GeneralListener implements ItemReadListener, ItemWriteListener, StepExecutionListener, ChunkListener
//{
//
//    private static final Logger log = LoggerFactory.getLogger(GeneralListener.class);
//
//    public AtomicLong counter = new AtomicLong(0);
//    public AtomicLong counterChunk = new AtomicLong(0);
//
//    public AtomicLong getCounter()
//    {
//        return counter;
//    }
//
//    @Override
//    public void beforeRead() throws Exception
//    {
//        log.info("beforeRead()");
//        System.out.println("akjcnsmlk");
//    }
//
//    @Override
//    public void afterRead(Object item) throws Exception
//    {
//        log.info("afterRead() {}",item);
//        long l = counter.incrementAndGet();
//        log.info("General listener afterRead" + l);
//    }
//
//    @Override
//    public void onReadError(Exception ex) throws Exception
//    {
//
//    }
//
//    @Override
//    public void beforeWrite(List<Object> items) throws Exception
//    {
//        items.forEach(o -> {
//            log.info("beforeWrite {}", o);
//        });
//
//    }
//
//    @Override
//    public void afterWrite(List<Object> items) throws Exception
//    {
//        items.forEach(o -> {
//            log.info("afterWrite {}", o);
//        });
//    }
//
//    @Override
//    public void onWriteError(List<Object> items, Exception ex) throws Exception
//    {
//
//    }
//
//    @Override
//    public void beforeStep(StepExecution stepExecution)
//    {
//
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution)
//    {
//        return null;
//    }
//
//    @Override
//    public void beforeChunk() throws Exception
//    {
//
//    }
//
//    @Override
//    public void onError(Exception ex) throws Exception
//    {
//
//    }
//
//    @Override
//    public void afterChunk() throws Exception
//    {
////        log.info("afterChunk() ");
////        long l = counterChunk.incrementAndGet();
////        log.info("chunkCounter {}",  l);
//    }
//}
