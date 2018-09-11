package ch5.s4;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --" + event.get() * event.get() + "--");
    }
}
