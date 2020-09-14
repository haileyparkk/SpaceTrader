package scenes;

import data.Good;
import data.UserData;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Region;

import java.util.Random;

import static data.UserData.*;

public class PoliceEncounter extends scenes.SpaceTraderScene {
    private Random random = new Random();
    private Region region;      // the region the player is attempting to travel to
    private Good forfeitGood;

    public PoliceEncounter(Stage primaryStage, Region region) {
        super(primaryStage);
        this.region = region;
    }

    private Stage popo;

    public Scene get() {
        forfeitGood = (Good) UserData.getShip().getCargo()
                .get(random.nextInt(UserData.getShip().getCargo().size()));
        System.out.println("Getting police encounter...");

        Text title1 = new Text("Police Encounter");
        Text title2 = new Text("Choose one of the options below");
        title1.setFill(Color.BLACK);
        title1.setFont(new Font("verdana", 50));
        title1.setUnderline(true);
        title2.setFill(Color.BLACK);
        title2.setFont(new Font("verdana", 50));
        title2.setUnderline(true);

        HBox empty = new HBox();
        empty.setMinHeight(40);


        Text option1 = new Text("Forfeit your " + forfeitGood.getName());
        option1.setFill(Color.BLACK);
        option1.setFont(new Font("verdana", 50));
        VBox uno = new VBox(option1);
        uno.setOnMouseClicked(new FirstClick());

        Text option2 = new Text("Try to flee");
        option2.setFill(Color.BLACK);
        option2.setFont(new Font("verdana", 50));
        VBox dos = new VBox(option2);
        dos.setOnMouseClicked(new SecondClick());

        Text option3 = new Text("Fight off the Police");
        option3.setFill(Color.BLACK);
        option3.setFont(new Font("verdana", 50));
        VBox tres = new VBox(option3);
        tres.setOnMouseClicked(new ThirdClick());


        VBox options = new VBox(uno, dos, tres);
        VBox display = new VBox(title1, empty, title2, options);
        display.setMinSize(1000, 600);






        return new Scene(display);

    }

    //option one
    //still need to add the effect on the players item

    class FirstClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Stage popo = new Stage();
            popo.initModality(Modality.NONE);
            popo.initOwner(primaryStage);
            VBox popupDialog = new VBox(20);
            Text great = new Text("Good choice. The police has the items and you are free to go.");
            UserData.getOwnedItems().remove(forfeitGood);
            popupDialog.getChildren().add(great);
            popupDialog.setMinSize(400, 400);
            Scene popupScene = new Scene(popupDialog, 300, 300);
            popo.setScene(popupScene);
            popo.show();

            // Added this block to update current region
            UserData.getCurrentRegion().clearPrices();
            UserData.setCurrentRegion(region);
            region.setPrices();
            region.setDiscovered(true);

            goBackOneScene();
        }

    }
    class SecondClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Stage popup = new Stage();
            popup.initModality(Modality.NONE);
            popup.initOwner(primaryStage);
            VBox dialog = new VBox(20);
            //make this a random
            int n = random.nextInt(8);
            /*
            If the player fails to flee, the Police will confiscate
            the stolen items, damage the health value of the
            player's ship, and force the player to pay a fine for evasion.
            Then the player returns to the previous
            region.
             */

            if (getPilot() >= n) {
                //setFuel(getFuel - distance??);
                UserData.getShip().setCurrentFuel(UserData.getShip().getCurrentFuel() - 10);
                Text won = new Text("Good job! You successfully fleed from the pilot!"
                        + "\nYou did lose some fuel though. Your fuel level is now ");
                dialog.getChildren().add(won);

                // Added this block to update current region
                //UserData.getCurrentRegion().clearPrices();
                //UserData.setCurrentRegion(region);
                //region.setPrices();
                //region.setDiscovered(true);



            } else {
                Text lost = new Text("The police caught you. He confiscated "
                        + "your stolen items, damaged your health,"
                        + "\nand this cost you a fine of $10.");
                dialog.getChildren().add(lost);
                setCredit(getCredit() - 10);
                UserData.getShip().setCurrentHealth(UserData.getShip().getCurrentHealth() - 5);
                UserData.getShip().getCargo().remove(forfeitGood);
            }
            Scene popupScene = new Scene(dialog, 300, 300);
            popup.setScene(popupScene);
            popup.show();
            goBackOneScene();
        }
    }
    class ThirdClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Stage popup =  new Stage();
            popup.initModality(Modality.NONE);
            popup.initOwner(primaryStage);
            VBox dialog = new VBox(20);
            int n = random.nextInt(8);
            if (getFighter() >= n) {
                Text won = new Text("You beat the police! Good job :)");
                dialog.getChildren().add(won);

                // Added this block to update current region
                UserData.getCurrentRegion().clearPrices();
                UserData.setCurrentRegion(region);
                region.setPrices();
                region.setDiscovered(true);


            } else {
                Text lost = new Text("You lost to the police! ");
                dialog.getChildren().add(lost);
            }
            Scene popupScene = new Scene(dialog, 300, 300);
            popup.setScene(popupScene);
            popup.show();
            goBackOneScene();
        }
    }
}
