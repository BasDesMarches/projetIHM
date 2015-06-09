package views;

import java.util.ArrayList;

import application.Map;
import application.Team;
import application.TurnManager;
import application.Weapon;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class WorldView {
	MapView map;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	ArrayList<TeamView> team;
	TeamView currentTeam;
	int currentWormIndex;
	int currentTeamIndex;
	Text timer;
	TurnManager turnManager;
	int numberOfRemainingTeams;
	SimpleBooleanProperty gameFinished;
	ScaleTransition weaponChooserTransition1;
	TranslateTransition weaponChooserTransition2;
	ParallelTransition weaponChooserTransition;

// ========== Construction of the instance ==========
	public WorldView(Map m, ArrayList<Team> teams) {
		map = new MapView(m);							// Initializations
		weaponChooser = new TilePane(4, 4);
		initiateWeaponChooser();
		currentWormIndex = 0;
		currentTeamIndex = 0;
		numberOfRemainingTeams = teams.size();
		gameFinished = new SimpleBooleanProperty(false);
		timer = new Text(740,50,"");					// The timer and turn manager
		timer.setId("timer");
		Light.Distant light = new Light.Distant(-120.0, 60, Color.YELLOW);
		timer.setEffect(new Lighting(light));
//		timer.setVisible(false);
		turnManager = new TurnManager(30, this);
		team = new ArrayList<TeamView>(teams.size());	// Teams and worms
		for (Team t : teams) {
			team.add(new TeamView(t, m));
		}
		currentTeam = team.get(currentTeamIndex);
		currentWorm = currentTeam.getMembers().get(currentWormIndex);
		currentWorm.getWorm().setCurrentWorm(true);
		world = new Group();							// The returned Group
		world.getChildren().add(map.getView());
		world.getChildren().add(weaponChooser);
		for (TeamView t : team) {
			for (WormView w : t.getMembers()) {
				world.getChildren().add(w.getWormGroup());
			}
		}
		world.getChildren().add(timer);	
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

// ========== Functions ==========
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
	
	public void changeTeam() {
		boolean cont = true;
		while (cont && currentTeamIndex < team.size() - 1) {
			currentTeamIndex++;
			currentTeam = team.get(currentTeamIndex);
			// Stops the loops if the new team is valid
			if (currentTeam.getTeam().numberOfAliveMembers() > 0) {
				cont = false;
			}
		}
		// Starts over from the begining if the end of the array was reached with no valid worm found
		if (cont) {
			currentTeamIndex = -1; // The last element of the array is also the -1th
		}
		while (cont && currentTeamIndex < team.size() - 1) {
			currentTeamIndex++;
			currentTeam = team.get(currentTeamIndex);
			if (currentTeam.getTeam().numberOfAliveMembers() > 0) {
				cont = false;
			}
		}
		nextWorm();
	}
	
	public void checkForVictory() {
		Team t = null;
		for (TeamView tv : team) {
			if (tv.getTeam().numberOfAliveMembers() > 0) {
				t = tv.getTeam();
			} else if (tv.getTeam().getRank() == 0) {
				tv.getTeam().setRank(numberOfRemainingTeams);
				numberOfRemainingTeams--;
			}
		}
		if (numberOfRemainingTeams <= 1) {
			world.getChildren().clear();
			isGameFinished().set(true);
			Text text = new Text();
			if (t == null) {
				text.setText("It's a draw!");
			} else {
				text.setText("The " + t.getName() + " team wins!");
			}
			text.setFont(Font.font(40));
			text.setTextAlignment(TextAlignment.CENTER);
			text.setWrappingWidth(800);
			text.setLayoutX(200);
			text.setLayoutY(250);
			world.getChildren().add(text);
		}
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
	
	public Text getTimer() {
		return timer;
	}
		
	public SimpleBooleanProperty isGameFinished() {
		return gameFinished;
	}
	
	public void setGameFinished(boolean b) {
		gameFinished.set(b);
	}
	

	public ArrayList<TeamView> getTeamView(){
		return team;
	}

	public int getCurrentWormIndex() {
		return currentWormIndex;
	}

	public void setCurrentWormIndex(int currentWormIndex) {
		this.currentWormIndex = currentWormIndex;
	}

	public int getCurrentTeamIndex() {
		return currentTeamIndex;
	}

	public void setCurrentTeamIndex(int currentTeamIndex) {
		this.currentTeamIndex = currentTeamIndex;
	}

}
