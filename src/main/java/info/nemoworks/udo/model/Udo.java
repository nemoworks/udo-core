package info.nemoworks.udo.model;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Udo extends DigitalObject {

    private UdoSchema schema;

    private JsonNode data;

}
