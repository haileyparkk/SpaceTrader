package data;

/**
 * Region Data enum
 * 
 * @author jlukemire
 * @version 1.0
 */
public enum RegionData {
    BADLANDS("Badlands", "These are the Badlands"),
    BAJORANWORMHOLD("Bajoran Wormhole", "This is the Bajoran wormhole"),
    BRIARPATCH("Briar Patch", "This is the Briar Patch"),
    DELPHICEXPANSE("Delphic Expanse", "This is the Delphic Expanse"),
    GALACTICBARRIER("Galactic Barrier", "This is the Galactic Barrier"),
    GALACTICCORE("Galactic Core", "This is the Galactic Core"),
    NEKRIT("Nekrit Expanse", "This is the Nekrit Expanse"),
    ALASTOR("Alastor Cluster", "This is the Alastor Cluster"),
    GAEAN("Gaean Reach", "This is the Gaean Reach"),
    JOEYVILLE("Joeyville", "This is Joeyville");

    private String name;
    private String description;

    /**
     * Constructor that initializes a region with a name and description
     * @param name the name of the region
     * @param description the description of the region
     */
    RegionData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
