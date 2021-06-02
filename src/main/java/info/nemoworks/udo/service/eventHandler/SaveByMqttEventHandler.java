package info.nemoworks.udo.service.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.eventbus.Subscribe;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.event.SaveByMqttEvent;
import info.nemoworks.udo.service.UdoService;
import info.nemoworks.udo.service.UdoServiceException;
import info.nemoworks.udo.storage.UdoNotExistException;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveByMqttEventHandler {
    @Autowired
    UdoService udoService;

    @Subscribe
    public void saveByMqttEvent(SaveByMqttEvent saveByMqttEvent) throws JsonProcessingException {
        System.out.println("In SBUri Subscribe");
        Udo udo = (Udo) saveByMqttEvent.getSource();
        udo.setType(udo.inferType());
        try {
            udoService.saveOrUpdateUdo(udo);
        } catch (UdoServiceException | UdoNotExistException e) {
            e.printStackTrace();
        }
    }
}