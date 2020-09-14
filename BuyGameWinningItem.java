package scenes;

import data.UserData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BuyGameWinningItem extends SpaceTraderScene {

    public BuyGameWinningItem(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene get() {
        Text confirm = new Text("Buy the Holy Relic and end the game?");

        Image giantCalves = new Image("images/unrealCalves.jpg");
        ImageView calvesView = new ImageView(giantCalves);


        Button confirmButton = new Button("YES");
        confirmButton.setOnMouseClicked(e -> {
            UserData.setHasWinningItem();
            primaryStage.setScene(new GameOver(primaryStage).get());
        });

        Button cancelButton = new Button("NO");
        cancelButton.setOnMouseClicked(e -> {
            goBackOneScene();
        });
        HBox buttonLayout = new HBox(confirmButton, cancelButton);

        VBox vboxImageView = new VBox(confirm, calvesView, buttonLayout);
        vboxImageView.setSpacing(15);
        vboxImageView.setAlignment(Pos.CENTER);
        vboxImageView.setBackground(getBackgroundWithFill(Color.GOLD));
        vboxImageView.setMinSize(1000, 600);

        return new Scene(vboxImageView);
    }
}
