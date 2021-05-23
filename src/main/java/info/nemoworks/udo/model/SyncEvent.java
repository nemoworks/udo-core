package info.nemoworks.udo.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SyncEvent extends UdoEvent{

    private EventType contextId;
    private Identifiable source;
}
