package pl.eurobank.tfi.simulator;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;
import static org.junit.Assert.*;

public class EventBusTest {

    private boolean eventListenerCalled;

    @Test
    public void listener_should_be_notified() {
        EventBus eventBus = new EventBus();

        eventBus.register(new SampleEventListener());

        eventListenerCalled = false;
        eventBus.post(new SampleEvent());

        assertTrue(eventListenerCalled);
    }

    class SampleEvent {
    }

    class SampleEventListener {

        @Subscribe
        public void registerSampleEvent(SampleEvent event) {
            eventListenerCalled = true;
        }
    }
}
