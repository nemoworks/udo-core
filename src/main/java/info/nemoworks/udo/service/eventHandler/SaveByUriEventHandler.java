package info.nemoworks.udo.service.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.eventbus.Subscribe;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.event.SaveByUriEvent;
import info.nemoworks.udo.service.UdoService;
import info.nemoworks.udo.service.UdoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveByUriEventHandler {

    @Autowired
    UdoService udoService;

    @Subscribe
    public void saveByUriEvent(SaveByUriEvent saveByUriEvent) throws JsonProcessingException {
        System.out.println("In SBUri Subscribe");
        Udo udo = (Udo) saveByUriEvent.getSource();
        udo.setType(udo.inferType());
        try {
            udoService.saveOrUpdateType(udo.getType());
            udoService.saveOrUpdateUdoByUri(udo, saveByUriEvent.getPayload());
        } catch (UdoServiceException e) {
            e.printStackTrace();
        }
    }
}
