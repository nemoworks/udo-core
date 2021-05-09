package info.nemoworks.udo.storage;

import java.util.List;

import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;

public interface UdoRepository {

    // for udo
    Udo saveUdo(Udo udo) throws UdoPersistException;

    Udo sync(Udo udo) throws UdoPersistException;

    Udo findUdoById(String id);

    List<Udo> findUdosBySchema(String schemaId);

    void deleteUdoById(String id) throws UdoNotExistException;

    // for schema
    List<UdoSchema> findAllSchemas();

    UdoSchema findSchemaById(String id);

    UdoSchema saveSchema(UdoSchema udoSchema) throws UdoPersistException;

    void deleteSchemaById(String id) throws UdoNotExistException;

}