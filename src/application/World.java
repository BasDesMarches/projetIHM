package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class World {
	MapView map;
	WormView currentWorm;
	Group world;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		worm.layoutXProperty().bind(currentWorm.xProperty().multiply(5));
		worm.layoutYProperty().bind(currentWorm.yProperty().multiply(5));
		
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(worm);
	}
	
	public Group getWorld() {
		return world;
	}

	public MapView getMap() {
		return map;
	}

	public WormView getCurrentWorm() {
		return currentWorm;
	}
}
