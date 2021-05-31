package info.nemoworks.udo.service;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoType;
import info.nemoworks.udo.model.event.EventType;
import info.nemoworks.udo.model.event.UdoEvent;
import info.nemoworks.udo.service.eventHandler.UdoEventManager;
import info.nemoworks.udo.storage.UdoNotExistException;
import info.nemoworks.udo.storage.UdoPersistException;
import info.nemoworks.udo.storage.UdoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UdoService {

    private final UdoRepository udoRepository;

    private final UdoEventManager udoEventManager;

    public UdoService(
        @Qualifier("udoWrapperRepository") UdoRepository udoRepository,
        UdoEventManager udoEventManager) {
        this.udoRepository = udoRepository;
        this.udoEventManager = udoEventManager;
    }

    public void createUdoByUri(String uri, String id) {
        Udo saved = new Udo();
        saved.setId(id);
        saved.setUri(uri);
        udoEventManager.post(new UdoEvent(EventType.SAVE_BY_URI, saved, uri.getBytes()));
    }

    public Udo saveOrUpdateUdoByUri(Udo udo, byte[] payload) throws UdoServiceException {
        try {
            udoRepository.saveUdo(udo);
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
        }
        udoEventManager.post(new UdoEvent(EventType.SAVE, udo, payload));
        return udo;
    }

    public Udo saveOrUpdateUdo(Udo udo) throws UdoServiceException, UdoNotExistException {
        Udo saved = null;
        boolean created = false;
        if (udo.getId() != null) {
            saved = udoRepository.findUdoById(udo.getId());
            if (saved == null) {
                created = true;
            }
        } else {
            created = true;
        }
//        if (udo.getId() == null) {
//            created = true;
//        }
        try {
            saved = udoRepository.saveUdo(udo);
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
        }
        if (created) {
            udoEventManager.post(new UdoEvent(EventType.SAVE, saved, null));
        } else {
            udoEventManager.post(new UdoEvent(EventType.UPDATE, saved, null));
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
            udoEventManager.post(new UdoEvent(EventType.DELETE, null, id.getBytes()));
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public UdoType saveOrUpdateType(UdoType udoType) throws UdoServiceException {
        UdoType saved = null;
        try {
            saved = udoRepository.saveType(udoType);
            return saved;
        } catch (UdoPersistException e) {
            throw new UdoServiceException("canot save/update");
        }
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
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public Udo syncUdo(Udo udo) throws UdoServiceException {
        Udo saved = null;
        try {
            saved = udoRepository.sync(udo);
            udoEventManager.post(new UdoEvent(EventType.SYNC, udo, null));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be sync");
        }
        return saved;
    }
}
