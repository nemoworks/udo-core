package info.nemoworks.udo.model.event;

import info.nemoworks.udo.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageEvent extends UdoEvent{
    public StorageEvent(EventType contextId, Identifiable source, byte[] payload) {
        super(contextId, source, payload);
    }
}
