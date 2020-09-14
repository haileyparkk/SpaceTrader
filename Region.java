package objects;

import data.Good;
import data.GoodList;
import data.PriceGenerator;
import data.Upgrade;

import java.awt.*;
import java.util.Random;

/**
 * class representing the region
 * @author Joseph Lukemire, Andrew Harris
 * @version 1.1
 */
public class Region {
    private String regionName;
    private String description;
    private int techLevel;
    private boolean isDiscovered;
    private Point location;
    private PriceGenerator prices;
    private Good[] goods;
    private Upgrade[] upgrades;
    private boolean hasWinningItem;

    /**
     * gets the region name
     * @return the name of the region
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * gets the region description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the tech level of the region
     * @return the tech level
     */
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * returns if the region is discovered
     * @return if the region is discovered
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }

    /**
     * gets the location of the region
     * @return the location of the region
     */
    public Point getLocation() {
        return location;
    }

    public boolean getHasWinningItem() {
        return hasWinningItem;
    }

    /**
     * gets the upgrades available for purchase in the region
     * @return array containing available upgrades in the region
     */
    public Upgrade[] getUpgrades() {
        return upgrades;
    }

    /**
     * sets if the region has been discovered
     * @param val t/f if the region is discovered
     */
    public void setDiscovered(boolean val) {
        this.isDiscovered = val;
    }

    public void setHasWinningItem(boolean val) {
        this.hasWinningItem = val;
    }

    /**
     * constructs a region
     * @param builder builds the region
     */
    private Region(RegionBuilder builder) {
        this.regionName = builder.regionName;
        this.description = builder.description;
        this.techLevel = builder.techLevel;
        this.isDiscovered = builder.isDiscovered;
        this.location = builder.location;
        this.hasWinningItem = false;

        Random r = new Random();
        upgrades = new Upgrade[r.nextInt(3)];
        Upgrade[] possibleUpgrades = Upgrade.values();
        for (int i = 0; i < upgrades.length; i++) {
            this.upgrades[i] = possibleUpgrades[r.nextInt(possibleUpgrades.length)];
        }
    }

    public Good[] getGoods() {
        if (goods == null) {
            goods = GoodList.getGoods(techLevel);
        }
        return goods;
    }

    public void setPrices() {
        prices = new PriceGenerator(this);
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    public void clearPrices() {
        prices = null;
    }

    public PriceGenerator getPrices() {
        return prices;
    }

    /**
     * class building a region
     */
    public static class RegionBuilder {
        private String regionName;
        private int techLevel;
        private String description;
        private boolean isDiscovered;
        private Point location;

        /**
         * constructs a region builder with a specific region name
         * @param name the name of the region
         * @return the region builder
         */
        public RegionBuilder withName(String name) {
            this.regionName = name;
            return this;
        }

        /**
         * constructs a region builder with a specific region description
         * @param description the description of the region
         * @return the region builder
         */
        public RegionBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * constructs a region builder with a specific tech level
         * @param techLevel the tech level
         * @return the region builder
         */
        public RegionBuilder withTechLevel(int techLevel) {
            this.techLevel = techLevel;
            return this;
        }

        /**
         * constructs a region builder that has been discovered/not discovered
         * @param is if the region is discovered
         * @return the RegionBuilder
         */
        public RegionBuilder isDiscovered(boolean is) {
            this.isDiscovered = is;
            return this;
        }

        /**
         * constucts a region builder with a location
         * @param location the location of the region
         * @return the RegionBuilder
         */
        public RegionBuilder withLocation(Point location) {
            this.location = location;
            return this;
        }

        /**
         * builds a region
         * @return the region
         */
        public Region build() {
            Region region = new Region(this);
            if (region.getTechLevel() == 0) {
                region.setTechLevel(1);
            }
            return region;
        }
    }
}