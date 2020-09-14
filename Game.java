package scenes;

import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * class representing the game
 * @author Joseph Lukemire, Andrew Harris
 * @version 1.0
 */
public class Game extends SpaceTraderScene {

    /**
     * constructs the game
     * @param primaryStage the stage
     */
    public Game(Stage primaryStage) {
        super(primaryStage);
    }

    /**
     * shows the game
     * @return the scene
     */
    public Scene get() {
        Button marketplaceButton = new Button("Visit Marketplace");

        marketplaceButton.setOnAction(e -> setScene(new MarketplaceInventory(primaryStage).get()));

        Button goToMap = new Button("Leave Region");
        goToMap.setOnAction(e ->
                setScene(new UniverseDisplay(primaryStage, UserData.getUniverse()).get()));

        Text regionText = new Text(" " + UserData.getCurrentRegion().getRegionName() + " ");
        regionText.setFont(new Font(32.0));
        regionText.setTextAlignment(TextAlignment.CENTER);

        VBox regionPane = new VBox(regionText);
        regionPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(2.0),
                BorderWidths.DEFAULT)));
        regionPane.setMaxWidth(regionPane.getWidth());
        regionPane.setMinHeight(regionPane.getHeight() + 50.0);
        regionPane.setAlignment(Pos.CENTER);
        regionPane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                null, null)));

        VBox mapPane = new VBox(goToMap);
        mapPane.setPrefHeight(600.0 - regionPane.getHeight());
        mapPane.setAlignment(Pos.CENTER);

        Pane emptyPane = new Pane(new Text(""));
        emptyPane.setMinHeight(regionPane.getMinHeight());

        VBox marketplaceButtonPane = new VBox(marketplaceButton);
        marketplaceButtonPane.setPrefHeight(mapPane.getPrefHeight());
        marketplaceButtonPane.setAlignment(Pos.CENTER);

        VBox leftVBox = new VBox(emptyPane, marketplaceButtonPane);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setPrefWidth(500.0);

        leftVBox.setBackground(new Background(new BackgroundImage(
                new Image("images/MarketplaceGraphic.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1300.0, 650.0,
                        false, false, false, false))));

        VBox rightVBox = new VBox(regionPane, mapPane);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setPrefWidth(500.0);

        rightVBox.setBackground(new Background(new BackgroundImage(
                new Image("images/WelcomeImage.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        HBox hBox = new HBox(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                setScene(new Menu(primaryStage).get());
            }
        });

        return new Scene(hBox);
    }
}
