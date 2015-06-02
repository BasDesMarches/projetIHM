package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class World {
	MapView map;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		weaponChooser = new TilePane();
		initiateWeaponChooser();
		worm.layoutXProperty().bind(currentWorm.xProperty().multiply(5));
		worm.layoutYProperty().bind(currentWorm.yProperty().multiply(5));
		
		
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(worm);
		world.getChildren().add(weaponChooser);
		world.getChildren().add(currentWorm.lifeBg);
		world.getChildren().add(currentWorm.lifeValue);
	}
	
	private void initiateWeaponChooser(){
		for (Weapon weapon : Weapon.values()) {
			weaponChooser.getChildren().add(new ImageView(weapon.getImage()));
		}
		weaponChooser.setMaxWidth(64);
		weaponChooser.setStyle("-fx-background-color : #000000");
		weaponChooser.setVisible(false);
	}
	
	// ========== Getters and setters ==========
	public Group getWorld() {
		return world;
	}

	public MapView getMap() {
		return map;
	}

	public WormView getCurrentWorm() {
		return currentWorm;
	}
	
	public void showWeaponChooser() {
		weaponChooser.setVisible(true);
		currentWorm.getWorm().setIsChoosingWeapon(true);
	}
	
	public void hideWeaponChooser() {
		weaponChooser.setVisible(false);
		currentWorm.getWorm().setIsChoosingWeapon(false);
	}
}
