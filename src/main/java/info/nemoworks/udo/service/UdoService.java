package info.nemoworks.udo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoEvent;
import info.nemoworks.udo.model.UdoType;
import info.nemoworks.udo.storage.UdoNotExistException;
import info.nemoworks.udo.storage.UdoPersistException;
import info.nemoworks.udo.storage.UdoRepository;

@Service
public class UdoService {

    private final UdoRepository udoRepository;

    private final UdoEventManager udoEventManager;

    public UdoService(UdoRepository udoRepository, UdoEventManager udoEventManager) {
        this.udoRepository = udoRepository;
        this.udoEventManager = udoEventManager;
    }

    public Udo saveOrUpdateUdo(Udo udo) throws UdoServiceException {
        Udo saved = null;
        try {
            saved = udoRepository.saveUdo(udo);
            udoEventManager.post(new UdoEvent("save", saved, null));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
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
            udoEventManager.post(new UdoEvent("delete", null, id.getBytes()));
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public UdoType saveOrUpdateType(UdoType udoType) throws UdoServiceException {

        UdoType saved = null;
        try {
            saved = udoRepository.saveType(udoType);
            udoEventManager.post(new UdoEvent("save", saved, null));
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
            udoEventManager.post(new UdoEvent("delete", null, id.getBytes()));

        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public Udo syncUdo(Udo udo) throws UdoServiceException {
        Udo saved = null;
        try {
            saved = udoRepository.sync(udo);
            udoEventManager.post(new UdoEvent("sync", udo, null));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be sync");
        }
        return saved;
    }
}