package info.nemoworks.udo.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UdoTests {

    @Test
    public void testUdoSchema() {

        String jsonString = "{'id': 1001, " + "'firstName': 'Lokesh'," + "'lastName': 'Gupta',"
                + "'email': 'howtodoinjava@gmail.com'}";

        JsonObject data = new Gson().fromJson(jsonString, JsonObject.class);

        UdoType type = new UdoType(data);
        type.setId("type-1");

        JsonObject object = type.toJsonObject();
        System.out.println(object);

        assertNotNull(type.toJsonObject());

    }

    @Test
    public void testInfer() throws IOException, JSONException {
        String json = this.loadFromFile("src/test/resources/test0.json");
        JsonObject obj = new Gson().fromJson(json, JsonObject.class);
        Udo udo = new Udo(obj);
        System.out.println("result 1: " + udo.getType().getSchema().toString());
//        String result = JsonSchemaGenerator.outputAsString("udoType", "this is a test",
//                new Gson().toJson(udo.getData()));
//        System.out.println("result 2: " + result.replace(",}", "}"));
    }

    public String loadFromFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
