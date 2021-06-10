package info.nemoworks.udo.service.eventHandler;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonElement;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.event.SyncEvent;
import info.nemoworks.udo.service.UdoService;
import info.nemoworks.udo.service.UdoServiceException;
import info.nemoworks.udo.storage.UdoNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncEventHandler {

    @Autowired
    UdoService udoService;

    @Subscribe
    public void syncEvent(SyncEvent syncEvent) throws UdoServiceException, UdoNotExistException {
        Udo udo = (Udo) syncEvent.getSource();
        JsonElement udoData = udo.getData();
        Udo udo1 = udoService.getUdoById(udo.getId());
        if(!udo1.getData().equals(udo.getData())){
            udo1.setData(udoData);
            udoService.saveOrUpdateUdo(udo1);
        }
    }


}
