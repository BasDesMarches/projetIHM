package application;

import views.GameSelectorView;
import views.WorldView;
import event_handler.KeyPressedEvent;
import event_handler.MouseReleasedEvent;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
					setTheEvent(arenaScene, gsv.getMap(), world, primaryStage, menuScene);
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

	private void setTheEvent(Scene arena, Map map, WorldView world, Stage stage, Scene menu) {
		world.isGameFinished().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (oldValue) {
					stage.setScene(menu);
				}
			}
		});
		arena.setOnKeyPressed(new KeyPressedEvent(world));
		arena.setOnMouseReleased(new MouseReleasedEvent(world));
	}
}
