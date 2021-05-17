package info.nemoworks.udo.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Udo extends Identifiable {

    private UdoType type;

    private JsonElement data;

    public Udo(UdoType type, JsonElement data) {
        super();
        this.type = type;
        this.data = data;
    }

    @Override
    public JsonObject toJsonObject() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(this);
        return (JsonObject) jsonElement;
    }

}
