package info.nemoworks.udo.model.event;

import info.nemoworks.udo.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@Data
@AllArgsConstructor
public class SaveByUriEvent extends StorageEvent{

    private URI uri;
    public SaveByUriEvent(Identifiable source, byte[] payload, EventType contextId,URI uri) {
        super(contextId,source, payload);
        this.uri = uri;
    }
}
