package garcia.pablo.recyclerviewadvance.model;

/**
 * Created by V on 29/1/16.
 */
public class Girl extends Person {

    boolean beautiful;

    public Girl(String name, boolean beautiful) {
        super(name);
    }

    public boolean isBeautiful() {
        return beautiful;
    }

    public void setBeautiful(boolean beautiful) {
        this.beautiful = beautiful;
    }
}
