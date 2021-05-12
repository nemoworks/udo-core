package info.nemoworks.udo.model;


import com.google.gson.JsonObject;
import lombok.Data;

@Data
public abstract class Identifiable {

    private String id;

    public abstract JsonObject toJsonObject();

}
