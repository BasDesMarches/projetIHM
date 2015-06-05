package application;

import event_handler.KeyPressedEvent;
import event_handler.MouseReleasedEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			
			Map map = new Map("balistique.map");
			Worm w = new Worm("Worm1", map, 10, 10, Weapon.ROCKET);
			Worm w1 = new Worm("Worm1", map, 20, 10, Weapon.ROCKET);
			World world = new World(map, w);
			world.addWorm(w1, map);
			
			setTheEventFilters(scene, map,world.currentWorm.worm, world);
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

	private void setTheEventFilters(Scene s, Map map, Worm w, World world) {
		if (s == null) {
			System.out.println("The BorderPane must be initialized for the EventFilter to be set.");
			return;
		}
		s.setOnKeyPressed(new KeyPressedEvent(/*w,*/ world));
		s.setOnMouseReleased(new MouseReleasedEvent(w, world));
	}
}
