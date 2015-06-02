package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class World {
	MapView map;
	WormView[] currentWorm;
	Group world;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm =new WormView[1];
		currentWorm[0] = new WormView(w, m);
		ImageView worm = currentWorm[0].getPic();
		worm.layoutXProperty().bind(currentWorm[0].xProperty().multiply(5));
		worm.layoutYProperty().bind(currentWorm[0].yProperty().multiply(5));
		
		
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(worm);
		world.getChildren().add(currentWorm[0].lifeBg);
		world.getChildren().add(currentWorm[0].lifeValue);
	}
	
	public Group getWorld() {
		return world;
	}

	public MapView getMap() {
		return map;
	}

	public WormView getCurrentWorm() {
		return currentWorm[0];
	}
}
