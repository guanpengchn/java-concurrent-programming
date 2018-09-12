package ch5.s4;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
