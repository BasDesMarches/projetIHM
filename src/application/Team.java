package application;

import java.util.ArrayList;

public class Team {
	String name;
	ArrayList<Worm> members;
	
	public Team(String name, int number) {
		super();
		this.name = name;
		members = new ArrayList<Worm>(number);
	}

	public void addMember(Worm member) {
		members.add(member);
	}
	
	public void removeMenmber(Worm member) {
		members.remove(member);
	}
	
	public int numberOfAliveMembers() {
		int i = 0;
		for(Worm w : members){
			if(w.lifeProperty().get() > 0) {
				i++;
			}
		}
		return i;
	}

	// ========== Getters end setters ==========
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Worm> getMembers() {
		return members;
	}
}
