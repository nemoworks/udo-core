package info.nemoworks.udo.model;

import java.util.concurrent.atomic.AtomicInteger;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor

public class Meter {
    private AtomicInteger value;

    public Meter(AtomicInteger value) {
        this.value = value;
    }

    public AtomicInteger getValue() {
        return value;
    }

    public void setValue(AtomicInteger value) {
        this.value = value;
    }
}
