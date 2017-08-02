package com.wangyin.seapay.loginkgo;

import com.wangyin.seapay.loginkgo.constant.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by hanxiaofei on 2017/7/17.
 */
public abstract class LoggerHelper {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerHelper.class);

    public synchronized static void PEPSI(final String info){
        Print print = new Print(info);
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(print);
        Thread begain = new Thread(futureTask);
        begain.start();
        System.out.println();
        try {
            futureTask.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOG.error("log send thread interrupted{}",e.getMessage());
        } catch (ExecutionException e) {
            LOG.error("log send thread execute error{}",e.getMessage());
        } catch (TimeoutException e) {
            LOG.error("log send thread connect kafka timeout,please check{}","=============___________=========");
        }
    }

    static class Print implements Callable<Boolean> {
        private String info;
        public Print(final String info){
            this.info = info;
        }

        @Override
        public Boolean call() throws Exception {
            try{
                LOG.warn(Markers.CEO,info);
                return true;
            }catch (final Exception e){
                LOG.error(e.getMessage());
                return false;
            }
        }
    }
}
