package scenes;

import data.Upgrade;
import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import objects.Region;
import data.Good;
import data.PriceGenerator;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Generates the marketplace scene.
 *
 * @author Mary Beth Lord, Andrew Harris
 * @version 1.0
 */
public class MarketplaceInventory extends scenes.SpaceTraderScene {

    public MarketplaceInventory(Stage primaryStage) {
        super(primaryStage);
    }

    public Scene get() {
        Pane[] array = new Pane[9];

        Region region = UserData.getCurrentRegion();
        PriceGenerator priceGen = region.getPrices();
        Good[] goods = region.getGoods();

        Text title = new Text(region.getRegionName());
        title.setFont(new Font("verdana", 42.0));
        title.setFill(Color.WHITE);

        VBox titleBG = new VBox(title);
        titleBG.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        titleBG.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderWidths.DEFAULT)));


        Text back = new Text("Back");
        back.setFont(new Font("verdana", 20.0));
        back.setFill(Color.WHITE);
        VBox backG = new VBox(back);
        backG.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        backG.setAlignment(Pos.CENTER);
        backG.setMaxWidth(36);
        backG.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderWidths.DEFAULT)));
        backG.setOnMouseClicked(e -> {
            goBackOneScene();
        });

        VBox vBox1 = new VBox(backG);
        VBox vBox2 = new VBox(titleBG);
        VBox vBox3 = new VBox();

        vBox1.setAlignment(Pos.BOTTOM_CENTER);
        vBox2.setAlignment(Pos.BOTTOM_CENTER);
        vBox3.setAlignment(Pos.BOTTOM_CENTER);

        vBox1.setSpacing(20.0);
        vBox2.setSpacing(20.0);
        vBox3.setSpacing(20.0);

        for (int i = 0; i < 9; i++) {

            Text name = new Text(goods[i].getName());
            name.setFont(new Font("verdana", 20.0));
            name.setFill(Color.WHITE);

            Text buyText = new Text("Buy: $" + priceGen.getBuyPrice(i));
            buyText.setFill(Color.WHITE);
            Text sellText = new Text("Sell: $" + priceGen.getSellPrice(i));
            sellText.setFill(Color.WHITE);

            Font buySellFont = new Font("Verdana", 15.0);
            buyText.setFont(buySellFont);
            sellText.setFont(buySellFont);

            VBox miniVBox = new VBox(buyText, sellText);
            VBox mainVBox = new VBox(name, miniVBox);

            mainVBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

            if (i % 3 == 0) {
                vBox1.getChildren().add(mainVBox);
            }
            if (i % 3 == 1) {
                vBox2.getChildren().add(mainVBox);
            }
            if (i % 3 == 2) {
                vBox3.getChildren().add(mainVBox);
            }

            mainVBox.setAlignment(Pos.CENTER);
            mainVBox.setAlignment(Pos.CENTER_LEFT);

            int num = i;
            mainVBox.setOnMouseClicked(m -> {
                UserData.setSelectedIndex(num);
                setScene(new MarketplaceItemInfo(primaryStage).get());
            });

            mainVBox.setPrefSize(200.0, 150.0);
            mainVBox.setMaxSize(200.0, 150.0);
            mainVBox.setAlignment(Pos.CENTER);

            miniVBox.setPrefWidth(100.0);
            miniVBox.setAlignment(Pos.CENTER_LEFT);
            miniVBox.setMaxWidth(100.0);

            mainVBox.setBackground(new Background(new BackgroundFill(Color.BLACK,
                    null, null)));
            mainVBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                    BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderWidths.DEFAULT)));

            array[1] = mainVBox;
        }

        HBox hBox = new HBox(vBox1, vBox2, vBox3);

        hBox.setMinSize(1000.0, 600.0);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane(hBox);
        HBox hBox2 = new HBox();
        hBox2.setPrefHeight(12.0);
        borderPane.setBottom(hBox2);
        //borderPane.setBackground(new Background(new BackgroundImage(new Image("images/space.jpg"),
        // BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        // new BackgroundSize(1000, 800, false, false, false, false))));

        borderPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                setScene(new Menu(primaryStage).get());
            }
        });

        // New VBox to put region's available upgrades at the bottom of the screen
        VBox separateUpgrades = new VBox(borderPane);
        separateUpgrades.setAlignment(Pos.CENTER);
        separateUpgrades.setSpacing(20.0);
        separateUpgrades.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        separateUpgrades.setBackground(new Background(new
                BackgroundImage(new Image("images/space.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1000, 1000, false, false, false, false))));


        Text upgradeText = new Text("Upgrades");
        upgradeText.setFont(new Font("verdana", 42.0));
        upgradeText.setFill(Color.WHITE);
        upgradeText.setUnderline(true);
        HBox upVbox = new HBox(upgradeText);
        upVbox.setAlignment(Pos.CENTER);
        upVbox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                BorderStrokeStyle.SOLID,
                new CornerRadii(2.0), BorderWidths.DEFAULT)));
        upVbox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        upVbox.setMinSize(10.0, 10.0);
        upVbox.setMaxWidth(300);

        separateUpgrades.getChildren().add(upVbox);


        VBox winningItemBox = new VBox(5.0);
        if (region.getHasWinningItem()) {
            Text winningItemText = new Text("Calf Implants (Game Winner)");
            Text priceText = new Text("Cost: 1500 Credits");
            winningItemText.setStyle("-fx-font: 20 verdana");
            winningItemText.setFill(Color.WHITE);
            Text effectText = new Text("This item wins the game");
            effectText.setStyle("-fx-font: 15 verdana");
            effectText.setFill(Color.WHITE);
            winningItemBox.setBackground(new Background(new BackgroundFill(Color.BLACK,
                    null, null)));
            winningItemBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(2.0), BorderWidths.DEFAULT)));
            winningItemBox.setMinSize(100.0, 100.0);
            winningItemBox.getChildren().addAll(winningItemText, priceText, effectText);
            winningItemBox.setOnMouseClicked(e -> {
                if (UserData.getCredit() >= 1500) {
                    setScene(new BuyGameWinningItem(primaryStage).get());
                }
            });
        }

        /* this HBox will go inside of the separateUpgrades VBox to arrange
        upgrade boxes along the bottom of
        the Marketplace
        */
        HBox upgradeContainer = new HBox();
        upgradeContainer.setSpacing(20.0);
        upgradeContainer.setAlignment(Pos.CENTER);


        /* looping through upgrades that the region offers and adding
        them to boxes to be displayed along the bottom
        of the Marketplace
         */
        for (Upgrade upgrade: region.getUpgrades()) {
            Text upgradeName = new Text(upgrade.getName());
            upgradeName.setStyle("-fx-font: 20 verdana");
            upgradeName.setFill(Color.WHITE);
            Text skillAffected = new Text("Affected skill: " + upgrade.getSkill().name());
            skillAffected.setStyle("-fx-font: 15 verdana");
            skillAffected.setFill(Color.WHITE);
            VBox upgradeBox = new VBox(5.0);
            upgradeBox.setBackground(new Background(new BackgroundFill(Color.BLACK,
                    null, null)));
            upgradeBox.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.WHITE,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(2.0), BorderWidths.DEFAULT)));
            upgradeBox.setMinSize(100.0, 100.0);
            upgradeBox.getChildren().addAll(upgradeName, skillAffected);
            upgradeBox.setOnMouseClicked(e -> {
                setScene(new UpgradeMarketDisplay(primaryStage, upgrade).get());
            });
            upgradeContainer.getChildren().addAll(upgradeBox, winningItemBox); // this one will need the game winner upgrade
        }

        separateUpgrades.getChildren().addAll(upgradeContainer);
        ScrollPane scrollPane = new ScrollPane(separateUpgrades);


        return new Scene(scrollPane);
    }
}