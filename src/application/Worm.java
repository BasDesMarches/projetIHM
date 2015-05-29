package application;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Worm {
	String name;
	Map map;
//	SimpleIntegerProperty life;
	Weapon weapon;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	SimpleBooleanProperty onRight;
//	int paceCounter;
	//new Team team;
	
	public Worm(String name, Map m, int x, int y, Weapon w){
		this.name = name;
		map = m;
		xPos = new SimpleIntegerProperty(x);
		yPos = new SimpleIntegerProperty(y);
		onRight = new SimpleBooleanProperty(true);
		weapon = w;
//		life=new SimpleIntegerProperty(100);
	}
	
	private void elementalMove(){
		
		//move right or left
		if (onRight.get() && xPos.get() < 155){
			xPos.set(xPos.get() + 1);
		} else if (!onRight.get() && xPos.get() > 0) {
			xPos.set(xPos.get() - 1);
		}
		//adjust the height
		while(map.getMap()[yPos.get() + 4][xPos.get() + 2]==('1'))
		{
			yPos.set(yPos.get() - 1);
		}
		while(map.getMap()[yPos.get() + 5][xPos.get() + 2]==('0')){
			yPos.set(yPos.get() + 1);
		}
//		paceCounter--;
	}
	
	public void moveRight() {
		onRight.set(true);
		elementalMove();
	}
	
	public void moveLeft() {
		onRight.set(false);
		elementalMove();
	}
	
	public void fire(double angle, double initSpeed) {
		boolean hasHit = false;
		char[][] grid = map.getMap();
		double x = 0;
		double y = 0;
		double g = 0.1;
		double xInit = xPos.get()*5;
		double yInit = yPos.get()*5;
		double hInitSpeed = initSpeed*Math.cos(angle);
		double vInitSpeed = initSpeed*Math.sin(angle);
		int i = 0;
		while (!hasHit) {
			x = xInit + hInitSpeed*i;
			y = yInit + vInitSpeed*i + g*i*i;
			i++;
			if ((inBounds((int)(y/5), (int)(x/5)) && grid[(int)(y/5)][(int)(x/5)] == '1') || y/5 > map.getYSize() + 20) {
				hasHit = true;
			}
		}
		map.destroy((int)(y/5), (int)(x/5), weapon.getDamage());
	}
	
	private boolean inBounds(int y, int x) {
		return (0 <= y && y < map.getYSize() && 0 <= x && x < map.getXSize());
	}
	
	//========== Getters and setters ==========
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
