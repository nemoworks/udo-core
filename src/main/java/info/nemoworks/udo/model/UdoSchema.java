package info.nemoworks.udo.model;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UdoSchema extends Identifiable {

    private JsonObject schema;

    @Override
    public JsonObject toJsonObject() {

        JsonObject json = new JsonObject();

        json.addProperty("id", this.getId());
        json.add("data", this.getSchema());
        return json;
    }
}
