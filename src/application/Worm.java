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
	
	public void elementalMove(boolean isRight, char[][] getMap){
		
		//move right or left
		if (isRight){
			xPos.add(1);
		}else{
			xPos.add(-1);
		}
		//adjust the hight
		if (getMap[yPos.get()][xPos.get()]==('0')){
			while(getMap[yPos.get()-1][xPos.get()]==('0'))
			{
				yPos.add(-1);
			}
		}else{
			while(getMap[yPos.get()-1][xPos.get()]==('1')){
				yPos.add(1);
			}
		}
//		paceCounter--;
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
