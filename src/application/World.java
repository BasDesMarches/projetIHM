package application;

import event_handler.KeyPressedEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class World {
	MapView map;
	WormView currentWorm;
	AnchorPane world;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		AnchorPane.setTopAnchor(worm, currentWorm.yProperty().multiply(5).doubleValue());
		AnchorPane.setLeftAnchor(worm, currentWorm.xProperty().multiply(5).doubleValue());
		
		world = new AnchorPane();
		setTheEventFilters();
		world.getChildren().add(map.getView());
		world.getChildren().add(worm);
	}
	
	public AnchorPane getWorld() {
		return world;
	}

	private void setTheEventFilters() {
		if (world == null) {
			System.out.println("\'world\' must be initialized for the EventFilter to be set.");
			return;
		}
		world.addEventFilter(KeyEvent.KEY_PRESSED, new KeyPressedEvent(currentWorm.getWorm(), map.getMap()));
//		world.addEventFilter(MouseEvent.MOUSE_CLICKED, new KeyPressedEvent(currentWorm, map.getMap()));
	}
}
