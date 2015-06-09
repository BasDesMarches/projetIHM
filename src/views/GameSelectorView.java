package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import application.Map;
import application.Team;
import application.ToggleSet;
import application.Weapon;
import application.Worm;

public class GameSelectorView {
	Map map;
	ArrayList<Team> teams;
	ToggleGroup tg;
	VBox selectorDisplay;
	VBox mapSelector;
	HBox numberOfTeamsSelector;
	HBox numberOfWormsSelector;
	HBox teamSelector;
	HBox playModeSelector;
	
	public GameSelectorView() {
		teams = new ArrayList<Team>();
		tg = new ToggleGroup();
		mapSelector = new VBox(10, new Label("Choose the map :"), loadThumbnails());
		numberOfTeamsSelector = new HBox(10, new Label("Number of teams : "), newNumberChoiceBox(2, 4));
		numberOfWormsSelector = new HBox(10, new Label("Number of worms in a team : "), newNumberChoiceBox(1, 10));
		selectorDisplay = new VBox(10);
		selectorDisplay.getChildren().addAll(mapSelector, numberOfTeamsSelector, numberOfWormsSelector);
		selectorDisplay.setPadding(new Insets(10));
		selectorDisplay.setId("selector");
	}
	
	private HBox loadThumbnails() {
		HBox thumbnails = new HBox(15);
		ToggleSet ts = new ToggleSet("hill1", tg);
		ts.setSelected(true);
		thumbnails.getChildren().add(ts.getSet());
		ts = new ToggleSet("test", tg);
		thumbnails.getChildren().add(ts.getSet());
		return thumbnails;
	}
	
	private ChoiceBox<Integer> newNumberChoiceBox(int start, int end) {
		ChoiceBox<Integer> box = new ChoiceBox<Integer>();
		for (int i = start; i <= end; i++) {
			box.getItems().add(i);
		}
		box.setValue(start);
		return box;
	}

	@SuppressWarnings("unchecked")
	public void generate() {
		map = new Map(tg.getSelectedToggle().getUserData() + ".map");
		for (int i = 0; i < ((ChoiceBox<Integer>) numberOfTeamsSelector.getChildren().get(1)).getValue(); i++) {
			teams.add(new Team("Team" + i, Color.AQUA));
		}
		for (int j = 0; j < teams.size(); j++) {
			Team t = teams.get(j);
			for (int k = 0; k < ((ChoiceBox<Integer>) numberOfWormsSelector.getChildren().get(1)).getValue(); k++) {
				t.addMember(new Worm("name", map, j*40 + k*3, 20, Weapon.GUN));
			}
		}
	}
	
	// ========== Getters and setters ==========
	public VBox getSelector() {
		return selectorDisplay;
	}
	
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}
}
