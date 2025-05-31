package rank;

public class WebExtended extends Web{


    public WebExtended(){
    super();
    }

    protected void addSiteWithName(String name){
        SiteExtended siteExtended = new SiteExtended(name);
        super.addSite(siteExtended);
    }

    protected void distribute(Site site, double prize){

        SiteExtended siteEx = (SiteExtended)site;

        if(siteEx.isValid()){
            super.distribute(siteEx, prize);
        }
        else{
            return;
        }
    }

    public void switchSiteWithName(String name){
        for(Site site : sites){
            SiteExtended siteEx = (SiteExtended)site;
            if(siteEx.getName().equals(name)){
                siteEx.setValid(!siteEx.isValid());
                break;
            }
        }
    }

}
