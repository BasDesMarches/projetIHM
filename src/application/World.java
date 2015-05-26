package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class World {
	MapView map;
	WormView currentWorm;
	AnchorPane world;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		AnchorPane.setTopAnchor(worm, currentWorm.yProperty().doubleValue()*5);
		AnchorPane.setLeftAnchor(worm, currentWorm.xProperty().doubleValue()*5);
		
		world = new AnchorPane();
		world.getChildren().add(map.getMap());
		world.getChildren().add(worm);
	}
	
	public AnchorPane getWorld() {
		return world;
	}
}
