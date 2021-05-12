package info.nemoworks.udo.storage;

import java.util.List;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoType;

public interface UdoRepository {

    // for udo
    Udo saveUdo(Udo udo) throws UdoPersistException;

    Udo sync(Udo udo) throws UdoPersistException;

    Udo findUdoById(String id);

    List<Udo> findUdosByType(UdoType udoType);

    void deleteUdoById(String id) throws UdoNotExistException;

    // for type
    List<UdoType> findAllTypes();

    UdoType findTypeById(String id);

    UdoType saveType(UdoType udoType) throws UdoPersistException;

    void deleteTypeById(String id) throws UdoNotExistException;

    List<Udo> findAllUdos();

}