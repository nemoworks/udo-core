package info.nemoworks.udo.event;

import com.google.common.eventbus.EventBus;

import org.springframework.stereotype.Component;

//import lombok.AllArgsConstructor;
//import lombok.Data;

@Component
//@Data
//@AllArgsConstructor
public class UdoEventBus {

    private EventBus bus;

    public void publish(UdoEvent event) {
        this.bus.post(event);
    }

    public void register(Object object) {
        this.bus.register(object);
    }

    public void unregister(Object object) {
        this.bus.unregister(object);
    }

    public UdoEventBus(EventBus bus) {
        this.bus = bus;
    }

    public EventBus getBus() {
        return bus;
    }

    public void setBus(EventBus bus) {
        this.bus = bus;
    }
}
