package info.nemoworks.udo.service.eventHandler;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonElement;
import info.nemoworks.udo.model.event.SyncEvent;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.service.UdoService;
import info.nemoworks.udo.service.UdoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncEventHandler {
    @Autowired
    UdoService udoService;

    @Subscribe
    public void syncEvent(SyncEvent syncEvent) {
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
