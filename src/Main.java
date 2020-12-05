import Controller.AIController;
import Controller.AStar;
import Model.MapModel;
import javafx.application.Application;
import javafx.stage.Stage;
import View.*;
public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        MapView view = new MapView(stage);
        MapModel model = new MapModel(view);
        AStar controller = new AStar(model, view);
        view.setController(controller);
        controller.start();
    }
}
