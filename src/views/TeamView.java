package views;

import java.util.ArrayList;

import application.Map;
import application.Team;
import application.Worm;

public class TeamView {
	Team team;
	ArrayList<WormView> members;
	
	public TeamView(Team team, Map map) {
		this.team = team;
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

	public ArrayList<WormView> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<WormView> members) {
		this.members = members;
	}
}
