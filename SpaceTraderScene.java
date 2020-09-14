package scenes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Stack;

/**
* abstract class for all the stages.
* @author Joseph Lukemire
* @version 1.0
*/
public abstract class SpaceTraderScene {
    protected Stage primaryStage;
    protected static Stack<Scene> sceneStack = new Stack<>();

    /**
    * constructor for all the scenes.
    * @param primaryStage the stage
    */
    public SpaceTraderScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Abstract getter method for Scene which must be implemented in all subclasses
     * @return Scene the scene returned by an extension of this class
     */
    public abstract Scene get();

    /**
     * Adds the current scene to sceneStack and changes primaryStage's scene to the parameter scene
     * @param scene the scene to change to
     */

    public void setScene(Scene scene) {
        if (primaryStage.getScene() != null) {
            Scene prev = primaryStage.getScene();
            sceneStack.push(prev);
            System.out.println("Pushed " + prev.toString());
        }
        primaryStage.setScene(scene);
        scene.getWindow().centerOnScreen();
    }

    /**
     * Reverts to the scene most recently added to sceneStack
     */
    public void goBackOneScene() {
        if (sceneStack.empty()) {
            System.out.println("Scene stack is empty");
        } else {
            System.out.println("Inside of pop method");
            primaryStage.setScene(sceneStack.pop());
        }
    }

    /**
     * Helper method which creates and returns a new Background with the image referenced
     * by the path parameter
     * @param filepath path to the image to use as a background
     * @return Background
     */
    public static Background getBackgroundWithImage(String filepath) {
        Image background = new Image(filepath);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

    /**
     * returns a background with a specified color
     * @param fill the color of the background
     * @return the background
     */
    public static Background getBackgroundWithFill(Color fill) {
        return new Background(new BackgroundFill(fill, null, null));
    }
}
