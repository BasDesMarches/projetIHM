package application;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Worm {
	String name;
	Map map;
	SimpleIntegerProperty life;
	Weapon weapon;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	SimpleBooleanProperty onRight;
	boolean choosingWeapon;
	SimpleDoubleProperty xFire;
	SimpleDoubleProperty yFire;
	SimpleBooleanProperty isFiring;
	
	
//	int paceCounter;
	//new Team team;
	
	public Worm(String name, Map m, int x, int y, Weapon w){
		this.name = name;
		map = m;
		xPos = new SimpleIntegerProperty(x);
		yPos = new SimpleIntegerProperty(y);
		onRight = new SimpleBooleanProperty(true);
		isFiring = new SimpleBooleanProperty(false);
		choosingWeapon = false;
		weapon = w;
		life = new SimpleIntegerProperty(100);
		xFire= new SimpleDoubleProperty(x);
		yFire= new SimpleDoubleProperty(y);
		
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
		isFiring.set(true);
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
					xFire.set(xInit + hInitSpeed*i);
					yFire.set(yInit + vInitSpeed*i + g*i*i);
					i++;
					Thread.sleep(10);
					if ((inBounds((int)(yFire.get()/5), (int)(xFire.get()/5)) && grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') || yFire.get()/5 > map.getYSize() + 20) {
						hasHit = true;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
			
		case GUN:
			xFire.set(xInit);
			yFire.set(yInit);
			int j = 0;
			while (!hasHit && inBounds((int)(yFire.get()/5), (int)(xFire.get()/5))) {
				if (grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') {
					hasHit = true;
				}
				j++;
				xFire.set(xInit + hInitSpeed*j);
				yFire.set(yInit + vInitSpeed*j);
			}
			break;

		default:
			break;
		}
		map.destroy((int)(yFire.get()/5), (int)(xFire.get()/5), weapon.getDamage());
		isFiring.set(false);
	}
	
	private boolean inBounds(int y, int x) {
		return (0 <= y && y < map.getYSize() && 0 <= x && x < map.getXSize());
	}
	
	public void  wound(int i){
		life.set(life.get()-i);
	}
	
	public SimpleIntegerProperty lifeProperty() {
		return life;
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
	
	public void setLife(SimpleIntegerProperty life) {
		this.life = life;
	}
	
	public boolean isChoosingWeapon() {
		return choosingWeapon;
	}
	
	public void setIsChoosingWeapon(boolean b) {
		choosingWeapon = b;
	}
	public SimpleBooleanProperty IsFiring() {
		return isFiring;
	}

	public void setIsFiring(SimpleBooleanProperty isFiring) {
		this.isFiring = isFiring;
	}
}
