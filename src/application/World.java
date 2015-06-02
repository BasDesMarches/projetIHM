package application;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class World {
	MapView map;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	ScaleTransition weaponChooserTransition;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		weaponChooser = new TilePane(4,4);
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
		weaponChooser.setStyle("-fx-background-color : #222");
//		weaponChooser.setVisible(false);
		weaponChooser.scaleXProperty().set(0);
		weaponChooser.scaleYProperty().set(0);
		weaponChooserTransition = new ScaleTransition(new Duration(500), weaponChooser);
		weaponChooserTransition.setInterpolator(Interpolator.EASE_IN);
	}
	
	public void showWeaponChooser() {
		weaponChooserTransition.setToX(1);
		weaponChooserTransition.setToY(1);
		weaponChooserTransition.play();
		currentWorm.getWorm().setIsChoosingWeapon(true);
	}
	
	public void hideWeaponChooser() {
		weaponChooserTransition.setToX(0);
		weaponChooserTransition.setToY(0);
		weaponChooserTransition.play();
		currentWorm.getWorm().setIsChoosingWeapon(false);
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
}
