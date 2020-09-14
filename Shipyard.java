package objects;

/**
 * class representing a shipyard
 * @author Andrew Harris
 * @version 1.0
 */
public class Shipyard {
    private objects.Region region;

    public Shipyard(objects.Region region) {
        this.region = region;
    }

    public objects.Region getRegion() {
        return region;
    }
}
