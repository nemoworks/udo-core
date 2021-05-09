package info.nemoworks.udo.event;

import info.nemoworks.udo.model.Identifiable;
//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor
public class UdoEvent {

    private Object source;
    private Identifiable identifiable;

    public UdoEvent(Object source, Identifiable identifiable) {
        this.source = source;
        this.identifiable = identifiable;
    }

    public Object getSource() {
        return source;
    }

    public Identifiable getIdentifiable() {
        return identifiable;
    }

    public void setIdentifiable(Identifiable identifiable) {
        this.identifiable = identifiable;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
