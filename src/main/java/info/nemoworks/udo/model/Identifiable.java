package info.nemoworks.udo.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;

@Data
public abstract class Identifiable {

    private String id;

    public abstract ObjectNode toJsonObject();

}
