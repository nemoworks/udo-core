package info.nemoworks.udo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import info.nemoworks.udo.event.UdoEvent;
import info.nemoworks.udo.event.UdoEventBus;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
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

    public List<Udo> getUdoBySchema(UdoSchema schema) {
        return udoRepository.findUdosBySchema(schema);
    }

    public void deleteUdoById(String id) throws UdoServiceException {
        try {
            udoRepository.deleteUdoById(id);
        } catch (UdoNotExistException e) {
            throw new UdoServiceException("not exist");
        }
    }

    public UdoSchema saveOrUpdateSchema(UdoSchema schema) throws UdoServiceException {

        UdoSchema saved = null;
        try {
            saved = udoRepository.saveSchema(schema);
            udoEventBus.publish(new UdoEvent("save", saved));
        } catch (UdoPersistException e) {
            throw new UdoServiceException("canot save/update");
        }
        return saved;

    }

    public UdoSchema getSchemaById(String id) {
        return udoRepository.findSchemaById(id);
    }

    public List<UdoSchema> getAllSchemas() {
        return udoRepository.findAllSchemas();
    }

    public UdoSchema getSchemaForUdo(Udo udo) {
        return udoRepository.findSchemaById(udo.getSchema().getId());
    }

    public void deleteSchemaById(String id) throws UdoServiceException {
        try {
            udoRepository.deleteSchemaById(id);
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