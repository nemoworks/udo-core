package info.nemoworks.udo.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

public class UdoTests {

    @Test
    public void testUdoSchema() throws JsonParseException, IOException {

        String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonString);


        UdoSchema schema = new UdoSchema(actualObj);
        schema.setId("schema-1");

        Udo udo = new Udo(schema, actualObj);
        udo.setId("udo-1");


        System.out.println(schema.toJsonObject());
        System.out.println(udo.toJsonObject());

        assertNotNull(schema.toJsonObject());

    }

}
