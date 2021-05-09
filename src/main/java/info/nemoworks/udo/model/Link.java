package info.nemoworks.udo.model;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor
public class Link {

    public enum LinkType {
        OBJ, OBJS
    }

    private String name;
    private LinkType linkType;
//    private UdoSchema from;
//    private UdoSchema to;
    private String to;

    public Link(String name, LinkType linkType, String to) {
        this.name = name;
        this.linkType = linkType;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
