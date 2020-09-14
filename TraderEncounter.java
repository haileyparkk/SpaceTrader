package scenes;

import data.Good;
import data.UserData;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Region;

import java.util.Random;


/**
 * class representing the Trader encounter
 * @author Hailey Park, Mary Beth Lord
 * @version 1.0
 */

public class TraderEncounter extends SpaceTraderScene {
    private Region region;  // the region the player is attempting to travel to

    public TraderEncounter(Stage primaryStage, Region region) {
        super(primaryStage);
        this.region = region;
    }

    private Random newRandom = new Random();
    private Random secondRandom = new Random();
    private int i = newRandom.nextInt(10) + 1;
    private double j = secondRandom.nextDouble() * 100;
    private Good good = (Good.values()[i]);
    private objects.Ship ship;

    public Scene get() {
        System.out.println("Getting Trader Encounter...");

        Text title1 = new Text("Trader Encounter");
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

        //information box
        Text infoBox = new Text("The trader would like to offer you "
                + i + " of " + good.getName()
                + " for " + j + ".");
        infoBox.setFont(new Font("verdana", 30));
        VBox infoVBOx = new VBox(infoBox);
        infoVBOx.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        infoVBOx.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));
        infoVBOx.setAlignment(Pos.CENTER);

        //option 1
        Text option1 = new Text("Option 1: Buy Items");
        option1.setFont(new Font("verdana", 30));
        VBox option1Box = new VBox(option1);
        option1Box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option1Box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));
        option1Box.setAlignment(Pos.CENTER);

        //option 2
        Text option2 = new Text("Option 2: Ignore the Trader");
        option2.setFont(new Font("verdana", 30));
        VBox option2Box = new VBox(option2);
        option2Box.setAlignment(Pos.CENTER);
        option2Box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option2Box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));

        //option 3
        Text option3 = new Text("Option 3: Rob the trader");
        option3.setFont(new Font("verdana", 30));
        VBox option3Box = new VBox(option3);
        option3Box.setAlignment(Pos.CENTER);
        option3Box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option3Box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));

        //option 4
        Text option4 = new Text("Option 4: Negotiate for a better price");
        option4.setFont(new Font("verdana", 30));
        VBox option4Box = new VBox(option4);
        option4Box.setAlignment(Pos.CENTER);
        option4Box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        option4Box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));

        //buttons
        option1.setOnMouseClicked(new BuyClick());
        option2.setOnMouseClicked(new IgnoreClick());
        option3.setOnMouseClicked(new RobClick());

        option4.setOnMouseClicked(e -> {
            Random negotiateInt = new Random();
            int i = negotiateInt.nextInt(9);
            int points = data.UserData.getMerchant();
            double negotiatedPrice = j - (j * 0.5);
            double increasedPrice = j + (j * 0.5);
            if (points >= i) {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);

                infoBox.setText("The trader would like to offer you "
                        + i + " of " + good.getName()
                        + " for " + negotiatedPrice + ".");
                j = increasedPrice;

                popupDialog.getChildren().add(new Text("You have successfully "
                        + "negotiated with Trader! The lowered price is "
                        + negotiatedPrice + "."));
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            } else {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);

                infoBox.setText("The trader would like to offer you "
                        + i + " of " + good.getName()
                        + " for " + increasedPrice + ".");
                j = increasedPrice;

                popupDialog.getChildren().add(new Text("You have not "
                        + "successfully negotiated with Trader! The price has increased to "
                        + increasedPrice + "."));
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        });


        //back button
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            goBackOneScene();
        });

        //display everything
        VBox vbox = new VBox(empty2, infoVBOx, option1Box, option2Box,
                option3Box, option4Box, infoBox, option1, option2,
                option3, option4);
        vbox.setMinSize(1000, 600);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5.0);
        return new Scene(vbox);
    }

    /**
     * class that handles the user clicking the buy option
     */

    class BuyClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Text popupText = new Text();
            if (UserData.getCredit() - j >= 0) {
                double currentCredit = 0;
                currentCredit = data.UserData.getCredit() - j;
                data.UserData.setCredit(currentCredit);
                currentCredit = Double.parseDouble(String.format("%.2f", currentCredit));
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupText.setText("You have " + currentCredit
                        + " credit left, and you have purchased "
                        + i + " amount of " + good.getName() + ".");
                popupDialog.getChildren().add(popupText);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            } else {
                popupText.setText("You don't have enough money!");
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(popupText);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        }
    }

    /**
     * class that handles the user clicking the ignore option
     */

    class IgnoreClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {



            // Added this block to update current region
            UserData.getCurrentRegion().clearPrices();
            UserData.setCurrentRegion(region);
            region.setPrices();
            region.setDiscovered(true);

            primaryStage.setScene(new UniverseDisplay(primaryStage,
                    UserData.getUniverse()).get());



        }
    }

    /**
     * class that handles the rob option
     */

    class RobClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Random robInt = new Random();
            int i = robInt.nextInt(9);
            int points = data.UserData.getFighter();
            Random goodInt = new Random();
            int j = goodInt.nextInt(10) + 1;
            Random chooseGood = new Random();
            int k = chooseGood.nextInt(22);
            Good good = (Good.values()[k]);
            if (points >= i) {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("You have "
                        + "successfully robbed the Trader! You have received: "
                        + j + good.getName()));
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            } else {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("You have not successfully "
                        + "robbed the Trader, and your ship's health has decreased!"));
                int currentShipHealth = 0;
                //currentShipHealth = ship.getMaxHealth() - 10;
                //ship.setMaxHealth(currentShipHealth);
                UserData.getShip().setCurrentHealth(UserData.getShip().getCurrentHealth() - 10);
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
        }
    }

    /**
     * class that handles the negotiate option
     */

    class NegotiateClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent m) {
            Random negotiateInt = new Random();
            int i = negotiateInt.nextInt(9);
            int points = data.UserData.getMerchant();
            double negotiatedPrice = j - (j * 0.5);
            double increasedPrice = j + (j * 0.5);
            if (points >= i) {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("You have successfully "
                        + "negotiated with Trader! The lowered price is "
                        + negotiatedPrice + "."));
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            } else {
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("You have not "
                        + "successfully negotiated with Trader! The price has increased to "
                        + increasedPrice + "."));
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
            }
            //somehow get display to change


        }

    }

}
