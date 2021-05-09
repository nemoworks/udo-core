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

    private UdoSchema schema;

    private JsonNode data;

    public Udo(UdoSchema schema, JsonNode data) {
        this.schema = schema;
        this.data = data;
    }

    @Override
    public ObjectNode toJsonObject() {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode udoNode = mapper.createObjectNode();
        udoNode.put("id", this.getId());
        udoNode.set("schema", this.getSchema().toJsonObject());
        udoNode.set("data", this.getData());

        return udoNode;
    }

    public JsonNode getData() {
        return data;
    }

    public UdoSchema getSchema() {
        return schema;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public void setSchema(UdoSchema schema) {
        this.schema = schema;
    }


}
