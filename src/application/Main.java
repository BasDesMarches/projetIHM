package application;

import event_handler.KeyPressedEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			
			Map map = new Map("hill1.map");
			Worm w = new Worm("Coucou", 80, 10);
			World world = new World(map, w);
			
			setTheEventFilters(scene, map, w);
			root.setCenter(world.getWorld());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void setTheEventFilters(Scene s, Map map, Worm w) {
		if (s == null) {
			System.out.println("The BorderPane must be initialized for the EventFilter to be set.");
			return;
		}
		s.addEventFilter(KeyEvent.KEY_PRESSED, new KeyPressedEvent(w, map));
	}
}
