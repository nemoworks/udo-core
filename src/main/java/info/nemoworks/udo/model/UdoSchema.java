package info.nemoworks.udo.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//@Data
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class UdoSchema extends Identifiable {

    private JsonNode schema;

    public UdoSchema(String Id, JsonNode schema) {
        this.setId(Id);
        this.schema = schema;
    }

    @Override
    public ObjectNode toJsonObject() {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode schemaNode = mapper.createObjectNode();
        schemaNode.put("id", this.getId());
        schemaNode.set("data", this.getSchema());
        return schemaNode;
    }

    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }

    public JsonNode getSchema() {
        return schema;
    }
}
