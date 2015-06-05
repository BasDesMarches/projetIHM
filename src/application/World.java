package application;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class World {
	MapView map;
	WormView[] allWorms;
	int index = 0;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	
	ScaleTransition weaponChooserTransition1;
	TranslateTransition weaponChooserTransition2;
	ParallelTransition weaponChooserTransition;

	public World(Map m, Worm w) {
		map = new MapView(m);
		allWorms = new WormView[1];
		allWorms[0] = new WormView(w, m);
		currentWorm = allWorms[0];
		weaponChooser = new TilePane(4, 4);
		initiateWeaponChooser();
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(weaponChooser);
		world.getChildren().add(allWorms[0].wormGroup);
	}

	private void initiateWeaponChooser() {
		ImageView tempWeaponImage;
		for (Weapon weapon : Weapon.values()) {
			tempWeaponImage = new ImageView(weapon.getImage());
			tempWeaponImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					currentWorm.getWorm().setWeapon(weapon);
				}
			});
			weaponChooser.getChildren().add(tempWeaponImage);
		}
		weaponChooser.setStyle("-fx-background-color : #222");
		weaponChooser.setTranslateX(-(4*4 + 5*Weapon.GUN.getImage().getWidth()) / 2);
		weaponChooser.scaleXProperty().set(0);
		weaponChooser.scaleYProperty().set(0);
		int durationTime = 500;
		weaponChooserTransition1 = new ScaleTransition(new Duration(durationTime), weaponChooser);
		weaponChooserTransition1.setInterpolator(Interpolator.EASE_IN);
		weaponChooserTransition2 = new TranslateTransition(new Duration(durationTime), weaponChooser);
		weaponChooserTransition2.setInterpolator(Interpolator.EASE_IN);
		weaponChooserTransition = new ParallelTransition(weaponChooserTransition1, weaponChooserTransition2);
	}

	public void showWeaponChooser() {
		weaponChooserTransition1.setToX(1);
		weaponChooserTransition1.setToY(1);
		weaponChooserTransition2.setToX(0);
		weaponChooserTransition.play();
		currentWorm.getWorm().setIsChoosingWeapon(true);
	}

	public void hideWeaponChooser() {
		weaponChooserTransition1.setToX(0);
		weaponChooserTransition1.setToY(0);
		weaponChooserTransition2.setToX(- weaponChooser.getWidth() / 2);
		weaponChooserTransition.play();
		currentWorm.getWorm().setIsChoosingWeapon(false);
	}

	public void nextWorm() {
		if (index < allWorms.length - 1) {
			currentWorm = allWorms[index + 1];
			index++;
		} else {
			currentWorm = allWorms[0];
			index = 0;
		}
	}

	private void damages(int dam, int rad) {
		for (int i = 0; i < allWorms.length; i++) {
			if (Math.pow(allWorms[i].getWorm().xPos.get() - currentWorm.getWorm().xFire.get(), 2) + Math.pow(allWorms[i].getWorm().yPos.get() - currentWorm.getWorm().yFire.get(), 2) < Math.pow(rad, 2)) {
				allWorms[i].getWorm().life.subtract(dam);
			}
			if (allWorms[i].getWorm().life.get()<=0){
				removeWorm(i);
			}
		
		}
	}

	public void addWorm(Worm w, Map m) {
		WormView newWorm = new WormView(w, m);
		WormView[] a = new WormView[allWorms.length + 1];
		for (int i = 0; i < allWorms.length; i++) {
			a[i] = allWorms[i];
		}
		a[allWorms.length] = newWorm;
		allWorms = a;
		world.getChildren().add(allWorms[allWorms.length - 1].wormGroup);
	}

	private void removeWorm(int index) {
		WormView[] a = new WormView[allWorms.length - 1];
		for (int i = 0; i < index; i++) {
			a[i] = allWorms[i];
		}
		for (int i = index + 1; i < allWorms.length; i++) {
			a[i - 1] = allWorms[i];
		}
		allWorms = a;
	}
	
	private void turn(){
		
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
