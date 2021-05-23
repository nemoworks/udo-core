package info.nemoworks.udo.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonElement;
import info.nemoworks.udo.model.EventType;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UdoEventManager {

    @Autowired
    private EventBus eventBus;

    public void register(Object listener) {
        eventBus.register(listener);
    }

    public void unregister(Object listener) {
        eventBus.unregister(listener);
    }

    public void post(UdoEvent event) {
        eventBus.post(event);
    }



}
