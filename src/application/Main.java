package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			AnchorPane world = new AnchorPane();
			
			Map map = new Map("hill1.map");
			MapView mapView = new MapView(map);
			Worm w = new Worm("Coucou", 80, 60);
			WormView wv = new WormView(w);
			ImageView worm = wv.getPic();
			AnchorPane.setTopAnchor(worm, wv.yProperty().doubleValue()*5);
			AnchorPane.setLeftAnchor(worm, wv.xProperty().doubleValue()*5);
			world.getChildren().add(mapView.getMap());
			world.getChildren().add(worm);
			root.setCenter(world);
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
}
