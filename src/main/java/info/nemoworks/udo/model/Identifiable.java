package info.nemoworks.udo.model;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public abstract class Identifiable {

    private String id;

    public abstract JsonObject toJsonObject();

    private MetaInfo metaInfo;

    private ContextInfo contextInfo;

    public Identifiable() {
        this.metaInfo = new MetaInfo() {
            {
                this.createdBy = "nemoworks";
            }
        };

        this.contextInfo = new ContextInfo();
    }

}
