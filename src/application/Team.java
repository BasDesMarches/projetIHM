package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Team {
	String name;
	Color color;
	ArrayList<Worm> members;
	
	public Team(String name, Color color) {
		super();
		this.name = name;
		this.color = color;
		members = new ArrayList<Worm>();
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Worm> getMembers() {
		return members;
	}
}
