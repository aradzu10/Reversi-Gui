package Game;


import ReversiGui.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ex6  extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		MenuController.StartMenu(primaryStage);
	}
}

