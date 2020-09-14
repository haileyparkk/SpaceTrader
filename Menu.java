package scenes;

import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import styles.ButtonStyle;

/**
 * class representing a menu
 * @author Joseph Lukemire
 * @version 1.0
 */
public class Menu extends SpaceTraderScene {

    /**
     * contructs the menu
     * @param primaryStage the stage
     */
    public Menu(Stage primaryStage) {
        super(primaryStage);
    }

    /**
     * creates the menu
     * @return the menu
     */
    public Scene get() {

        // Vbox containing menu options
        VBox menuOptions = new VBox();
        menuOptions.setMinSize(500, 500);
        menuOptions.setMaxSize(500, 500);
        menuOptions.setStyle("-fx-background-color: BLACK");
        menuOptions.setAlignment(Pos.CENTER);
        menuOptions.setSpacing(30);
        menuOptions.setOpacity(0.85);

        // Creating Vbox nodes
        Text menuText = new Text("SPACE TRADER MENU");
        menuText.setStyle("-fx-font: 40 arial;");
        menuText.setFill(Color.WHITE);

        Text howToLeaveText = new Text("Press 'm' to return to game");
        howToLeaveText.setStyle("-fx-font: 15 arial");
        howToLeaveText.setFill(Color.WHITE);


        // Settings Button
        Button settingsButton = new Button("SETTINGS");
        settingsButton.setTextFill(Color.WHITE);
        settingsButton.setStyle(ButtonStyle.menu.get());
        settingsButton.setMinSize(150, 50);
        settingsButton.setMaxSize(150, 50);

        settingsButton.setOnMouseEntered(e ->
                settingsButton.setStyle(ButtonStyle.menuHighlighted.get())
        );
        settingsButton.setOnMouseExited(e ->
                settingsButton.setStyle(ButtonStyle.menu.get())
        );
        settingsButton.setOnMouseClicked(e ->
                primaryStage.setScene(new CreditDisplay(primaryStage).get()));

        /*
        // Show Map Button
        Button showMapButton = new Button("MAP");
        if (UserData.getUniverse() == null) {
            showMapButton.setOpacity(0.3);
        } else {
            showMapButton.setOnMouseEntered(e -> {
                showMapButton.setStyle(ButtonStyle.menuHighlighted.get());
            });
            showMapButton.setOnMouseExited(e -> {
                showMapButton.setStyle(ButtonStyle.menu.get());
            });
            showMapButton.setOnAction(e -> {
                setScene(new UniverseDisplay(primaryStage, UserData.getUniverse()).get());
            });
        }
        showMapButton.setTextFill(Color.WHITE);
        showMapButton.setStyle(ButtonStyle.menu.get());
        showMapButton.setMinSize(150, 50);
        showMapButton.setMaxSize(150, 50);
*/

        // Quit Button
        Button exitButton = new Button("QUIT GAME");
        exitButton.setTextFill(Color.WHITE);
        exitButton.setStyle(ButtonStyle.menu.get());
        exitButton.setMinSize(150, 50);
        exitButton.setMaxSize(150, 50);

        exitButton.setOnMouseEntered(e ->
                exitButton.setStyle(ButtonStyle.menuHighlighted.get())
        );
        exitButton.setOnMouseExited(e ->
                exitButton.setStyle(ButtonStyle.menu.get())
        );
        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        // add some menu children
        menuOptions.getChildren().addAll(menuText, howToLeaveText, settingsButton);

        // Upgrades Button
        Button upgradesButton;
        if (UserData.getCurrentRegion() != null) {
            upgradesButton = new Button("UPGRADES");
            upgradesButton.setTextFill(Color.WHITE);
            upgradesButton.setStyle(ButtonStyle.menu.get());
            upgradesButton.setMinSize(150, 50);
            upgradesButton.setMaxSize(150, 50);
            upgradesButton.setOnMouseEntered(e ->
                    upgradesButton.setStyle(ButtonStyle.menuHighlighted.get())
            );
            upgradesButton.setOnMouseExited(e ->
                    upgradesButton.setStyle(ButtonStyle.menu.get())
            );
            upgradesButton.setOnMouseClicked(e ->
                    setScene(new UpgradeDisplay(primaryStage).get())
            );
            menuOptions.getChildren().add(upgradesButton);
        }

        Button shipButton = new Button("SHIP");
        shipButton.setTextFill(Color.WHITE);
        shipButton.setStyle(ButtonStyle.menu.get());
        shipButton.setMinSize(150, 50);
        shipButton.setMaxSize(150, 50);

        shipButton.setOnMouseEntered(e ->
                shipButton.setStyle(ButtonStyle.menuHighlighted.get())
        );
        shipButton.setOnMouseExited(e ->
                shipButton.setStyle(ButtonStyle.menu.get())
        );

        shipButton.setOnMouseClicked(e -> {
            setScene(new ShipMenu(primaryStage).get());
        });





        // finish adding menu children
        menuOptions.getChildren().addAll(shipButton, exitButton);

        // Creating background on which menu sits
        StackPane backgroundStack = new StackPane();
        backgroundStack.setBackground(getBackgroundWithImage("images/welcomeGif.gif"));
        backgroundStack.setMinSize(1000, 600);

        backgroundStack.getChildren().addAll(menuOptions);

        backgroundStack.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                goBackOneScene();
            }
        });
        return new Scene(backgroundStack);
    }
}
