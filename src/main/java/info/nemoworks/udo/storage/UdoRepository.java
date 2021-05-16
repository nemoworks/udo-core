package info.nemoworks.udo.storage;

import java.util.List;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoType;

public interface UdoRepository {

    // for udo
    Udo saveUdo(Udo udo) throws UdoPersistException;

    Udo sync(Udo udo) throws UdoPersistException;

    Udo findUdoById(String id) throws UdoNotExistException;

    List<Udo> findUdosByType(UdoType udoType);

    List<Udo> findUdosByTypeId(String udoTypeId);

    void deleteUdoById(String id) throws UdoNotExistException;

    List<Udo> findAllUdos();
    // for type
    List<UdoType> findAllTypes();

    UdoType findTypeById(String id) throws UdoNotExistException;

    UdoType saveType(UdoType udoType) throws UdoPersistException;

    void deleteTypeById(String id) throws UdoNotExistException;



}