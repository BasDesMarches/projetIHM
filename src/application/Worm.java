package application;

import javafx.beans.property.SimpleIntegerProperty;

public class Worm {
	private String name;
	private SimpleIntegerProperty life;
	private Weapon[] weapons;
	private SimpleIntegerProperty xPos;
	private SimpleIntegerProperty yPos;
	private int paceCounter;
	//new Team team;
	
	public Worm(String name, int x, int y){
		this.name=name;
		xPos=new SimpleIntegerProperty(x);
		yPos=new SimpleIntegerProperty(y);
		life=new SimpleIntegerProperty(100);
	}
	
	private void elementalMove(boolean isRight, char[][]getMap){
		
		//move right or left
		if (isRight){
			xPos.add(1);
		}else{
			xPos.add(-1);
		}
		//adjust the height
		if (getMap[xPos.get()][yPos.get()]==('0')){
			while(getMap[xPos.get()][yPos.get()-1]==('0'))
			{
				yPos.add(-1);
			}
		}else{
			while(getMap[xPos.get()][yPos.get()-1]==('1')){
				yPos.add(1);
			}
		}
		paceCounter--;
	}
	
}
