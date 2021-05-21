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

    @Autowired
    private UdoService udoService;

    public void register(Object listener) {
        eventBus.register(listener);
    }

    public void unregister(Object listener) {
        eventBus.unregister(listener);
    }

    public void post(UdoEvent event) {
        eventBus.post(event);
    }


    @Subscribe
    public void syncEvent(UdoEvent syncEvent) {
        assert (syncEvent.getContextId().equals(EventType.SYNC));
        System.out.println("eync event handler");
        System.out.println(syncEvent);
        Udo udo = (Udo) syncEvent.getSource();
        JsonElement udoData = udo.getData();
        Udo udo1 = udoService.getUdoById(udo.getId());
        udo1.setData(udoData);
        try {
            udoService.saveOrUpdateUdo(udo1);
        } catch (UdoServiceException e) {
            e.printStackTrace();
        }
    }


}
