package scenes;

import data.UserData;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import objects.Encounter;
import objects.Region;
import objects.Universe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.Point;

/**
 * class displaying the universe
 * @author Joseph Lukemire, Andrew Harris
 * @version 1.0
 */
public class UniverseDisplay extends SpaceTraderScene {
    private Universe universe;
    private Circle selectedCircle;
    private Region selectedRegion;

    /**
     * conrtructor
     * @param primaryStage the stage
     * @param universe the universe to be displayed
     */
    public UniverseDisplay(Stage primaryStage, Universe universe) {
        super(primaryStage);
        this.universe = universe;
    }

    /**
     * displays the scene
     * @return the scene
     */
    public Scene get() {
        GridPane map = new GridPane();
        map.setAlignment(Pos.CENTER);
        map.setHgap(0.5);
        map.setVgap(0.5);
        map.setMinSize(universe.getxSize(), universe.getySize());

        Text regionText = new Text();
        regionText.setFill(Color.WHITE);
        regionText.setTextAlignment(TextAlignment.LEFT);
        Rectangle regionTextBox = new Rectangle(250, 100, Color.BLACK);
        StackPane infoStack = new StackPane(regionTextBox, regionText);
        infoStack.setAlignment(Pos.TOP_LEFT);

        Circle foundRegionKey = new Circle(10, Color.BLUE);
        Text foundRegionKeyText = new Text("Discovered Region");
        foundRegionKeyText.setFill(Color.WHITE);
        HBox foundRegionKeyBox = new HBox(foundRegionKey, foundRegionKeyText);
        foundRegionKeyBox.setAlignment(Pos.CENTER_LEFT);
        foundRegionKeyBox.setSpacing(5.0);

        Circle undiscoveredRegionKey = new Circle(10, Color.RED);
        Text undiscoveredRegionKeyText = new Text("Not Yet Discovered");
        undiscoveredRegionKeyText.setFill(Color.WHITE);
        HBox undiscoveredRegionKeyBox = new HBox(undiscoveredRegionKey, undiscoveredRegionKeyText);
        undiscoveredRegionKeyBox.setAlignment(Pos.CENTER_LEFT);
        undiscoveredRegionKeyBox.setSpacing(5.0);

        Circle currentRegionKey = new Circle(10, Color.WHITE);
        Text currentRegionKeyText = new Text("Current Region");
        currentRegionKeyText.setFill(Color.WHITE);
        HBox currentRegionKeyBox = new HBox(currentRegionKey, currentRegionKeyText);
        currentRegionKeyBox.setAlignment(Pos.CENTER_LEFT);
        currentRegionKeyBox.setSpacing(5.0);

        Circle selectedRegionKey = new Circle(10, Color.GREEN);
        Text selectedRegionKeyText = new Text("Selected Region");
        selectedRegionKeyText.setFill(Color.WHITE);
        HBox selectedRegionKeyBox = new HBox(selectedRegionKey, selectedRegionKeyText);
        selectedRegionKeyBox.setAlignment(Pos.CENTER_LEFT);
        selectedRegionKeyBox.setSpacing(5.0);

        VBox mapKey = new VBox(foundRegionKeyBox, undiscoveredRegionKeyBox,
                currentRegionKeyBox, selectedRegionKeyBox);
        mapKey.setSpacing(5.0);

        Button travelButton = new Button("Travel");

        travelButton.setOnAction(e -> {
            if (selectedRegion != UserData.getCurrentRegion()) {

                /*
                Serving the player a random encounter (trader, bandit, police, or none)
                 */
                Encounter encounterHandler = new Encounter(primaryStage, selectedRegion);
                Scene randomEncounter = encounterHandler.get();

                if (randomEncounter != null) {
                    primaryStage.setScene(randomEncounter);
                    //setScene(new Encounter(primaryStage, selectedRegion).get());
                } else {
                    System.out.println("Randomly chose to not serve an encounter");

                    // Travel without experiencing an encounter
                    UserData.getCurrentRegion().clearPrices();
                    UserData.setCurrentRegion(selectedRegion);
                    selectedRegion.setPrices();
                    selectedRegion.setDiscovered(true);
                    primaryStage.setScene(new UniverseDisplay(primaryStage, universe).get());
                }
            }
        });

        Button backButton = new Button("Return");
        backButton.setOnAction(e -> goBackOneScene());

        HBox informationBoxes = new HBox(mapKey, infoStack, travelButton, backButton);
        informationBoxes.setAlignment(Pos.CENTER_LEFT);
        informationBoxes.setSpacing(30);

        VBox vbox = new VBox(map, informationBoxes);
        vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Region playerRegion = UserData.getCurrentRegion();
        Point currentLocation = playerRegion.getLocation();

        regionText.setText("Region: " + playerRegion.getRegionName()
                + "\n" + "Description: " + playerRegion.getDescription()
                + "\n" + "Technology level: " + playerRegion.getTechLevel()
                + "\n" + "Coordinates: X:" + playerRegion.getLocation().x
                + ", Y: " + playerRegion.getLocation().y
                + "\n" + "Distance away: 0.0");

        for (Region region: universe.getRegions()) {
            Circle galaxyVisualization = new Circle(10);

            if (region.isDiscovered() && UserData.getCurrentRegion() == region) {
                galaxyVisualization.setFill(Color.WHITE);
            } else if (region.isDiscovered()) {
                galaxyVisualization.setFill(Color.BLUE);
            } else {
                galaxyVisualization.setFill(Color.RED);
            }

            galaxyVisualization.setOnMouseClicked(e -> {
                if (selectedRegion != null && selectedRegion != region
                    && region != UserData.getCurrentRegion()) {
                    if (selectedRegion.isDiscovered()) {
                        selectedCircle.setFill(Color.BLUE);
                    } else {
                        selectedCircle.setFill(Color.RED);
                    }
                }
                if (UserData.getCurrentRegion() != region) {
                    selectedRegion = region;
                    selectedCircle = galaxyVisualization;
                }
            });

            galaxyVisualization.setOnMouseEntered(e -> {
                if (region.isDiscovered()) {
                    regionText.setText("Region: " + region.getRegionName()
                            + "\n" + "Description: " + region.getDescription()
                            + "\n" + "Technology level: " + region.getTechLevel()
                            + "\n" + "Coordinates: X:" + region.getLocation().x
                            + ", Y: " + region.getLocation().y
                            + "\n" + "Distance away: "
                            + (Point.distance(currentLocation.x, currentLocation.y,
                                region.getLocation().x, region.getLocation().y)));
                } else {
                    regionText.setText("?????"
                            + "\n" + "Coordinates: X:" + region.getLocation().x
                            + ", Y: " + region.getLocation().y
                            + "\n" + "Distance away: "
                            + (Point.distance(currentLocation.x, currentLocation.y,
                            region.getLocation().x, region.getLocation().y)));
                    System.out.println("What will appear when this region is discovered: "
                        + "Region: " + region.getRegionName() + "\n" + "Description: "
                        + region.getDescription() + "\n" + "Tech level: "
                        + region.getTechLevel() + "\n"
                        + "Distance away: "
                        + (Point.distance(currentLocation.x, currentLocation.y,
                        region.getLocation().x, region.getLocation().y)));
                }
                if (region != UserData.getCurrentRegion()) {
                    galaxyVisualization.setFill(Color.YELLOW);
                }
            });

            galaxyVisualization.setOnMouseExited(e -> {
                if (region != UserData.getCurrentRegion()) {
                    if (region == selectedRegion) {
                        galaxyVisualization.setFill(Color.GREEN);
                    } else if (region.isDiscovered()) {
                        galaxyVisualization.setFill(Color.BLUE);
                    } else {
                        galaxyVisualization.setFill(Color.RED);
                    }
                }
                if (selectedRegion == null) {
                    Region curRegion = UserData.getCurrentRegion();
                    regionText.setText("Region: " + curRegion.getRegionName()
                            + "\n" + "Description: " + curRegion.getDescription()
                            + "\n" + "Technology level: " + curRegion.getTechLevel()
                            + "\n" + "Coordinates: X:" + curRegion.getLocation().x
                            + ", Y: " + curRegion.getLocation().y
                            + "\n" + "Distance away: 0.0");
                } else if (selectedRegion.isDiscovered()) {
                    regionText.setText("Region: " + selectedRegion.getRegionName()
                            + "\n" + "Description: "
                        + selectedRegion.getDescription() + "\n" + "Technology level: "
                        + selectedRegion.getTechLevel() + "\n" + "Coordinates: X:"
                            + selectedRegion.getLocation().x
                        + ", Y:" + selectedRegion.getLocation().y + "\n" + "Distance away: "
                        + Point.distance(currentLocation.x, currentLocation.y,
                            region.getLocation().x, region.getLocation().y));
                } else {
                    regionText.setText("?????"
                            + "\n" + "Coordinates: X:" + region.getLocation().x
                            + ", Y: " + region.getLocation().y
                            + "\n" + "Distance away: "
                            + (Point.distance(currentLocation.x, currentLocation.y,
                            region.getLocation().x, region.getLocation().y)));
                }
            });

            map.add(galaxyVisualization, region.getLocation().x,
                    region.getLocation().y);
        }
        map.setBackground(getBackgroundWithImage("Images/universeDisplayGrid.png"));

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M) {
                setScene(new scenes.Menu(primaryStage).get());
            }
        });

        return scene;
    }
}
