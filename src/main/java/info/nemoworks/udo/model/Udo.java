package info.nemoworks.udo.model;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Udo extends Identifiable {

    private UdoSchema schema;

    private JsonObject data;

    @Override
    public JsonObject toJsonObject() {

        JsonObject json = new JsonObject();
        json.addProperty("id", this.getId());
        json.add("schema", this.getSchema().toJsonObject());

        json.add("data", this.getData());

        return json;
    }

}
