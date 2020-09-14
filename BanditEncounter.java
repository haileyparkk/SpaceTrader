package scenes;

import data.UserData;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Region;

import static data.UserData.*;
/**
 * Generates the bandit encounter scene.
 *
 * @author Mary Beth Lord
 * @version 1.0
 */

public class BanditEncounter extends scenes.SpaceTraderScene {
    private Region region;  // the region the player is attempting to travel to

    public BanditEncounter(Stage primaryStage, Region region) {
        super(primaryStage);
        this.region = region;
    }

    private Stage popup;

    public Scene get() {
        System.out.println("Getting Bandit Encounter...");

        //the top of the scene
        Text title1 = new Text("Bandit Encounter");
        Text title2 = new Text("Choose one of the options below");
        title1.setFill(Color.BLACK);
        title1.setFont(new Font("verdana", 50));
        title1.setUnderline(true);

        title2.setFill(Color.BLACK);
        title2.setFont(new Font("verdana", 50));
        title2.setUnderline(true);

        HBox empty = new HBox();
        empty.setMinHeight(40);

        HBox empty2 = new HBox();
        empty2.setMinHeight(70);



        VBox display = new VBox(title1, empty, title2);
        display.setMinSize(1000, 600);


        //the description for "option 1"

        Text fOption = new Text("Option 1: Pay the bandit's demand "
                + "and continue to the desired destination");
        fOption.setFont(new Font("verdana", 30));
        VBox option1 = new VBox(fOption);
        option1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));
        option1.setAlignment(Pos.CENTER);


        Text sOption = new Text("Option 2: Try to flee back to the previous region");
        sOption.setFont(new Font("verdana", 30));
        VBox option2 = new VBox(sOption);
        option2.setAlignment(Pos.CENTER);
        option2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));

        Text tOption = new Text("Option 3: Try to fight off the bandit");
        tOption.setFont(new Font("verdana", 30));
        VBox option3 = new VBox(tOption);
        option3.setAlignment(Pos.CENTER);
        option3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));

        Text uno = new Text("Hover over the options to help you decide. Choose wisely!");
        VBox info = new VBox(uno);
        uno.setFont(new Font("verdana", 40));
        info.setAlignment(Pos.CENTER);
        info.setMinHeight(70);

        Text dos = new Text("Click to choose option 1");
        dos.setFont(new Font("verdana", 40));
        VBox choose1 = new VBox(dos);
        choose1.setAlignment(Pos.CENTER);
        choose1.setMinHeight(70);

        Text tres = new Text("Click to choose option 2");
        tres.setFont(new Font("verdana", 40));
        VBox choose2 = new VBox(tres);
        choose2.setAlignment(Pos.CENTER);
        choose2.setMinHeight(70);

        Text cuatro = new Text("Click to choose option 3");
        cuatro.setFont(new Font("verdana", 40));
        VBox choose3 = new VBox(cuatro);
        choose3.setAlignment(Pos.CENTER);
        choose3.setMinHeight(70);

        //all the methods for the first option
        option1.setOnMouseEntered(new FirstHover());
        option1.setOnMouseExited(new EndHover1());
        choose1.setOnMouseClicked(new FirstClick());
        //popup.setOnMouseExited(new EndPopUp);

        //all the methods for the second option
        option2.setOnMouseEntered(new SecondHover());
        option2.setOnMouseExited(new EndHover2());
        choose2.setOnMouseClicked(new SecondClick());


        option3.setOnMouseEntered(new ThirdHover());
        option3.setOnMouseExited(new EndHover3());
        choose3.setOnMouseClicked(new ThirdClick());

        display.getChildren().add(empty2);
        display.getChildren().add(info);
        display.getChildren().add(choose1);
        display.getChildren().add(choose2);
        display.getChildren().add(choose3);
        display.getChildren().add(option1);
        display.getChildren().add(option2);
        display.getChildren().add(option3);
        display.setAlignment(Pos.TOP_CENTER);

        return new Scene(display);
    }


    //hover over the first option for info
    class FirstHover implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            if (popup == null) {
                popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("To comply with the bandit's demands, "
                        + "\n you must pay $25."));
                popupDialog.setMinSize(400, 400);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        }
    }

    //end the first hover pop up
    class EndHover1 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            popup.close();
            popup = null;
        }
    }

    //click to choose it
    class FirstClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            popup = new Stage();
            popup.initModality(Modality.NONE);
            popup.initOwner(primaryStage);
            VBox popupDialog = new VBox(20);

            if (UserData.getCredit() - 25 >= 0) {
                setCredit(getCredit() - 25.0);
                popupDialog.getChildren().add(new Text("Good choice. Your credit is now $"
                        + getCredit()));
            } else {
                popupDialog.getChildren().add(new Text("You don't have enough money! "
                        + "You lose all of your items! "
                        + "Your ship is damaged and you continue on..."));
                UserData.getOwnedItems().clear();
                UserData.getShip().setCurrentHealth(UserData.getShip().getCurrentHealth() - 10);
            }
            popupDialog.setMinSize(400, 400);
            Scene popupScene = new Scene(popupDialog, 300, 300);
            popup.setScene(popupScene);
            popup.show();

            // Added this block to update current region
            UserData.getCurrentRegion().clearPrices();
            UserData.setCurrentRegion(region);
            region.setPrices();
            region.setDiscovered(true);
            goBackOneScene();

        }
    }

    ////hover over the second option for info
    class SecondHover implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            if (popup == null) {
                popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("How good is your pilot level?"
                        + "\n If you successfully flee, you will still lose fuel "
                        + "\n required to travel."
                        + " But, you will keep all your \n credits and items."
                        + " If you try to flee and \n fail, bandit will take all of your credits"
                        + " \nand damage the health value of your ship."));
                popupDialog.setMinSize(400, 400);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        }
    }

    //end the second hover
    class EndHover2 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            popup.close();
            popup = null;

        }
    }

    //click for the second option
    class SecondClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            //make this random
            int banditPow = 1;
            popup = new Stage();
            popup.initModality(Modality.NONE);
            popup.initOwner(primaryStage);
            VBox popupDialog = new VBox(20);
            if (getPilot() >= banditPow) {

                //add fuel variable? getter + setter minus 2 points ish
                popupDialog.getChildren().add(new Text("Good job. You fled from the bandit!!!\n"
                        + "You still lost some fuel."));



                // Added this block to update current region
                UserData.getCurrentRegion().clearPrices();
                //UserData.setCurrentRegion(region);
                UserData.getShip().setCurrentFuel(UserData.getShip().getCurrentFuel() - 5);
                //region.setPrices();
                //region.setDiscovered(true);
            }





            if (getPilot() < banditPow) {
                setHealth((int) (getHealth() - (Math.round(banditPow * .5))));
                setCredit(0.0);
                UserData.getShip().setCurrentHealth(UserData.getShip().getCurrentHealth() - 10);
                popupDialog.getChildren().add(new Text("The bandit beat you this time :("
                        + "Your credit is now 0.0, and your ship's health is now "
                        + getHealth() + "."));
            }
            popupDialog.setMinSize(400, 400);
            Scene popupScene = new Scene(popupDialog, 300, 300);
            popup.setScene(popupScene);
            goBackOneScene();
            popup.show();
        }
    }

    class ThirdHover implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            if (popup == null) {
                popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("How high is your fighting skill?"
                        + "\nYou can try to fight off the bandit. "
                        + "If you win,\nthere are no consequences, and "
                        + "you get\nsome of the bandit's "
                        + "credits as a reward!\nBut if you lose, the bandit gets all "
                        + "your credits, \nand damages "
                        + "the health of your\nship."));
                popupDialog.setMinSize(400, 400);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        }
    }

    class EndHover3 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            popup.close();
            popup = null;

        }
    }

    class ThirdClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            //make this random and credit
            int bFighter = 1;
            int bCredit = 0;
            popup = new Stage();

            if (getFighter() >= bFighter) {
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("Great job. You beat the bandit!!! "
                        + "\nYou gained $" + bCredit + "credit!"));





                // Added this block to update current region
                UserData.getCurrentRegion().clearPrices();
                UserData.setCurrentRegion(region);
                region.setPrices();
                region.setDiscovered(true);
                setScene(new UniverseDisplay(primaryStage, getUniverse()).get());





            } else {
                setHealth((int) (getHealth() - (Math.round(bFighter * .5))));
                UserData.getShip().setCurrentHealth(
                        UserData.getShip().getCurrentHealth() - 10);
                setCredit(0.0);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("The bandit beat you this time :( "
                        + "\nYour credit is now 0.0, and your health is\nnow "
                        + UserData.getShip().getCurrentHealth() + "."));
                popupDialog.setMinSize(400, 400);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                goBackOneScene();
                popup.show();
            }
        }
    }
}

