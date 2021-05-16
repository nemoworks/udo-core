package info.nemoworks.udo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UdoEvent {

    private String contextId;
    private Identifiable source;
    private byte[] payload;

}
