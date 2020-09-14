package scenes;

import data.GoodList;
import data.ShipModel;
import data.UserData;
import objects.Region;
import objects.Ship;
import objects.Universe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * screen that allows the player to set up their game
 * @author Andrew Harris, Joseph Lukemire
 * @version 1.0
 */
public class Configuration extends SpaceTraderScene {

    private Random r;

    private Font verdana36;
    private Text inputLabel;               // label for name
    private TextField nameInputField;      // input box for name
    private Text difficultyLabel;          // label for difficulty
    private Background background;
    private Button continueButton;   // continues to next stage if everything is full
    private VBox vBox;               // root pane for the scene
    private Text skillPointNum;
    private Background whiteBackground;
    private BackgroundFill whiteBackgroundFill;
    private Background yellowBackground;
    private BackgroundFill yellowBackgroundFill;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private HBox warningHBox;
    private Text warningText;
    private Text pilotNum;
    private Text fighterNum;
    private Text merchantNum;
    private Text engineerNum;
    private Button pilotPlus;
    private Button pilotMinus;
    private Button fighterPlus;
    private Button fighterMinus;
    private Button merchantPlus;
    private Button merchantMinus;
    private Button engineerPlus;
    private Button engineerMinus;

    /**
     * creates the scene
     * @param primaryStage the stage
     */
    public Configuration(Stage primaryStage) {
        super(primaryStage);

    }
    /**
     * starts the scene
     * @return the scene itself
     */
    public Scene get() {
        r = new Random();

        verdana36 = new Font("Verdana", 36.0);
        inputLabel = new Text("Name");
        inputLabel.setFont(verdana36);
        nameInputField = new TextField("");
        nameInputField.setMaxWidth(200);

        difficultyLabel = new Text("Difficulty");
        difficultyLabel.setFont(verdana36);

        inputLabel.setFill(Color.WHITE);
        difficultyLabel.setFill(Color.WHITE);

        easyButton = new Button("Easy");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");

        HBox difficultyHBox = new HBox();
        difficultyHBox.setAlignment(Pos.CENTER);
        difficultyHBox.getChildren().addAll(easyButton, mediumButton,
                hardButton);

        Image imageForBackground = new Image("images/background.jpg");
        BackgroundImage backgroundImage = new
                BackgroundImage(imageForBackground, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        background = new Background(backgroundImage);

        continueButton = new Button("Continue");
        continueButton.setOnAction(new ContinueButtonEvent());

        easyButton.setOnAction(new EasyButtonEvent());
        mediumButton.setOnAction(new MediumButtonEvent());
        hardButton.setOnAction(new HardButtonEvent());

        Text skillPointLabel = new Text("Remaining Skill Points:");
        skillPointLabel.setFont(verdana36);
        skillPointNum = new Text("?");
        skillPointNum.setFont(verdana36);
        skillPointNum.setFill(Color.WHITE);
        skillPointLabel.setFill(Color.WHITE);

        VBox skillPointVBox = new VBox();

        HBox pilotHBox = new HBox();
        HBox fighterHBox = new HBox();
        HBox merchantHBox = new HBox();
        HBox engineerHBox = new HBox();

        pilotPlus = new Button("+");
        pilotMinus = new Button("-");
        fighterPlus = new Button("+");
        fighterMinus = new Button("-");
        merchantPlus = new Button("+");
        merchantMinus = new Button("-");
        engineerPlus = new Button("+");
        engineerMinus = new Button("-");

        pilotPlus.setOnAction(new PilotPlusEvent());
        pilotMinus.setOnAction(new PilotMinusEvent());
        fighterPlus.setOnAction(new FighterPlusEvent());
        fighterMinus.setOnAction(new FighterMinusEvent());
        merchantPlus.setOnAction(new MerchantPlusEvent());
        merchantMinus.setOnAction(new MerchantMinusEvent());
        engineerPlus.setOnAction(new EngineerPlusEvent());
        engineerMinus.setOnAction(new EngineerMinusEvent());

        pilotHBox.setAlignment(Pos.CENTER);
        fighterHBox.setAlignment(Pos.CENTER);
        merchantHBox.setAlignment(Pos.CENTER);
        engineerHBox.setAlignment(Pos.CENTER);

        Text pilotLabel = new Text("Pilot Points:");
        Text fighterLabel = new Text("Fighter Points:");
        Text merchantLabel = new Text("Merchant Points:");
        Text engineerLabel = new Text("Engineer Points:");

        pilotLabel.setFill(Color.WHITE);
        fighterLabel.setFill(Color.WHITE);
        merchantLabel.setFill(Color.WHITE);
        engineerLabel.setFill(Color.WHITE);

        pilotNum = new Text("0");
        fighterNum = new Text("0");
        merchantNum = new Text("0");
        engineerNum = new Text("0");

        pilotNum.setFill(Color.WHITE);
        fighterNum.setFill(Color.WHITE);
        merchantNum.setFill(Color.WHITE);
        engineerNum.setFill(Color.WHITE);

        pilotNum.setFont(verdana36);
        fighterNum.setFont(verdana36);
        merchantNum.setFont(verdana36);
        engineerNum.setFont(verdana36);

        pilotHBox.getChildren().addAll(pilotLabel, pilotMinus, pilotNum, pilotPlus);
        fighterHBox.getChildren().addAll(fighterLabel, fighterMinus, fighterNum, fighterPlus);
        merchantHBox.getChildren().addAll(merchantLabel, merchantMinus, merchantNum, merchantPlus);
        engineerHBox.getChildren().addAll(engineerLabel, engineerMinus, engineerNum, engineerPlus);

        whiteBackgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        yellowBackgroundFill = new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY);
        whiteBackground = new Background(whiteBackgroundFill);
        yellowBackground = new Background(yellowBackgroundFill);

        easyButton.setBackground(whiteBackground);
        mediumButton.setBackground(whiteBackground);
        hardButton.setBackground(whiteBackground);

        warningText = new Text("");
        warningText.setFont(new Font("Verdana", 12.0));
        warningText.setFill(Color.WHITE);

        warningHBox = new HBox();
        warningHBox.getChildren().addAll(warningText);
        warningHBox.setAlignment(Pos.CENTER);

        vBox = new VBox(inputLabel, nameInputField, difficultyLabel,
                difficultyHBox, skillPointLabel, skillPointNum,
                pilotHBox, fighterHBox, merchantHBox, engineerHBox, continueButton, warningHBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(background);
        vBox.setMinSize(1000, 600);

        return new Scene(vBox);
    }

    /**
     * class representing continue button press
     */
    class ContinueButtonEvent implements EventHandler<ActionEvent> {
        /**
         * handles the button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            try {
                if (nameInputField.getText().equals("")) {
                    warningText.setText("Please enter a name");
                } else if (skillPointNum.getText().equals("?")) {
                    warningText.setText("Please select a difficulty");
                } else if (!(skillPointNum.getText().equals("0"))) {
                    warningText.setText("Please assign your remaining skill points");
                } else {
                    UserData.setName(nameInputField.getText());
                    if (easyButton.getBackground() == yellowBackground) {
                        UserData.setCredit(1000);
                    } else if (mediumButton.getBackground() == yellowBackground) {
                        UserData.setCredit(500);
                    } else {
                        UserData.setCredit(100);
                    }
                    UserData.setPilot(Integer.parseInt(pilotNum.getText()));
                    UserData.setFighter(
                            Integer.parseInt(fighterNum.getText()));
                    UserData.setMerchant(
                            Integer.parseInt(merchantNum.getText()));
                    UserData.setEngineer(
                            Integer.parseInt(engineerNum.getText()));
                    UserData.setSkillPoints(UserData.getPilot()
                            + UserData.getFighter() + UserData.getMerchant()
                            + UserData.getEngineer());




                    // Universe is created here
                    // Added Feb. 9 - switches primary scene to universe display for M4

                    Universe playerUniverse = new Universe.UniverseBuilder()
                            .withRegionCount(10)
                            .withMapSize(1000, 600)
                            .build();
                    UserData.setUniverse(playerUniverse);
                    Region region = UserData.getUniverse().getRegions()[r.nextInt(10)];
                    UserData.setCurrentRegion(region);
                    region.setDiscovered(true);
                    System.out.println("Randomly generated new universe with galaxy count: "
                            + playerUniverse.getRegions().length);
                    System.out.println("Randomly set current region to "
                            + region.getRegionName());
                    GoodList.makeList();
                    region.setPrices();
                    primaryStage.setScene(new Game(primaryStage).get());
                    UserData.setShip(new Ship(ShipModel.BIG_CHUNGUS, primaryStage));
                }
            } catch (NumberFormatException ex) {
                return;
            }
        }
    }

    /**
     * class representing an easy button press
     */
    class EasyButtonEvent implements EventHandler<ActionEvent> {
        /**
         * class representing easy button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            easyButton.setBackground(yellowBackground);
            mediumButton.setBackground(whiteBackground);
            hardButton.setBackground(whiteBackground);
            UserData.setSkillPoints(16);
            UserData.setDifficulty("Easy");
            skillPointNum.setText("16");
            pilotNum.setText("0");
            fighterNum.setText("0");
            merchantNum.setText("0");
            engineerNum.setText("0");
        }
    }

    /**
     * class representing a medium button press
     */
    class MediumButtonEvent implements EventHandler<ActionEvent> {
        /**
         * class representing easy button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            easyButton.setBackground(whiteBackground);
            mediumButton.setBackground(yellowBackground);
            hardButton.setBackground(whiteBackground);
            UserData.setSkillPoints(12);
            UserData.setDifficulty("Medium");
            skillPointNum.setText("12");
            pilotNum.setText("0");
            fighterNum.setText("0");
            merchantNum.setText("0");
            engineerNum.setText("0");
        }
    }

    /**
     * class representing a hard button press
     */
    class HardButtonEvent implements EventHandler<ActionEvent> {
        /**
         * class representing easy button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            easyButton.setBackground(whiteBackground);
            mediumButton.setBackground(whiteBackground);
            hardButton.setBackground(yellowBackground);
            UserData.setSkillPoints(8);
            UserData.setDifficulty("Hard");
            skillPointNum.setText("8");
            pilotNum.setText("0");
            fighterNum.setText("0");
            merchantNum.setText("0");
            engineerNum.setText("0");
        }
    }

    /**
     * handles the pilot plus button press
     */
    class PilotPlusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the pilot plus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int pilotN = Integer.parseInt(pilotNum.getText());
                if (skillPointN > 0) {
                    pilotNum.setText(Integer.toString(pilotN + 1));
                    skillPointNum.setText(Integer.toString(skillPointN - 1));
                }
            }
        }
    }

    /**
     * handles the pilot minus button press
     */
    class PilotMinusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the pilot minus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int pilotN = Integer.parseInt(pilotNum.getText());
                if (pilotN > 0) {
                    skillPointNum.setText(Integer.toString(skillPointN + 1));
                    pilotNum.setText(Integer.toString(pilotN - 1));
                }
            }
        }
    }

    /**
     * handles the fighter plus button press
     */
    class FighterPlusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the fighter plus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int fighterN = Integer.parseInt(fighterNum.getText());
                if (skillPointN > 0) {
                    fighterNum.setText(Integer.toString(fighterN + 1));
                    skillPointNum.setText(Integer.toString(skillPointN - 1));
                }
            }
        }
    }

    /**
     * handles the fighter minus button press
     */
    class FighterMinusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the fighter minus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int fighterN = Integer.parseInt(fighterNum.getText());
                if (fighterN > 0) {
                    skillPointNum.setText(Integer.toString(skillPointN + 1));
                    fighterNum.setText(Integer.toString(fighterN - 1));
                }
            }
        }
    }

    /**
     * handles the merchant plus button press
     */
    class MerchantPlusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the merchant plus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int merchantPointN = Integer.parseInt(merchantNum.getText());
                if (skillPointN > 0) {
                    merchantNum.setText(Integer.toString(merchantPointN + 1));
                    skillPointNum.setText(Integer.toString(skillPointN - 1));
                }
            }
        }
    }

    /**
     * handles the merchant minus button press
     */
    class MerchantMinusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the merchant minus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int merchantN = Integer.parseInt(merchantNum.getText());
                if (merchantN > 0) {
                    skillPointNum.setText(Integer.toString(skillPointN + 1));
                    merchantNum.setText(Integer.toString(merchantN - 1));
                }
            }
        }
    }

    /**
     * handles the engineer plus button press
     */
    class EngineerPlusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the engineer plus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int engineerN = Integer.parseInt(engineerNum.getText());
                if (skillPointN > 0) {
                    engineerNum.setText(Integer.toString(engineerN + 1));
                    skillPointNum.setText(Integer.toString(skillPointN - 1));
                }
            }
        }
    }

    /**
     * handles the engineer minus button press
     */
    class EngineerMinusEvent implements EventHandler<ActionEvent> {
        /**
         * handles the engineer minus button press
         * @param aE the event
         */
        @Override
        public void handle(ActionEvent aE) {
            if (skillPointNum.getText() != "?") {
                int skillPointN = Integer.parseInt(skillPointNum.getText());
                int engineerN = Integer.parseInt(engineerNum.getText());
                if (engineerN > 0) {
                    skillPointNum.setText(Integer.toString(skillPointN + 1));
                    engineerNum.setText(Integer.toString(engineerN - 1));
                }
            }
        }
    }
}