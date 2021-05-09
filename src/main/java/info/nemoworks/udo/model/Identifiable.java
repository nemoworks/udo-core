package info.nemoworks.udo.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

//import lombok.Data;

//@Data
public abstract class Identifiable {

    private String id;

    public abstract ObjectNode toJsonObject();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
