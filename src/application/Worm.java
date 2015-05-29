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
		int i = yPos.get();
		int j = xPos.get();
		while(i >= 0 && map.getMap()[i + 4][j + 2]==('1'))
		{
			i--;
		}
		while((i + 5 < 120) && map.getMap()[i + 5][j + 2]==('0')){
			i++;
		}
		if (0 <= i && i + 5 < 120) {
			yPos.set(i);
		} else if (onRight.get()) {
			xPos.set(j - 1);
		} else {
			xPos.set(j + 1);
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
		double g = 0.01;
		double xInit = (xPos.get() + 3)*5;
		double yInit = (yPos.get() + 3)*5;
		double hInitSpeed = initSpeed*Math.cos(angle);
		double vInitSpeed = initSpeed*Math.sin(angle);
		switch (weapon) {
		case ROCKET:
			int i = 0;
			try {
				while (!hasHit) {
					x = xInit + hInitSpeed*i;
					y = yInit + vInitSpeed*i + g*i*i;
					i++;
					Thread.sleep(10);
					if ((inBounds((int)(y/5), (int)(x/5)) && grid[(int)(y/5)][(int)(x/5)] == '1') || y/5 > map.getYSize() + 20) {
						hasHit = true;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
			
		case GUN:
			x = xInit;
			y = yInit;
			int j = 0;
			while (!hasHit && inBounds((int)(y/5), (int)(x/5))) {
				if (grid[(int)(y/5)][(int)(x/5)] == '1') {
					hasHit = true;
				}
				j++;
				x = xInit + hInitSpeed*j;
				y = yInit + vInitSpeed*j;
			}
			break;

		default:
			break;
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
