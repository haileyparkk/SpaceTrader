package scenes;

import data.Upgrade;
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

public class UpgradeMarketDisplay extends SpaceTraderScene {
    private Upgrade upgrade;

    public UpgradeMarketDisplay(Stage primaryStage, Upgrade upgrade) {
        super(primaryStage);
        this.upgrade = upgrade;
    }

    @Override
    public Scene get() {
        VBox upgradeInfo = new VBox();
        upgradeInfo.setSpacing(20.0);
        upgradeInfo.setAlignment(Pos.CENTER);

        Text name = new Text(upgrade.getName());
        if (UserData.getOwnedUpgrades() != null && UserData.getOwnedUpgrades().contains(upgrade)) {
            name.setText(upgrade.getName() + " (owned)");
        }
        name.setStyle("-fx-font: 20 verdana");
        name.setFill(Color.WHITE);

        Text affectedSkill = new Text("Affected Skill: " + upgrade.getSkill());
        affectedSkill.setStyle("-fx-font: 15 verdana");
        affectedSkill.setFill(Color.WHITE);

        Text description = new Text("Description: " + upgrade.getDescription());
        description.setStyle("-fx-font: 15 verdana");
        description.setFill(Color.WHITE);

        Text price = new Text("Price: " + upgrade.getPrice());
        price.setStyle("-fx-font: 15 verdana");
        price.setFill(Color.WHITE);

        Text currentCredits = new Text("User Credits: " + UserData.getCredit());
        currentCredits.setStyle("-fx-font: 15 verdana");
        currentCredits.setFill(Color.WHITE);

        Text purchaseDenied = new Text();
        purchaseDenied.setStyle("-fx-font: 15 verdana");
        purchaseDenied.setFill(Color.BLACK);

        // Button for purchasing the upgrade
        Button purchase = new Button("Purchase");
        purchase.setOnMouseClicked(e -> {
            if (UserData.getOwnedUpgrades() != null
                    && UserData.getOwnedUpgrades().contains(upgrade)) {
                purchaseDenied.setText("You already own this item.");
                purchaseDenied.setFill(Color.RED);
            } else if (UserData.getCredit() - upgrade.getPrice() >= 0) {
                UserData.addUpgrade(upgrade);
                UserData.setCredit(UserData.getCredit() - upgrade.getPrice());
                currentCredits.setText("User Credits: " + UserData.getCredit());
                name.setText(upgrade.getName() + " (owned)");
            } else {
                purchaseDenied.setText("You can't afford this. Get a job!");
                purchaseDenied.setFill(Color.RED);
            }
        });

        Button exit = new Button("Cancel");
        exit.setOnMouseClicked(e -> {
            goBackOneScene();
        });

        upgradeInfo.getChildren().addAll(name, affectedSkill, description,
                price, currentCredits, purchase, purchaseDenied, exit);

        // HBox to separate image from description
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        // Image for left side of screen
        Image upgradeImage = new Image(upgrade.getImagePath());
        ImageView imageView = new ImageView(upgradeImage);

        hbox.getChildren().addAll(imageView, upgradeInfo);
        hbox.setMinSize(1000, 600);
        hbox.setBackground(getBackgroundWithFill(Color.BLACK));
        return new Scene(hbox);
    }

}
