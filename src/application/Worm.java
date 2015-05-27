package application;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Worm {
	String name;
//	SimpleIntegerProperty life;
	Weapon weapon;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	SimpleBooleanProperty onRight;
//	int paceCounter;
	//new Team team;
	
	public Worm(String name, int x, int y){
		this.name = name;
		xPos = new SimpleIntegerProperty(x);
		yPos = new SimpleIntegerProperty(y);
		onRight = new SimpleBooleanProperty(true);
//		life=new SimpleIntegerProperty(100);
	}
	
	private void elementalMove(char[][] getMap){
		
		//move right or left
		if (onRight.get() && xPos.get() < 155){
			xPos.set(xPos.get() + 1);
		} else if (!onRight.get() && xPos.get() > 0) {
			xPos.set(xPos.get() - 1);
		}
		//adjust the height
		while(getMap[yPos.get() + 5][xPos.get() + 2]==('1'))
		{
			yPos.set(yPos.get() - 1);
		}
		while(getMap[yPos.get() + 6][xPos.get() + 2]==('0')){
			yPos.set(yPos.get() + 1);
		}
//		paceCounter--;
	}
	
	public void moveRight(Map m) {
		onRight.set(true);
		elementalMove(m.getMap());
	}
	
	public void moveLeft(Map m) {
		onRight.set(false);
		elementalMove(m.getMap());
	}
	
	public SimpleIntegerProperty xPosProperty() {
		return xPos;
	}
	
	public SimpleIntegerProperty yPosProperty() {
		return yPos;
	}
	
	public String getName() {
		return name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public SimpleBooleanProperty isOnRight() {
		return onRight;
	}
}
