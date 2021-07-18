package info.nemoworks.udo.model.event;

import info.nemoworks.udo.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckByFilterEvent extends StorageEvent {

    public CheckByFilterEvent(EventType contextId, Identifiable source, byte[] payload) {
        super(contextId, source, payload);
    }

}
