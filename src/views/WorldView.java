package views;

import java.util.ArrayList;

import application.Map;
import application.Team;
import application.Weapon;
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

public class WorldView {
	MapView map;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	ArrayList<TeamView> team;
	TeamView currentTeam;
	int currentWormIndex;
	
	ScaleTransition weaponChooserTransition1;
	TranslateTransition weaponChooserTransition2;
	ParallelTransition weaponChooserTransition;

	public WorldView(Map m, ArrayList<Team> teams) {
		map = new MapView(m);
		weaponChooser = new TilePane(4, 4);
		initiateWeaponChooser();
		currentWormIndex = 0;
		
		team = new ArrayList<TeamView>(teams.size());
		for (Team t : teams) {
			team.add(new TeamView(t, m));
		}
		currentTeam = team.get(0);
		currentWorm = currentTeam.getMembers().get(currentWormIndex);
		currentWorm.getWorm().setCurrentWorm(true);
		
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(weaponChooser);
		for (TeamView t : team) {
			for (WormView w : t.getMembers()) {
				world.getChildren().add(w.getWormGroup());
			}
		}
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
		currentWorm.getWorm().setCurrentWorm(false);
		boolean cont = true;
		while (cont && currentWormIndex < currentTeam.getMembers().size() - 1) {
			currentWormIndex++;
			currentWorm = currentTeam.getMembers().get(currentWormIndex);
			// Stops the loops if the new worm is valid
			if (currentWorm.getWorm().lifeProperty().get() > 0) {
				cont = false;
			}
		}
		// Starts over from the begining if the end of the array was reached with no valid worm found
		if (cont) {
			currentWormIndex = -1; // The last element of the array is also the -1th
		}
		while (cont && currentWormIndex < currentTeam.getMembers().size() - 1) {
			currentWormIndex++;
			currentWorm = currentTeam.getMembers().get(currentWormIndex);
			if (currentWorm.getWorm().lifeProperty().get() > 0) {
				cont = false;
			}
		}
		currentWorm.getWorm().setCurrentWorm(true);
	}

/*	private void damages(int dam, int rad) {
		for (int i = 0; i < allWorms.length; i++) {
			if (Math.pow(allWorms[i].getWorm().xPosProperty().get() - currentWorm.getWorm().xFireProperty().get(), 2) + Math.pow(allWorms[i].getWorm().yPosProperty().get() - currentWorm.getWorm().yFireProperty().get(), 2) < Math.pow(rad, 2)) {
				allWorms[i].getWorm().lifeProperty().subtract(dam);
			}
			if (allWorms[i].getWorm().lifeProperty().get()<=0){
				removeWorm(i);
			}
		
		}
	}*/

/*	public void addWorm(Worm w, Map m) {
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
	}*/
	
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
