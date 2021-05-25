package info.nemoworks.udo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveByUriEvent {
    private EventType contextId;
    private Identifiable source;
    private byte[] payload;
}
