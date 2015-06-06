package views;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import application.Map;
import application.Team;
import application.Worm;

public class TeamView {
	Team team;
	Color color;
	ArrayList<WormView> members;
	
	public TeamView(Team team, Color color, Map map) {
		this.team = team;
		this.color = color;
		members = new ArrayList<WormView>(team.getMembers().size());
		for (Worm worm : team.getMembers()) {
			members.add(new WormView(worm, map));
		}
	}

	// ========== Getters and setters ==========
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<WormView> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<WormView> members) {
		this.members = members;
	}
}
