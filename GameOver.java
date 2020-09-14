package scenes;

import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOver extends SpaceTraderScene {

    public GameOver(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public Scene get() {

        Text outcome = new Text("GAME OVER\nYOU LOST");

        if (UserData.getHasWinningItem()) {
            outcome.setText("GAME OVER\nYOU WON");
        }

        Button goToCredits = new Button("Credits");

        goToCredits.setOnAction(e -> {
            primaryStage.setScene(new GameCredits(primaryStage).get());
        });

        VBox vboxFinal = new VBox(outcome, goToCredits);
        vboxFinal.setSpacing(50);
        vboxFinal.setAlignment(Pos.CENTER);
        vboxFinal.setMinSize(1000, 600);

        return new Scene(vboxFinal);
    }
}
