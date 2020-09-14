package scenes;

import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class ShipMenu extends SpaceTraderScene {

    public ShipMenu(Stage primaryStage) {
        super(primaryStage);
    }

    // Right side of screen: image of ship
    // Left side of screen: ship specs

    public Scene get() {

        Text currentCredits = new Text("Current credits: " + UserData.getCredit());

        String cssLayout = "-fx-border-color: red;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-style: solid;\n";

        // VBox for buying fuel

        Text currentFuelInPopup = new Text("Current fuel: " + UserData.getShip().getCurrentFuel()
                + "/" + UserData.getShip().getFuelCapacity());

        AtomicInteger cost = new AtomicInteger(20);
        Text priceText = new Text("Price: " + cost);
        priceText.setStyle("-fx-font: 15 arial;");


        Text enterAmount = new Text("10");


        // Buy button for actually purchasing fuel
        Button purchase = new Button("Purchase Fuel");
        purchase.setMinSize(100, 50);
        purchase.setMaxSize(100, 50);
        AtomicInteger amountPurchased = new AtomicInteger(Integer.parseInt(enterAmount.getText()));

        if (!(cost.get() <= UserData.getCredit() && (UserData.getShip().getCurrentFuel()
                + amountPurchased.get() <= UserData.getShip().getFuelCapacity()))) {
            purchase.setOpacity(0.3);
        }

        purchase.setOnAction(e -> {
            if (cost.get() <= UserData.getCredit() && (UserData.getShip().getCurrentFuel()
                    + amountPurchased.get() <= UserData.getShip().getFuelCapacity())) {

                // Update ship's fuel
                UserData.getShip().setCurrentFuel(UserData.getShip().getCurrentFuel()
                        + amountPurchased.get());

                // Update user's credit
                UserData.setCredit(UserData.getCredit() - cost.get());

                // Update the 'Current Fuel:" display
                currentFuelInPopup.setText("Current fuel: "
                        + UserData.getShip().getCurrentFuel() + "/"
                        + UserData.getShip().getFuelCapacity());

                // Update the current credit display
                currentCredits.setText("Current credits: " + UserData.getCredit());

            }
        });

        // + button for increasing the amount of fuel to buy
        Button buyMoreFuel = new Button("+");
        buyMoreFuel.setMinSize(30, 30);
        buyMoreFuel.setMaxSize(30, 30);
        buyMoreFuel.setOnAction(e -> {
            int currVal = Integer.parseInt(enterAmount.getText());
            currVal += 10;
            enterAmount.setText(Integer.toString(currVal));

            amountPurchased.set(Integer.parseInt(enterAmount.getText()));

            cost.set(currVal * 5);
            priceText.setText("Price: " + cost);
            if (cost.get() > UserData.getCredit()
                    || currVal + UserData.getShip().getCurrentFuel()
                    > UserData.getShip().getFuelCapacity()) {
                purchase.setOpacity(0.3);
            } else {
                purchase.setOpacity(1.0);
            }
        });

        // - button for decreasing the amount of fuel to buy
        Button buyLessFuel = new Button("-");
        buyLessFuel.setMinSize(30, 30);
        buyLessFuel.setMaxSize(30, 30);
        buyLessFuel.setOnAction(e -> {
            int currVal = Integer.parseInt(enterAmount.getText());
            if (currVal - 10 > 0) {
                currVal -= 10;
                enterAmount.setText(Integer.toString(currVal));

                cost.set(currVal * 5);
                priceText.setText("Price: " + cost);
                if (cost.get() > UserData.getCredit()
                        || currVal + UserData.getShip().getCurrentFuel()
                        > UserData.getShip().getFuelCapacity()) {
                    purchase.setOpacity(0.3);
                } else {
                    purchase.setOpacity(1.0);
                }
            }
        });

        // Button for removing fuel (for testing purposes)
        Button removeFuel = new Button("Remove fuel");
        removeFuel.setOnAction(e -> {

            // Update current ship fuel
            UserData.getShip().setCurrentFuel(UserData.getShip().getCurrentFuel() - 5);

            // Update display of ship fuel
            currentFuelInPopup.setText("Current fuel: " + UserData.getShip().getCurrentFuel()
                    + "/" + UserData.getShip().getFuelCapacity());

            // Update purchase button to reflect if purchase is possible
            int currVal = Integer.parseInt(enterAmount.getText());
            if (cost.get() > UserData.getCredit()
                    || currVal + UserData.getShip().getCurrentFuel()
                    > UserData.getShip().getFuelCapacity()) {
                purchase.setOpacity(0.3);
            } else {
                purchase.setOpacity(1.0);
            }

        });

        // Button to give the user more credits (for testing purposes)
        Button addCredits = new Button("Add Credits");
        addCredits.setOnMouseClicked(e -> {
            UserData.setCredit(UserData.getCredit() + 500);
            currentCredits.setText("Current credits: " + UserData.getCredit());
        });



        HBox quantityOptions = new HBox(buyLessFuel, enterAmount, buyMoreFuel);
        quantityOptions.setSpacing(13);

        Text quantityToBuy = new Text("Quantity");
        VBox quantityAndDescription = new VBox(quantityToBuy, quantityOptions);
        quantityAndDescription.setAlignment(Pos.CENTER);

        VBox fuelDisplay = new VBox(currentFuelInPopup,
                quantityAndDescription, priceText, purchase, removeFuel, addCredits);
        fuelDisplay.setSpacing(15);
        fuelDisplay.setAlignment(Pos.CENTER);
        fuelDisplay.setStyle(cssLayout);


        // end VBox for displaying and buying fuel







        // Vbox for repairing ship

        Text currentHealthInPopup = new Text("Current health: " + UserData.getShip().getCurrentHealth());

        int engineeringLevel = UserData.getEngineer();

        int shipMaxHealth = UserData.getShip().getMaxHealth();
        AtomicInteger shipCurrHealth = new AtomicInteger(UserData.getShip().getCurrentHealth());

        AtomicInteger currPrice = new AtomicInteger(0);
        AtomicInteger currQuantity = new AtomicInteger(0);

        Text currentToPurchase = new Text(Integer.toString(currQuantity.get()));
        currentToPurchase.setStyle("-fx-font: 15 arial;"
        );
        Text currentPriceText = new Text("Price: " + currPrice.get());
        currentPriceText.setStyle("-fx-font: 15 arial;");

        Text currHealth = new Text("Ship health: " + shipCurrHealth + "/" + shipMaxHealth);
        currHealth.setStyle("-fx-font: 15 arial;");

        Button purchaseRepair = new Button("Purchase Repair");
        purchaseRepair.setMinSize(100, 50);
        purchaseRepair.setMaxSize(100, 50);

        if (currPrice.get() == 0 || currPrice.get() > UserData.getCredit()
                || shipCurrHealth.get() + currQuantity.get() > shipMaxHealth) {
            purchaseRepair.setOpacity(0.3);
        }

        Button buyLessHealth = new Button("-");
        buyLessHealth.setOnMouseClicked(e -> {
            if (!(currQuantity.get() - 10 < 0)) {

                // Update quantity of health to repair
                currQuantity.addAndGet(-10);

                // Update display of quantity of health to repair
                currentToPurchase.setText(Integer.toString(currQuantity.get()));

                // Update cost of health to repair
                currPrice.set((currQuantity.get() * 4) - (2 * UserData.getEngineer()));

                // Update displayed cost of health to repair
                currentPriceText.setText("Current price: " + currPrice.get());

                // Update purchase button to reflect if user can purchase this repair
                if (currPrice.get() > UserData.getCredit()
                        || shipCurrHealth.get() + currQuantity.get() > shipMaxHealth
                        || currQuantity.get() == 0) {
                    purchaseRepair.setOpacity(0.3);
                } else {
                    purchaseRepair.setOpacity(1.0);
                }
            }
        });

        Button buyMoreHealth = new Button("+");

        buyMoreHealth.setOnMouseClicked(e -> {

            // Update quantity of health to repair
            currQuantity.addAndGet(+10);

            // Update display of quantity of health to repair
            currentToPurchase.setText(Integer.toString(currQuantity.get()));

            // Update cost of health to repair
            currPrice.set((currQuantity.get() * 4) - (2 * UserData.getEngineer()));

            // Update displayed cost of health to repair
            currentPriceText.setText("Current price: " + currPrice.get());

            // Update purchase button to reflect if user can purchase this repair
            if (currPrice.get() > UserData.getCredit()
                    || shipCurrHealth.get() + currQuantity.get() > shipMaxHealth) {
                purchaseRepair.setOpacity(0.3);
            } else {
                purchaseRepair.setOpacity(1.0);
            }
        });

        purchaseRepair.setOnAction(e -> {
            if (currPrice.get() < UserData.getCredit()
                    && currQuantity.get() + shipCurrHealth.get() <= shipMaxHealth) {

                // Update ship current health
                UserData.getShip().setCurrentHealth(currQuantity.get() + shipCurrHealth.get());

                //update local current health variable
                shipCurrHealth.set(UserData.getShip().getCurrentHealth());

                // Update user credits
                UserData.setCredit(UserData.getCredit() - currPrice.get());

                // Update current credit display
                currentCredits.setText("Current credits: " + UserData.getCredit());

                // Update current ship health display
                currentHealthInPopup.setText("Current health: "
                        + UserData.getShip().getCurrentHealth());

                // Update appearance of purchase button
                if (currPrice.get() > UserData.getCredit()
                        || shipCurrHealth.get() + currQuantity.get() > shipMaxHealth) {
                    purchaseRepair.setOpacity(0.3);
                } else {
                    purchaseRepair.setOpacity(1.0);
                }


            }
        });

        // This button does 10 damage to the ship (for testing purposes)
        Button damageMyShip = new Button("Damage Ship");
        damageMyShip.setOnAction(e -> {
            // Update ship health
            UserData.getShip().setCurrentHealth(UserData.getShip().getCurrentHealth() - 10);

            // Update display of ship health
            currentHealthInPopup.setText("Current health: " + UserData.getShip().getCurrentHealth());

            // Update local health variable (why does this exist again? I don't know)
            shipCurrHealth.set(UserData.getShip().getCurrentHealth());

            // Update appearance of purchase button
            if (currPrice.get() > UserData.getCredit()
                    || shipCurrHealth.get() + currQuantity.get() > shipMaxHealth) {
                purchaseRepair.setOpacity(0.3);
            } else {
                purchaseRepair.setOpacity(1.0);
            }
        });


        // Fix spacing
        // Have engineering level affect price of repair
        // Way to get back to menu

        HBox healthSpecs = new HBox(buyLessHealth, currentToPurchase, buyMoreHealth);
        healthSpecs.setAlignment(Pos.CENTER);
        healthSpecs.setSpacing(13);

        Text healthQuantityToBuy = new Text("Quantity");
        healthQuantityToBuy.setStyle("-fx-font: 15 arial;");

        VBox healthSpecsAndQuantity = new VBox(currentHealthInPopup, healthQuantityToBuy,
                healthSpecs, currentPriceText, purchaseRepair);
        healthSpecsAndQuantity.setAlignment(Pos.CENTER);







        VBox healthDisplay = new VBox(healthSpecsAndQuantity, damageMyShip);
        healthDisplay.setAlignment(Pos.CENTER);
        healthDisplay.setSpacing(15);
        healthDisplay.setStyle(cssLayout);

        // end Vbox for repairing ship




        HBox shipSpecDisplay = new HBox(fuelDisplay, healthDisplay);
        shipSpecDisplay.setSpacing(15);

        VBox shipSpecsAndCredits = new VBox(currentCredits, shipSpecDisplay);


        ImageView shipImage = new ImageView(UserData.getShip().getModel().getShipImage());

        HBox specsAndImage = new HBox(shipSpecsAndCredits, shipImage);
        specsAndImage.setAlignment(Pos.CENTER);
        specsAndImage.setSpacing(50);
        specsAndImage.setMinSize(1000, 600);
        specsAndImage.setMaxSize(1000, 600);

        specsAndImage.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                goBackOneScene();
            }
        });

        return new Scene(specsAndImage);
    }
}
