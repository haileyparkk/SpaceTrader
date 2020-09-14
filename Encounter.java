package objects;

import data.UserData;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.BanditEncounter;
import scenes.PoliceEncounter;
import scenes.TraderEncounter;

import java.util.Random;

/**
 * This class handles serving the player a particular encounter Scene based on the
 * player's difficulty level
 *
 */
public class Encounter {
    private Stage primaryStage;
    private double banditProbability;
    private double policeProbability;
    private double traderProbability;
    private Random r;
    private Region region;  //The region the player is attempting to travel to

    public Encounter(Stage primaryStage, Region region) {
        this.primaryStage = primaryStage;
        this.r = new Random();
        String difficulty = UserData.getDifficulty();
        this.region = region;

        switch (difficulty) {
            case "Easy":
                // 20% change of bad encounters
                // 50% chance of trade encounter
                // 30% chance of no encounters
                this.banditProbability = 0.1;
                this.policeProbability = 0.1;
                this.traderProbability = 0.5;
                break;
            case "Medium":
                // 30% chance of bad encounters
                // 40% chance of trade encounter
                // 30% chance of no encounter
                this.banditProbability = 0.15;
                this.policeProbability = 0.15;
                this.traderProbability = 0.4;
                break;
            case "Hard":
                // 40% chance of bad encounter
                // 30% chance of trade encounter
                // 30% chance of no encounter
                this.banditProbability = 0.20;
                this.policeProbability = 0.20;
                this.traderProbability = 0.3;
                break;
            default:
                //checkstyle made me add a default case
        }
    }

    public Scene get() {
        double chosen = r.nextDouble();

        if (chosen < banditProbability) {
            return new BanditEncounter(primaryStage, region).get();
        } else if (chosen < banditProbability + policeProbability) {
            return new PoliceEncounter(primaryStage, region).get();
        } else if (chosen < banditProbability + policeProbability + traderProbability
                && UserData.getShip().getCargo().size() != 0) {
            return new TraderEncounter(primaryStage, region).get();
        } else {
            return null;
        }
    }
}
