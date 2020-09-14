package data;

import objects.Region;

import java.util.Random;

/**
 * Generates a price for the marketplace.
 *
 * @author tselman3, Andrew Harris
 * @version 1.0
 */
public class PriceGenerator {

    private double[] prices;
    private final double STEP_SIZE = 0.5;
    private static Random random = new Random();

    private final Good[] goodList;
    private final double stepSize;
    
    /**
     * Creates a price generator object specifying variables
     *
     * @param goodList list of goods
     * @param stepSize the precision for the values
     */
    public PriceGenerator(Good[] goodList, double stepSize) {
        this.goodList = goodList;
        this.prices = new double[18];
        this.stepSize = stepSize;
        setPrices();
    }

    /**
     * Creates a default price generator object
     * @param region the region
     */
    public PriceGenerator(Region region) {
        goodList = GoodList.getGoods(region.getTechLevel());
        stepSize = STEP_SIZE * (160.0 / (160 + UserData.getMerchant()));
        prices = new double[18];
        setPrices();
    }

    public void setPrices() {
        Good good;
        for (int i = 0; i < 9; i++) {
            good = goodList[i];
            double base = good.getPrice();

            Double stepSizeDouble = (stepSize * 20);

            int rNum = stepSizeDouble.intValue();
            rNum = rNum / 1;

            int diff = Math.abs(random.nextInt(rNum));
            double doubleDiff = Math.abs(random.nextInt(100) * 0.01);

            if (diff == 0) {
                diff = 1;
            }
            if (doubleDiff == 0) {
                doubleDiff = 0.01;
            }

            double sell = base - diff - doubleDiff;
            double buy = base + diff + doubleDiff;

            String sellString = String.format(" %.2f", sell);
            String buyString = String.format(" %.2f", buy);

            while (sellString.charAt(sellString.length() - 3) != '0') {
                sellString += "0";
            }
            while (buyString.charAt(buyString.length() - 3) != '0') {
                buyString += "0";
            }

            sell = Double.parseDouble(sellString);
            buy = Double.parseDouble(buyString);

            prices[i] = buy;
            prices[i + 9] = sell;
        }
    }

    public Good getGood(int index) {
        return goodList[index];
    }

    public String getGoodName(int index) {
        return goodList[index].getName();
    }
    
    /**
     * Gets the buy price for a good
     * 
     * @param index the index of the good
     * @return the buy price
     */
    public double getBuyPrice(int index) {
        return prices[index];
    }
    
    /**
     * Gets the sell price for a good
     * 
     * @param index the index of the good
     * @return the sell price
     */
    public double getSellPrice(int index) {
        return prices[index + 9];
    }
}
