package scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import styles.ButtonStyle;

/**
 * This class contains the functionality/graphics for the welcome screen
 * which is displayed on startup.
 * @author Joseph Lukemire, DMD
 * @version 1.0
 */
public class Welcome extends SpaceTraderScene {

    /**
     * Creates a new Welcome object.
     * @param primaryStage Stage which will change frames
     */
    public Welcome(Stage primaryStage) {
        super(primaryStage);
    }

    /**
     * Generates and returns the welcome screen javafx scene.
     * @return new scene object displaying the welcome
     */
    public Scene get() {
        Button startGame = new Button("Start Game");
        startGame.setStyle(ButtonStyle.menu.get());
        startGame.setTextFill(Color.WHITE);

        startGame.setOnMouseEntered(e ->
                startGame.setStyle(ButtonStyle.menuHighlighted.get())
        );
        startGame.setOnMouseExited(e ->
                startGame.setStyle(ButtonStyle.menu.get())
        );
        startGame.setOnAction(e -> {
            primaryStage.setScene(new Configuration(primaryStage).get());
        });

        Background background;
        Text titleText = new Text("SPACE TRADER");

        Text howToOpenMenuText = new Text("press 'm' for menu");
        howToOpenMenuText.setFill(Color.WHITE);
        howToOpenMenuText.setStyle("-fx-font: 20 arial;");


        Image imageForBackground = new
            Image("images/welcomeGif.gif");
        BackgroundImage backgroundImage = new
            BackgroundImage(imageForBackground, BackgroundRepeat.REPEAT,
                            BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                            BackgroundSize.DEFAULT);
        background = new Background(backgroundImage);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinSize(1000, 600);
        vbox.setBackground(background);
        vbox.setSpacing(30);
        titleText.setFont(new Font(50));
        titleText.setStyle("-fx-font: 60 arial;");
        titleText.setFill(Color.WHITE);

        vbox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                setScene(new scenes.Menu(primaryStage).get());
            }
        });

        vbox.getChildren().addAll(titleText, howToOpenMenuText, startGame);

        return new Scene(vbox);
    }
}
