package View;

import Controller.*;
import Model.MapSettings.MapSettings;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MapView extends View {
    private AIController controller;
    private boolean created = false;
    private Stage stage;
    private GridPane root;
    private boolean done = false;

    public MapView(Stage stage) {
        this.stage = stage;
    }

    public void draw(int[][] map) {
        if (!created) {
            stage.setTitle("Awesome");
            this.root = new GridPane();
            stage.show();
        }
        for (int x = map.length-1; x >= 0; x--) {
            for (int y = map[x].length-1; y >= 0; y--) {
                String colour = "";
                if (map[x][y] == MapSettings.PASSABLE) {
                    colour = MapSettings.PASSABLE_COLOUR;
                } else if (map[x][y] == MapSettings.BLOCK) {
                    colour = MapSettings.BLOCK_COLOUR;
                } else if (map[x][y] == MapSettings.START) {
                    colour = MapSettings.START_COLOUR;
                } else if (map[x][y] == MapSettings.END) {
                    colour = MapSettings.END_COLOUR;
                } else if (map[x][y] == MapSettings.PATH) {
                    colour = MapSettings.PATH_COLOUR;
                }
                Region rect = new Region();
                rect.setOnMouseClicked(event -> {
                    notifyController("click", event.getSceneX(), event.getSceneY());
                });
                rect.setStyle("-fx-background-color: " + colour + "; -fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black; -fx-min-width: 50; -fx-min-height:50; -fx-max-width:50; -fx-max-height: 50;");
                root.add(rect, y * 50, x * 50);
            }
        }
        stage.setScene(new Scene(root, map[0].length * 50, map.length * 50));
        stage.show();
    }

    public void notifyController(String type, double xLocation, double yLocation) {
        controller.notify(type, xLocation, yLocation);
    }

    @Override
    public void setController(Object controller) {
        this.controller = (AIController) controller;
    }
}
