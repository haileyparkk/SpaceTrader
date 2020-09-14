package scenes;

import com.sun.jdi.DoubleValue;
import data.Good;
import data.PriceGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import data.UserData;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Region;

import javax.swing.*;

/**
 * class representing the Marketplace item's info
 * @author Hailey Park, Andrew Harris
 * @version 1.0
 */
public class MarketplaceItemInfo extends scenes.SpaceTraderScene {

    private Text topTextLeft;
    private Text bottomTextLeft;
    private Text bottomTextRight;
    private objects.Ship ship;
    private Region region;
    private Good item;
    private PriceGenerator priceGenerator;
    private Text currentNum;
    private Text currentNumLabel;
    private Text labelForCapacity;

    /**
     * creates the stage
     * @param primaryStage the stage
     */


    public MarketplaceItemInfo(Stage primaryStage) {
        super(primaryStage);
        ship = UserData.getShip();
        region = UserData.getCurrentRegion();
        this.item = region.getGoods()[UserData.getSelectedIndex()];
    }

    /**
     * starts the scene
     * @return the scene
     */

    public Scene get() {

        // common fonts
        Font verdana36 = new Font("Verdana", 36.0);
        Font verdana30 = new Font("Verdana", 30.0);
        Font verdana28 = new Font("Verdana", 28.0);

        // top three labels
        Text regionLabel = new Text("Region: ");
        regionLabel.setFill(Color.WHITE);
        Text regionName = new Text(region.getRegionName());
        regionName.setFill(Color.WHITE);

        regionLabel.setFont(verdana36);
        regionName.setFont(verdana36);
        regionName.setStyle("-fx-font-weight: bold");

        HBox regionHBox = new HBox(regionLabel, regionName);
        regionHBox.setAlignment(Pos.CENTER);
        regionHBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        Text itemLabel = new Text("Item: ");
        Text itemName = new Text(item.getName());

        itemLabel.setFont(verdana30);
        itemLabel.setFill(Color.WHITE);
        itemName.setFont(verdana30);
        itemName.setFill(Color.WHITE);
        itemName.setStyle("-fx-font-weight: bold");

        HBox itemHBox = new HBox(itemLabel, itemName);
        itemHBox.setAlignment(Pos.CENTER);
        itemHBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        Text creditLabel = new Text("Your Credit: ");
        creditLabel.setFill(Color.WHITE);
        Text creditNum = new Text(Double.toString(UserData.getCredit()));
        creditNum.setFill(Color.WHITE);

        creditLabel.setFont(verdana28);
        creditNum.setFont(verdana28);
        creditNum.setStyle("-fx-font-weight: bold");

        HBox creditHBox = new HBox(creditLabel, creditNum);
        creditHBox.setAlignment(Pos.CENTER);
        creditHBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));


        // buy/sell vboxes
        Text buyLabel = new Text("Buy");
        buyLabel.setFont(verdana28);
        buyLabel.setFill(Color.WHITE);

        Text sellLabel = new Text("Sell");
        sellLabel.setFont(verdana28);
        sellLabel.setFill(Color.WHITE);


        labelForCapacity = new Text("Current / Capacity");
        labelForCapacity.setFont(new Font("verdana", 20));
        labelForCapacity.setFill(Color.WHITE);

        priceGenerator = UserData.getCurrentRegion().getPrices();

        Text buyPriceLabel = new Text("$"
                + priceGenerator.getBuyPrice(UserData.getSelectedIndex()));
        buyPriceLabel.setFont(verdana28);
        buyPriceLabel.setFill(Color.WHITE);

        Text sellPriceLabel = new Text("$"
                + priceGenerator.getSellPrice(UserData.getSelectedIndex()));
        sellPriceLabel.setFont(verdana28);
        sellPriceLabel.setFill(Color.WHITE);

        Button buyButton = new Button("Buy");

        Button sellButton = new Button("Sell");

        buyButton.setOnAction(new BuyButton());
        sellButton.setOnAction(new SellButton());

        HBox buyButtonHBox = new HBox(buyButton);
        buyButtonHBox.setAlignment(Pos.CENTER);
        buyButtonHBox.setSpacing(5.0);

        HBox sellButtonHBox = new HBox(sellButton);
        sellButtonHBox.setAlignment(Pos.CENTER);
        sellButtonHBox.setSpacing(5.0);

        VBox buyVBox = new VBox(buyLabel, buyPriceLabel, buyButtonHBox);
        buyVBox.setAlignment(Pos.CENTER);
        buyVBox.setSpacing(5.0);

        VBox sellVBox = new VBox(sellLabel, sellPriceLabel, sellButtonHBox);
        sellVBox.setAlignment(Pos.CENTER);
        sellVBox.setSpacing(5.0);

        // middle numbers
        currentNum = new Text(String.valueOf(ship.getContainsCargoNum(item)));
        currentNum.setFont(verdana30);
        currentNum.setFill(Color.WHITE);

        currentNumLabel = new Text("Current " + item.getName() + " Quantity");
        currentNumLabel.setFont(verdana28);
        currentNumLabel.setFill(Color.WHITE);

        StackPane currentNumPane = new StackPane(currentNum);
        currentNumPane.setMinWidth(50.0);
        currentNumPane.setMaxWidth(50.0);
        currentNumPane.setAlignment(Pos.CENTER);

        // right text
        topTextLeft = new Text(Integer.toString(ship.getCargo().size()));
        topTextLeft.setFont(verdana28);
        topTextLeft.setFill(Color.WHITE);

        Text topTextRight = new Text("/" + ship.getCargoCapacity());
        topTextRight.setFont(verdana28);
        topTextRight.setFill(Color.WHITE);

        HBox topText = new HBox(topTextLeft, topTextRight);
        topText.setAlignment(Pos.CENTER_RIGHT);
        topText.setPrefWidth(60.0);
        topText.setMaxWidth(60.0);


        bottomTextLeft = new Text(String.valueOf(ship.getCargoCapacity() - ship.getCargo().size()));

        bottomTextRight = new Text(" Left");

        bottomTextLeft.setFont(verdana28);
        bottomTextLeft.setFill(Color.WHITE);
        bottomTextRight.setFont(verdana28);
        bottomTextRight.setFill(Color.WHITE);

        HBox bottomText = new HBox(bottomTextLeft, bottomTextRight);
        bottomText.setAlignment(Pos.CENTER_RIGHT);
        bottomText.setPrefWidth(60.0);
        bottomText.setMaxWidth(60.0);

        VBox leftVBox = new VBox(buyVBox, sellVBox);
        VBox midVBox = new VBox(currentNumLabel, currentNum);
        VBox rightVBox = new VBox(labelForCapacity, topText, bottomText);

        leftVBox.setSpacing(25.0);
        midVBox.setSpacing(50.0);
        rightVBox.setSpacing(50.0);

        leftVBox.setAlignment(Pos.CENTER);
        midVBox.setAlignment(Pos.CENTER);
        rightVBox.setAlignment(Pos.CENTER_RIGHT);

        midVBox.setPrefWidth(100.0);
        midVBox.setMaxWidth(100.0);

        rightVBox.setMinWidth(100.0);
        rightVBox.setPrefWidth(100.0);

        HBox hBox = new HBox(leftVBox, midVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100.0);
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // bottom buttons
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction(e -> {
            goBackOneScene();
        });

        confirmButton.setOnAction(new ConfirmButton());

        HBox buttonBox = new HBox(cancelButton, confirmButton);
        buttonBox.setSpacing(20.0);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(regionHBox, itemHBox, creditHBox, hBox, buttonBox);
        vbox.setMinSize(1000, 600);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5.0);
        vbox.setBackground(new Background(new BackgroundImage(new Image("images/space.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1000, 800, false, false, false, false))));

        return new Scene(vbox);
    }

    /**
     * class representing the buy button
     */

    class BuyButton implements EventHandler<ActionEvent> {
        /**
         * handles the buy button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            int current = Integer.parseInt(currentNum.getText());
            int numerator = Integer.parseInt(topTextLeft.getText());
            int remainder = Integer.parseInt(bottomTextLeft.getText());
            if (numerator == ship.getCargoCapacity()) {
                return;
            } else {
                double buyBalance = (Integer.parseInt(
                        currentNum.getText()) + 1) * priceGenerator.getBuyPrice(
                                UserData.getSelectedIndex());
                if (buyBalance > UserData.getCredit()) {
                    return;
                } else {
                    currentNum.setText(String.valueOf(current + 1));
                    topTextLeft.setText(String.valueOf(numerator + 1));
                    bottomTextLeft.setText(String.valueOf(remainder - 1));
                }
            }
        }
    }

    /**
     * class representing the sell button
     */

    class SellButton implements EventHandler<ActionEvent> {
        /**
         * handles the sell button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            int current = Integer.parseInt(currentNum.getText());
            int numerator = Integer.parseInt(topTextLeft.getText());
            int remainder = Integer.parseInt(bottomTextLeft.getText());
            if (numerator == 0) {
                return;
            } else {
                double sellBalance = Integer.parseInt(currentNum.getText())
                        * priceGenerator.getSellPrice(UserData.getSelectedIndex());
                currentNum.setText(String.valueOf(current - 1));
                topTextLeft.setText(String.valueOf(numerator - 1));
                bottomTextLeft.setText(String.valueOf(remainder + 1));
            }
        }
    }

    /**
     * class representing the confirm button press
     */

    class ConfirmButton implements EventHandler<ActionEvent> {
        /**
         * handles the confirm button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            int numerator = Integer.parseInt(topTextLeft.getText());
            int remainder = Integer.parseInt(bottomTextLeft.getText());
            int numItem = Integer.parseInt(currentNum.getText());
            int initialItemNum = ship.getContainsCargoNum(item);
            double currentCredit = 0.00;
            if (numerator >= 0 && remainder >= 0 && numItem != initialItemNum) {
                if (numItem > initialItemNum) {
                    double buyBalance = (numItem - initialItemNum)
                            * priceGenerator.getBuyPrice(UserData.getSelectedIndex());
                    currentCredit = UserData.getCredit() - buyBalance;
                    currentCredit = Double.parseDouble(String.format("%.2f", currentCredit));
                    UserData.setCredit(currentCredit);
                    ship.addCargo(item, numItem - initialItemNum);
                } else {
                    double sellBalance = (initialItemNum - numItem)
                            * priceGenerator.getSellPrice(UserData.getSelectedIndex());
                    currentCredit = UserData.getCredit() + sellBalance;
                    currentCredit = Double.parseDouble(String.format("%.2f", currentCredit));
                    UserData.setCredit(currentCredit);
                    ship.removeCargo(item, initialItemNum - numItem);
                }
                final Stage popup = new Stage();
                popup.initModality(Modality.NONE);
                popup.initOwner(primaryStage);
                VBox popupDialog = new VBox(20);
                popupDialog.getChildren().add(new Text("You have "
                        + currentCredit + " credit left."));
                if (numItem > initialItemNum) {
                    popupDialog.getChildren().add(new Text("Your "
                            + item.getName() + " quantity is " + numItem + "."));
                } else {
                    popupDialog.getChildren().add(new Text("Your "
                            + item.getName() + " quantity is " + numItem + "."));
                }
                Scene popupScene = new Scene(popupDialog, 300, 300);
                popup.setScene(popupScene);
                popup.show();
                goBackOneScene();
            }
        }
    }
}
