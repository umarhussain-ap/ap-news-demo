package com.systems.demo.apnewsdemo.problem.solving;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

@Slf4j
public class ModulusTest {

    public static void main(String[] args) throws InterruptedException {
        int offset = 0;
        int limit = 300;
        int minutes = 1;

        int chunkSize = 50;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


            while (offset < limit) {
                log.info("offset: {}, chunk: {}", offset, chunkSize);
                offset = nextOffset(offset, chunkSize);

                Thread.sleep(30000);

                if (shouldInterrupt(stopWatch, minutes)) {
                    log.info("watch.getTime() {}  max minutes {}", stopWatch.getTime(), minutes);
                    log.info("breaking after reaching offset: {}, chunk: {}", offset, chunkSize);
                    break;
                }
            }

    }

    private static boolean shouldInterrupt(StopWatch watch, Integer minutes) {
        return watch.getTime() >= TimeUnit.MINUTES.toMillis(minutes);
    }

    private static int nextOffset(int currentOffset, int chunk){
       return currentOffset+chunk;
    }

}
