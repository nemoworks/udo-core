package info.nemoworks.udo.event;

import info.nemoworks.udo.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UdoEvent {

    private Object source;
    private Identifiable identifiable;

}
