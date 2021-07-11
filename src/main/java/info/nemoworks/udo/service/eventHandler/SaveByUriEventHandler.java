package info.nemoworks.udo.service.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
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
    public void saveByUriEvent(SaveByUriEvent saveByUriEvent)
        throws JsonProcessingException, UdoServiceException {
        System.out.println("In SBUri Subscribe");
        Udo udo = (Udo) saveByUriEvent.getSource();
//        udo.setType(udo.inferType());
        JsonObject data = (JsonObject) udo.getData();
//        JsonObject subData = data.getAsJsonObject("data");
        data.add("location", (JsonObject) udo.getContextInfo().getContext("location"));
        data.addProperty("avatarUrl", udo.getContextInfo().getContext("avatarUrl").toString());
//        data.add("data", subData);
        udo.setData(data);
        udo.setType(udoService.saveOrUpdateType(udo.inferType()));
        udoService.saveByUri(udo, saveByUriEvent.getPayload());
    }
}
