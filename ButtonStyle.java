package styles;

/**
 * enum representing style of buttons
 * @author Joseph Lukemire
 * @version 1.0
 */
public enum ButtonStyle {
    menu("-fx-background-color:black; -fx-border-color:green"),
    menuHighlighted("-fx-background-color:green; -fx-border-color:green");

    private String style;

    /**
     * assigns the style of the button
     * @param style the style of the button
     */
    ButtonStyle(String style) {
        this.style = style;
    }

    /**
     * gets the style of the button
     * @return the style of the button
     */
    public String get() {
        return this.style;
    }

}
