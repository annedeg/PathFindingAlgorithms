package Controller;

public abstract class AIController extends Controller {
    abstract public Node getShortestPath();
    abstract public void notify(String type, double xLocation, double yLocation);
}
