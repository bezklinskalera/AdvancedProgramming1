package rank;

import java.util.*;

public class Web {

    private final double THRESHOLD = 1.0e-5;
    private Random rndm;
    protected SortedSet<Site> sites;
    private Set<Link> links;

    public Web() {
        this.sites = new TreeSet<Site>();
        this.links = new HashSet<Link>();
        this.rndm = new Random(1);
    }

    protected  void addSite(Site s) {
        sites.add(s);
    }

    protected void addSiteWithName(String name){
        sites.add(new Site(name));
    }

    public void addLink(String dataLink){
        String[] data = dataLink.split("->");

        if(!(data.length == 2)){
            throw new IllegalArgumentException(dataLink);
        }

        addSiteWithName(data[0]);
        addSiteWithName(data[1]);
        Link link = new Link(data[0], data[1]);
        links.add(link);
    }

    public Site getSite(String name){
        for(Site s: sites){
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
            throw new NoSuchElementException(name);
    }


    public Set<String> getNames(){
        Set<String> names = new HashSet<>();
        for(Site s: sites){
            names.add(s.getName());
        }
        return names;
    }

    private Set<Site> getSitesLinkedFrom(Site page){

        Set<Site> linkedSites = new HashSet<>();

        for(Link link: links){
            if(link.getOrigin().equalsIgnoreCase(page.getName())){
                Site site = getSite(link.getLinked());
                linkedSites.add(site);
            }
        }
        return linkedSites;
    }

    protected void distribute(Site site, double prize){

        if (prize < THRESHOLD) {
            return;}

            double halfPrize = prize / 2;
            site.addRank(halfPrize);
            Set<Site> sitesNew = getSitesLinkedFrom(site);
            int n = sitesNew.size();
            if (n==0){
                return;
            }
            else {
                double half = prize / (2*n);
                for (Site linked : sitesNew) {
                    distribute(linked, half);
                }
            }
    }

    public void click(String name){
        try {
            Site site = getSite(name);
            distribute(site, 1.0);
        }catch (NoSuchElementException e){
        }
    }

    public void simulateClick(int numClick) {
        if (sites.isEmpty()) {
            return;
        }
        List<Site> sitesList = new ArrayList<>(sites);

        for (int i = 0; i < numClick; i++) {
            int index = rndm.nextInt(sitesList.size());
            Site randomSite = sitesList.get(index);
            click(randomSite.getName());
        }
    }


    public SortedSet<Site> getSitesByName() {
        return new TreeSet<>(sites);
    }

    public SortedSet<Site> getSitesByRank() {
        Comparator<Site> comp = (s1, s2) -> {
            int cmp = Double.compare(s2.getRank(), s1.getRank());
            if (cmp != 0) return cmp;
            return s1.compareTo(s2);
        };
        SortedSet<Site> sortedByRank = new TreeSet<>(comp);
        sortedByRank.addAll(sites);
        return sortedByRank;
    }

    @Override
    public String toString() {
        return "Web(" + sites + ", " + links + ")";
    }




}
