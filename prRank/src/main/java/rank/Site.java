package rank;

import java.util.Objects;

public class Site implements Comparable<Site> {

    private String name;
    private double rank;

    public Site(String name) {
        this.name = name;
        this.rank = 0;
    }

    public String getName() {
        return name;
    }

    public double getRank() {
        return rank;
    }

    public void addRank(double r) {
        this.rank += r;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return name.equalsIgnoreCase(site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name.toLowerCase());
    }

    @Override
    public int compareTo(Site s) {
        return this.name.compareToIgnoreCase(s.getName());
    }

    @Override
    public String toString() {
        return name +
                "(" + rank +
                ")";
    }
}
