package info.nemoworks.udo.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UdoType extends Identifiable {

    private JsonObject schema;

    @Override
    public JsonObject toJsonObject() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(this);
        return (JsonObject) jsonElement;

    }
}