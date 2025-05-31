package rank;

public class SiteExtended extends Site {

    private boolean valid;

    public SiteExtended(String name) {
        super(name);
        this.valid = true;
    }

    public void setValid(boolean b){
        this.valid = b;
    }

    public boolean isValid(){
        return this.valid;
    }

    @Override
    public String toString() {
        if (this.valid) {
            return super.toString();
        }
        else {
            return super.toString() + "*";
        }
    }
}
