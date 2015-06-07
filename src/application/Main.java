package application;

import views.GameSelectorView;
import views.WorldView;
import event_handler.KeyPressedEvent;
import event_handler.MouseReleasedEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane arena = new BorderPane();
			Scene arenaScene = new Scene(arena,800,600);
			BorderPane menu = new BorderPane();
			Scene menuScene = new Scene(menu, 800, 600);
			GameSelectorView gsv = new GameSelectorView();
			
			VBox sideBar = new VBox();
			sideBar.getChildren().add(new Label("Menu"));
			Button b = new Button("START");
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					gsv.generate();
					WorldView world = new WorldView(gsv.getMap(), gsv.getTeams());
					arena.setCenter(world.getWorld());
					setTheEventFilters(arenaScene, gsv.getMap(), world);
					arenaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(arenaScene);
				}
			});;
			sideBar.getChildren().add(b);
			menu.setCenter(gsv.getSelector());
			menu.setLeft(sideBar);
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(menuScene);
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
