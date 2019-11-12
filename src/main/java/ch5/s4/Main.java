package ch5.s4;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private Disruptor<PCData> disruptor;

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();

        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<PCData>(factory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy()
                );
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        disruptor.start();

        // 1个生产者 4个消费者
        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data "+l);
        }
    }
}
