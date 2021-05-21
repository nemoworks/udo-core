package info.nemoworks.udo.service;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonElement;
import info.nemoworks.udo.model.SyncEvent;
import info.nemoworks.udo.model.Udo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncEventHandler {
    @Autowired
    UdoService udoService;

    @Subscribe
    public void syncEvent(SyncEvent syncEvent){
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
