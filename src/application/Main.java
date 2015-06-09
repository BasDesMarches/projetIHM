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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// ===== Main elements
			BorderPane arena = new BorderPane();
			Scene arenaScene = new Scene(arena,800,600);
			BorderPane menu = new BorderPane();
			Scene menuScene = new Scene(menu, 800, 600);
			GameSelectorView gsv = new GameSelectorView();
			
			// ===== Elements of the side menu
			Label menuLabel = new Label("Menu");
				// Launch the creation of the world, then launch the game
			Button start = new Button("START GAME");
			start.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					gsv.generate();
					WorldView world = new WorldView(gsv.getMap(), gsv.getTeams());
					arena.setCenter(world.getWorld());
					setTheEvent(arenaScene, gsv.getMap(), world, primaryStage, menuScene);
					arenaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(arenaScene);
				}
			});
				// Quit the game
			Button quit = new Button("QUIT");
			quit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.exit(0);
				}
			});
			
			// ===== Setting the side menu
			setSideMenuLayout(menuLabel, start, quit);
			AnchorPane sideBar = new AnchorPane();
			sideBar.setPrefSize(150, 2000);
			sideBar.setId("sideBar");
			sideBar.getChildren().addAll(menuLabel, start, quit);
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
	
	private void setSideMenuLayout(Label menuLabel, Button start, Button quit) {
		menuLabel.setId("menuLabel");
		AnchorPane.setTopAnchor(menuLabel, 10.0);
		AnchorPane.setLeftAnchor(menuLabel, 10.0);
		AnchorPane.setRightAnchor(menuLabel, 5.0);
		AnchorPane.setBottomAnchor(quit, 10.0);
		AnchorPane.setLeftAnchor(quit, 10.0);
		AnchorPane.setRightAnchor(quit, 5.0);
		quit.setPrefHeight(30);
		AnchorPane.setBottomAnchor(start, 50.0);
		AnchorPane.setLeftAnchor(start, 10.0);
		AnchorPane.setRightAnchor(start, 5.0);
		start.setPrefHeight(30);
	}
}
