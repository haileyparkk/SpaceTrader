package scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameCredits extends SpaceTraderScene {

    public GameCredits(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene get() {

        Text authors = new Text("Credits (Alphabetical Order):\n"
                + "Mary Beth Lord\nDrew Harris\nJoseph Lukemire\nHailey Park\nTy Selman");
        authors.setFill(Color.BLACK);

        Button startNewGame = new Button("Start New Game");

        startNewGame.setOnAction(e -> {
            primaryStage.setScene(new Welcome(primaryStage).get());
        });

        VBox vboxFinal = new VBox(authors, startNewGame);

        return new Scene(vboxFinal);
    }
}
