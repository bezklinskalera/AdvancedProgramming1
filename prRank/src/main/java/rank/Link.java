package rank;

import java.util.Objects;

public class Link {

    private String origin;
    private String linked;

    public Link(String origin, String linked) {
        this.origin = origin;
        this.linked = linked;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLinked() {
        return linked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return origin.equalsIgnoreCase(link.origin) && linked.equalsIgnoreCase(link.linked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin.toLowerCase(), linked.toLowerCase());
    }

    @Override
    public String toString() {
        return origin +
                "->" + linked;
    }
}
