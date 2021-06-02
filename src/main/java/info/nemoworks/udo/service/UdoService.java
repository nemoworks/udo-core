package info.nemoworks.udo.service;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoType;
import info.nemoworks.udo.model.event.EventType;
import info.nemoworks.udo.model.event.GatewayEvent;
import info.nemoworks.udo.service.eventHandler.UdoEventManager;
import info.nemoworks.udo.storage.UdoNotExistException;
import info.nemoworks.udo.storage.UdoPersistException;
import info.nemoworks.udo.storage.UdoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdoService {

    private final UdoRepository udoRepository;

    private final UdoEventManager udoEventManager;

    public UdoService(@Qualifier("udoWrapperRepository") UdoRepository udoRepository, UdoEventManager udoEventManager) {
        this.udoRepository = udoRepository;
        this.udoEventManager = udoEventManager;
    }

    public void createUdoByUri(String uri, String id) throws UdoServiceException {
        Udo saved = new Udo();
        saved.setId(id);
        saved.setUri(uri);
        udoEventManager.post(new GatewayEvent(EventType.SAVE_BY_URI, saved, uri.getBytes()));
    }

    public Udo saveUdo(Udo udo, byte[] payload) throws UdoServiceException {
        Udo saved = null;
        boolean created = false;
        if (udo.getId() == null) {
            created = true;
        }
        try {
            saved = udoRepository.saveUdo(udo);
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
        }
        if (created) {
            udoEventManager.post(new GatewayEvent(EventType.SAVE, saved, payload));
        }
        return saved;
    }

    public Udo saveOrUpdateUdo(Udo udo) throws UdoServiceException {
        Udo saved = null;
        boolean created = false;
        if (udo.getId() == null) {
            created = true;
        }
        try {
            saved = udoRepository.saveUdo(udo);
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
        }
        if (created) {
            udoEventManager.post(new GatewayEvent(EventType.SAVE, saved, null));
        } else {
            udoEventManager.post(new GatewayEvent(EventType.UPDATE, saved, null));
        }
        return saved;
    }

    public Udo getUdoById(String id) {
        try {
            return udoRepository.findUdoById(id);
        } catch (UdoNotExistException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Udo> getUdoByType(UdoType udoType) {
        return udoRepository.findUdosByType(udoType);
    }

    public void deleteUdoById(String id) throws UdoServiceException {
        try {
            udoRepository.deleteUdoById(id);
            udoEventManager.post(new GatewayEvent(EventType.DELETE, null, id.getBytes()));
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public UdoType saveOrUpdateType(UdoType udoType) throws UdoServiceException {

        UdoType saved = null;
        try {
            saved = udoRepository.saveType(udoType);
        } catch (UdoPersistException e) {
            throw new UdoServiceException("canot save/update");
        }
        return saved;

    }

    public UdoType getTypeById(String id) {
        try {
            return udoRepository.findTypeById(id);
        } catch (UdoNotExistException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UdoType> getAllTypes() {
        return udoRepository.findAllTypes();
    }

    public UdoType getTypeForUdo(Udo udo) {
        try {
            return udoRepository.findTypeById(udo.getType().getId());
        } catch (UdoNotExistException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTypeById(String id) throws UdoServiceException {
        try {
            udoRepository.deleteTypeById(id);
            udoEventManager.post(new GatewayEvent(EventType.DELETE, null, id.getBytes()));

        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

}