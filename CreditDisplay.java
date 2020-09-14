package scenes;

import data.UserData;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import mediaPlayers.AudioPlayer;
import styles.ButtonStyle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays the number of credits the user has.
 *
 * @author Andrew Harris, Joseph Lukemire
 * @version 1.0
 */
public class CreditDisplay extends SpaceTraderScene {
    
    /**
     * Constructor.
     *
     * @param primaryStage the primary stage
     */
    public CreditDisplay(Stage primaryStage) {
        super(primaryStage);
    }
    
    /**
     * Displays how many credits you have
     *
     * @return the scene in a vBox
     */
    public Scene get() {
        Text creditText;
        Text nameText;
        Text difficultyText;
        Text engineerText;
        Text fighterText;
        Text merchantText;
        Text pilotText;

        if (UserData.getName() != null) {
            creditText = new Text("You have " + UserData.getCredit() + " credits");
            nameText = new Text("Username: " + UserData.getName());
            difficultyText = new Text("Difficulty: " + UserData.getDifficulty());
            engineerText = new Text("Engineer points: " + UserData.getEngineer());
            fighterText = new Text("Fighter points: " + UserData.getFighter());
            merchantText = new Text("Merchant points: " + UserData.getMerchant());
            pilotText = new Text("Pilot points: " + UserData.getPilot());
        } else {
            creditText = new Text();
            nameText = new Text("Username: N/A");
            difficultyText = new Text("Difficulty: N/A");
            engineerText = new Text("Engineer points: N/A");
            fighterText = new Text("Fighter points: N/A");
            merchantText = new Text("Merchant points: N/A");
            pilotText = new Text("Pilot points: N/A");
        }

        creditText.setFill(Color.WHITE);
        nameText.setFill(Color.WHITE);
        difficultyText.setFill(Color.WHITE);
        engineerText.setFill(Color.WHITE);
        fighterText.setFill(Color.WHITE);
        merchantText.setFill(Color.WHITE);
        pilotText.setFill(Color.WHITE);

        Font font = new Font("arial", 20.0);
        creditText.setFont(font);
        nameText.setFont(font);
        difficultyText.setFont(font);
        engineerText.setFont(font);
        fighterText.setFont(font);
        merchantText.setFont(font);
        pilotText.setFont(font);

        ImageView whiteX = new ImageView(new Image("images/WhiteX.png"));
        ImageView audioImage = new ImageView(new Image("images/AudioImage.png"));

        whiteX.setPreserveRatio(true);
        audioImage.setPreserveRatio(true);

        audioImage.setFitHeight(30.0);
        whiteX.setFitHeight(30.0);

        StackPane audioButton = new StackPane(audioImage);
        audioButton.setOnMouseClicked(e -> {
            if (AudioPlayer.getMusicPlaying()) {
                audioButton.getChildren().add(whiteX);
                AudioPlayer.muteMusic();
            } else {
                audioButton.getChildren().remove(whiteX);
                AudioPlayer.unmuteMusic();
            }
        });

        if (!AudioPlayer.getMusicPlaying()) {
            audioButton.getChildren().add(whiteX);
        }

        Button backButton = new Button("BACK");
        backButton.setMinSize(100, 50);
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle(ButtonStyle.menu.get());
        backButton.setOnMouseEntered(e ->
                backButton.setStyle(ButtonStyle.menuHighlighted.get())
        );
        backButton.setOnMouseExited(e ->
                backButton.setStyle(ButtonStyle.menu.get())
        );
        backButton.setOnMouseClicked(e ->
                primaryStage.setScene(new Menu(primaryStage).get())
        );

        Image imageForBackground = new Image("images/background.jpg");
        BackgroundImage backgroundImage = new
            BackgroundImage(imageForBackground, BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        VBox vBox = new VBox();
        vBox.setMinSize(1000, 600);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        vBox.getChildren().addAll(backButton, nameText, difficultyText, pilotText,
            fighterText, merchantText, engineerText, creditText, audioButton);
        vBox.setBackground(background);

        return new Scene(vBox);
    }
}
