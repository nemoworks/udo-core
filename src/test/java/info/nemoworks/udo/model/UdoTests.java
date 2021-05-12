package info.nemoworks.udo.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.jupiter.api.Test;

public class UdoTests {

    @Test
    public void testUdoSchema() {

        String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        UdoSchema schema = new UdoSchema(jsonObject);
        schema.setId("schema-1");

        Udo udo = new Udo(schema, jsonObject);
        udo.setId("udo-1");

        assertNotNull(schema.toJsonObject());

    }

}
