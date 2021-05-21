package info.nemoworks.udo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UdoEvent {

    private EventType contextId;
    private Identifiable source;
    private byte[] payload;

}
