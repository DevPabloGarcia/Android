package garcia.pablo.recyclerviewadvance.model;

/**
 * Created by V on 29/1/16.
 */
public class Boy extends Person {

    Boolean bearded;

    public Boy(String name, boolean bearded) {
        super(name);
        this.bearded = bearded;
    }

    public boolean isBearded(){
        return bearded;
    }

    public Boolean getBearded() {
        return bearded;
    }

    public void setBearded(Boolean bearded) {
        this.bearded = bearded;
    }
}
