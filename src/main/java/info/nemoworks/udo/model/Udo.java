package info.nemoworks.udo.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import net.sf.json.*;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;

//@Data
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class Udo extends Identifiable {

    private String schemaId;

    private JsonNode data;

    public Udo(String schemaId, JsonNode data) {
        this.schemaId = schemaId;
        this.data = data;
    }

    @Override
    public ObjectNode toJsonObject() {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode udoNode = mapper.createObjectNode();
        udoNode.put("id", this.getId());
        udoNode.put("schemaId", this.getSchemaId());
        udoNode.set("data", this.getData());

        return udoNode;
    }

    public JsonNode getData() {
        return data;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }


}
