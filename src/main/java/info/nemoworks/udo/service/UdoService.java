package info.nemoworks.udo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import info.nemoworks.udo.event.UdoEvent;
import info.nemoworks.udo.event.UdoEventBus;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoType;
import info.nemoworks.udo.storage.UdoNotExistException;
import info.nemoworks.udo.storage.UdoPersistException;
import info.nemoworks.udo.storage.UdoRepository;

@Service
public class UdoService {

    private final UdoRepository udoRepository;

    private final UdoEventBus udoEventBus;

    public UdoService(UdoRepository udoRepository, UdoEventBus udoEventBus) {
        this.udoRepository = udoRepository;
        this.udoEventBus = udoEventBus;
    }

    public Udo saveOrUpdateUdo(Udo udo) throws UdoServiceException {
        Udo saved = null;
        try {
            saved = udoRepository.saveUdo(udo);
            udoEventBus.publish(new UdoEvent("save", udo));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be saved");
        }
        return saved;
    }

    public Udo getUdoById(String id) {
        return udoRepository.findUdoById(id);
    }

    public List<Udo> getUdoByType(UdoType udoType) {
        return udoRepository.findUdosByType(udoType);
    }

    public void deleteUdoById(String id) throws UdoServiceException {
        try {
            udoRepository.deleteUdoById(id);
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public UdoType saveOrUpdateType(UdoType udoType) throws UdoServiceException {

        UdoType saved = null;
        try {
            saved = udoRepository.saveType(udoType);
            udoEventBus.publish(new UdoEvent("save", saved));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("canot save/update");
        }
        return saved;

    }

    public UdoType getTypeById(String id) {
        return udoRepository.findTypeById(id);
    }

    public List<UdoType> getAllTypes() {
        return udoRepository.findAllTypes();
    }

    public UdoType getTypeForUdo(Udo udo) {
        return udoRepository.findTypeById(udo.getType().getId());
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
            udoEventBus.publish(new UdoEvent("sync", udo));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("Udo (" + udo.getId() + ") cannot be sync");
        }
        return saved;
    }
}