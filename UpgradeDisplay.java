package scenes;

import data.Upgrade;
import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import styles.ButtonStyle;

import java.util.ArrayList;

public class UpgradeDisplay extends SpaceTraderScene {

    public UpgradeDisplay(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene get() {
        ArrayList<Upgrade> ownedUpgrades = UserData.getOwnedUpgrades();
        ArrayList<Upgrade> activeUpgrades = UserData.getActiveUpgrades();

        // Creating VBox to contain names of active upgrades
        VBox equippedUpgradeVbox = new VBox();
        equippedUpgradeVbox.setAlignment(Pos.TOP_CENTER);
        equippedUpgradeVbox.setMinSize(500, 600);
        equippedUpgradeVbox.setBackground(getBackgroundWithFill(Color.BLACK));
        equippedUpgradeVbox.setSpacing(10.0);



        /*
        Making all of the texts for displaying the player's current
        level of skills and the buffs they are receiving from equipped items
         */

        Text currentStatBuffs = new Text("Current Stat Buffs");
        currentStatBuffs.setStyle("-fx-font: 20 arial;");
        currentStatBuffs.setFill(Color.BLUE);

        int buffToPilot = UserData.getBuffToPilot();
        int buffToEngineer = UserData.getBuffToEngineer();
        int buffToFighter = UserData.getBuffToFighter();
        int buffToMerchant = UserData.getBuffToMerchant();

        Text buffToPilotText = new Text("+ " + buffToPilot);
        buffToPilotText.setStyle("-fx-font: 15 arial;");
        buffToPilotText.setFill(Color.GREEN);

        Text buffToEngineerText = new Text("+ " + buffToEngineer);
        buffToEngineerText.setStyle("-fx-font: 15 arial;");
        buffToEngineerText.setFill(Color.GREEN);

        Text buffToFighterText = new Text("+ " + buffToFighter);
        buffToFighterText.setStyle("-fx-font: 15 arial;");
        buffToFighterText.setFill(Color.GREEN);

        Text buffToMerchantText = new Text("+ " + buffToMerchant);
        buffToMerchantText.setStyle("-fx-font: 15 arial;");
        buffToMerchantText.setFill(Color.GREEN);

        int basePilot = UserData.getPilot() - buffToPilot;
        int baseEngineer = UserData.getEngineer() - buffToEngineer;
        int baseFighter = UserData.getFighter() - buffToFighter;
        int baseMerchant = UserData.getMerchant() - buffToMerchant;

        Text basePilotText = new Text("Pilot: " + basePilot);
        basePilotText.setStyle("-fx-font: 15 arial;");
        basePilotText.setFill(Color.WHITE);

        Text baseEngineerText = new Text("Engineer: " + baseEngineer);
        baseEngineerText.setStyle("-fx-font: 15 arial;");
        baseEngineerText.setFill(Color.WHITE);

        Text baseFighterText = new Text("Fighter: " + baseFighter);
        baseFighterText.setStyle("-fx-font: 15 arial;");
        baseFighterText.setFill(Color.WHITE);

        Text baseMerchantText = new Text("Merchant: " + baseMerchant);
        baseMerchantText.setStyle("-fx-font: 15 arial;");
        baseMerchantText.setFill(Color.WHITE);

        HBox allPilot = new HBox(basePilotText, buffToPilotText);
        allPilot.setAlignment(Pos.CENTER);
        HBox allEngineer = new HBox(baseEngineerText, buffToEngineerText);
        allEngineer.setAlignment(Pos.CENTER);
        HBox allFighter = new HBox(baseFighterText, buffToFighterText);
        allFighter.setAlignment(Pos.CENTER);
        HBox allMerchant = new HBox(baseMerchantText, buffToMerchantText);
        allMerchant.setAlignment(Pos.CENTER);


        // Scene title creation
        Text activeUpgradeTitle = new Text("Active Upgrades");
        activeUpgradeTitle.setStyle("-fx-font: 20 arial;");
        activeUpgradeTitle.setFill(Color.BLUE);

        /*
        Adding all children to the VBox covering the left half of the screen
         */
        equippedUpgradeVbox.getChildren().addAll(currentStatBuffs, allPilot, allEngineer,
                allFighter, allMerchant, activeUpgradeTitle);

        if (activeUpgrades != null) {
            // Adding upgrade names to scene
            for (Upgrade upgrade : activeUpgrades) {
                Text upgradeText = new Text(upgrade.getName());
                upgradeText.setStyle("-fx-font: 20 arial;");
                upgradeText.setFill(Color.WHITE);
                equippedUpgradeVbox.getChildren().add(upgradeText);
            }
        }

        // VBox containing all owned upgrades
        VBox ownedUpgradeVbox = new VBox();
        ownedUpgradeVbox.setAlignment(Pos.TOP_CENTER);
        ownedUpgradeVbox.setMinSize(500, 600);


        Text ownedTitle = new Text("Owned Upgrades");
        ownedTitle.setFill(Color.BLUE);
        ownedTitle.setStyle("-fx-font: 20 arial;");

        ownedUpgradeVbox.getChildren().addAll(ownedTitle);

        for (Upgrade upgrade: ownedUpgrades) {
            Text upgradeName = new Text();
            upgradeName.setStyle("-fx-font: 15 arial;");
            if (activeUpgrades != null && activeUpgrades.contains(upgrade)) {
                upgradeName.setText(upgrade.getName() + " (equipped)");
                upgradeName.setFill(Color.RED);
                upgradeName.setOnMouseClicked(e -> {
                    UserData.unequipUpgrade(upgrade);
                    primaryStage.setScene(new UpgradeDisplay(primaryStage).get());
                });
            } else {
                upgradeName.setText(upgrade.getName());
                upgradeName.setFill(Color.WHITE);
                upgradeName.setOnMouseClicked(e -> {
                    UserData.equipUpgrade(upgrade);
                    primaryStage.setScene(new UpgradeDisplay(primaryStage).get());
                });
            }
            ownedUpgradeVbox.getChildren().add(upgradeName);
        }


        // HBox to allow separation of vbox with equipped upgrades and vbox of owned upgrades
        HBox divider = new HBox(equippedUpgradeVbox, ownedUpgradeVbox);
        divider.setMinSize(900, 600);
        divider.setBackground(getBackgroundWithFill(Color.BLACK));

        VBox finalDivideVBox = new VBox();
        finalDivideVBox.setAlignment(Pos.CENTER);
        finalDivideVBox.setMinSize(100, 600);
        finalDivideVBox.setBackground(getBackgroundWithFill(Color.BLACK));

        Button goBack = new Button("Back");
        goBack.setStyle(ButtonStyle.menu.get());
        goBack.setOnMouseClicked(e -> {
            goBackOneScene();
        });
        finalDivideVBox.getChildren().addAll(goBack, divider);


        return new Scene(finalDivideVBox);
    }
}
