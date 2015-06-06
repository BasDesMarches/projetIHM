package application;

import java.util.ArrayList;

import views.WorldView;
import event_handler.KeyPressedEvent;
import event_handler.MouseReleasedEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			
			Map map = new Map("balistique.map");
			Team red = new Team("Red", Color.RED, 1);
			red.addMember(new Worm("Worm1", map, 5, 10, Weapon.ROCKET));
			red.addMember(new Worm("Worm2", map, 25, 10, Weapon.ROCKET));
			Team blue = new Team("Blue", Color.BLUE, 1);
			blue.addMember(new Worm("Worm3", map, 15, 10, Weapon.ROCKET));
			ArrayList<Team> teams = new ArrayList<Team>(2);
			teams.add(red);
			teams.add(blue);
			WorldView world = new WorldView(map, teams);
			
			setTheEventFilters(scene, map, world);
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

	private void setTheEventFilters(Scene s, Map map, WorldView world) {
		if (s == null) {
			System.out.println("The BorderPane must be initialized for the EventFilter to be set.");
			return;
		}
		s.setOnKeyPressed(new KeyPressedEvent(world));
		s.setOnMouseReleased(new MouseReleasedEvent(world));
	}
}
