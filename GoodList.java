package data;

import java.util.ArrayList;
import java.util.Random;

/**
 * class representing a list of all good types in the game
 * @author Andrew Harris
 * @version 1.0
 */
public class GoodList {
    private static ArrayList<Good> goods;
    private static final int numOfGoods = 15;

    /**
     * constructs the list
     */
    public static void makeList() {
        goods = new ArrayList<>(numOfGoods);
        goods.add(Good.WATER);
        goods.add(Good.FUR);
        goods.add(Good.FOOD);
        goods.add(Good.ORE);
        goods.add(Good.GAME);
        goods.add(Good.FIREARM);
        goods.add(Good.MEDICINE);
        goods.add(Good.MACHINES);
        goods.add(Good.NARCOTICS);
        goods.add(Good.FUEL);
        goods.add(Good.FLATULENCE);
        goods.add(Good.HAIRGEL);
        goods.add(Good.MAYONNAISE);
        goods.add(Good.CLOTHING);
        goods.add(Good.SEEDS);
        goods.add(Good.RUBBER);
        goods.add(Good.ANTIQIUES);
        goods.add(Good.FOSSIL);
        goods.add(Good.GLASS);
        goods.add(Good.SPRINGS);
        goods.add(Good.PAINTINGS);
        goods.add(Good.WOOD);
    }

    /**
     * returns a list of goods in a region at the current time as an array
     * @param techLevel tech level of the region
     * @return the goods
     */
    public static Good[] getGoods(int techLevel) {
        Random r = new Random();
        int rNum;
        Good[] goodArray = new Good[9];
        for (int i = 0; i < 9;) {
            rNum = r.nextInt(numOfGoods - i);
            if (rNum <= goods.size()) {
                Good good = goods.get(rNum);
                if (good.getMinTechLevel() <= techLevel) {
                    goodArray[i] = good;
                    i++;
                }
                goods.remove(rNum);
            }
        }
        makeList();
        return goodArray;
    }
}
