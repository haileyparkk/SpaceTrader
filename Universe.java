package objects;

import data.RegionData;

import java.awt.*;
import java.util.Random;

/**
 * class representing the universe
 * @author Joseph Lukemire
 * @version 1.0
 */
public class Universe {
    private static final RegionData[] POSSIBLEREGIONS = RegionData.values();

    private Region[] regions;
    private Random r;
    private int xSize;
    private int ySize;
    private Point[] occupied;

    /**
     * Returns an array of region data
     *
     * @return the possible regions
     */
    public static RegionData[] getPossibleRegions() {
        return POSSIBLEREGIONS;
    }

    /**
     * Returns an array of regions
     *
     * @return an array of regions
     */
    public Region[] getRegions() {
        return regions;
    }

    /**
     * Returns the xSize
     *
     * @return the xSize
     */
    public int getxSize() {
        return xSize;
    }

    /**
     * Returns ySize
     * @return the ySize
     */
    public int getySize() {
        return ySize;
    }

    /**
     * Returns an array of occupied points
     * @return an array of points
     */
    public Point[] getOccupied() {
        return occupied;
    }

    /**
     * constructor for the universe
     * @param builder builds the universe
     */
    private Universe(UniverseBuilder builder) {
        r = new Random();
        this.xSize = builder.xSize;
        this.ySize = builder.ySize;
        occupied = new Point[builder.regionCount];
        regions = new Region[builder.regionCount];
        regions = generateRegions(builder.regionCount);
    }

    /**
     * generates regions
     * @param count the number of regions
     * @return the regions
     */
    private Region[] generateRegions(int count) {
        Region[] generated = new Region[count];

        int winningItemRegion = r.nextInt(count);

        for (int i = 0; i < count; i++) {
            Point location = getUniqueLocation();
            generated[i] = new Region.RegionBuilder()
                    .withName(POSSIBLEREGIONS[i].getName())
                    .withDescription(POSSIBLEREGIONS[i].getDescription())
                    .withTechLevel(r.nextInt(5))   // Will change tech levels to enum when we
                    .isDiscovered(false)                  // know more about them
                    .withLocation(location)
                    .build();

            if (i == winningItemRegion) {
                generated[i].setHasWinningItem(true);
                System.out.println("The winning item has been assigned to " + generated[i].getRegionName());
            }

            occupied[i] = location;
            System.out.println("X: " + location.x + " and Y: " + location.y);
        }
        return generated;
    }

    /**
     * generates a new unique Point for a region to occupy
     * @return the Point
     */
    private Point getUniqueLocation() {
        int xGen = r.nextInt(xSize);
        int yGen = r.nextInt(ySize);

        boolean alreadyExists = false;
        for (int i = 0; i < occupied.length; i++) {
            if (occupied[i] != null && (occupied[i].x == xGen || occupied[i].y == yGen)) {
                alreadyExists = true;
            }
        }
        if (!alreadyExists) {
            return new Point(xGen, yGen);
        }
        return getUniqueLocation();
    }

    /**
     * builds the universe
     */
    public static class UniverseBuilder {
        private int regionCount;
        private int xSize;
        private int ySize;

        /**
         * constructs a universe builder
         * @param count the number of regions
         * @return the UniverseBuilder
         */
        public UniverseBuilder withRegionCount(int count) {
            this.regionCount = count;
            return this;
        }

        /**
         * constructs a universe given a size of the map
         * @param xSize the size of the x-axis
         * @param ySize the size of the y-axis
         * @return the UniverseBuilder
         */
        public UniverseBuilder withMapSize(int xSize, int ySize) {
            this.xSize = xSize;
            this.ySize = ySize;
            return this;
        }

        /**
         * builds the universe
         * @return the universe
         */
        public Universe build() {
            return new Universe(this);
        }
    }
}