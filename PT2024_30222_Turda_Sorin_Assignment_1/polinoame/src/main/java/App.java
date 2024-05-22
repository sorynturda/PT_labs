import calculator.logic.Controller;
import calculator.GUI.Model;
import calculator.GUI.View;
import calculator.logic.Operation;
import calculator.model.Polynomial;

public class App {
    public static void main(String[] args) {
       Model model = new Model();
       View view = new View(model);
       Controller controller = new Controller(model, view);
       view.setVisible(true);
    }
}