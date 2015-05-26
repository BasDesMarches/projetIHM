package application;

import javafx.beans.property.SimpleIntegerProperty;

public class Worm {
	String name;
//	SimpleIntegerProperty life;
//	Weapon[] weapons;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
//	int paceCounter;
	//new Team team;
	
	public Worm(String name, int x, int y){
		this.name=name;
		xPos = new SimpleIntegerProperty(x);
		yPos = new SimpleIntegerProperty(y);
//		life=new SimpleIntegerProperty(100);
	}
	
	private void elementalMove(boolean isRight, char[][] getMap){
		
		//move right or left
		if (isRight){
			xPos.set(xPos.get() + 1);
		}else{
			xPos.set(xPos.get() - 1);
		}
		//adjust the height
		while(getMap[yPos.get() + 4][xPos.get() + 2]==('1'))
		{
			yPos.set(yPos.get() - 1);
		}
		while(getMap[yPos.get() + 5][xPos.get() + 2]==('0')){
			yPos.set(yPos.get() + 1);
		}
//		paceCounter--;
	}
	
	public void moveRight(Map m) {
		elementalMove(true, m.getMap());
	}
	
	public void moveLeft(Map m) {
		elementalMove(false, m.getMap());
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
}
