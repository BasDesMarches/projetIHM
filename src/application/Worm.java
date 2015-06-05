package application;

import tasks.Fire;
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
	//int paceCounter;
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
		double xInit = (xPos.get() + 3)*5;
		double yInit = (yPos.get() + 3)*5;
		isFiring.set(true);
		xFire.set(xPos.get());
		yFire.set(yPos.get());
		Fire f = new Fire(xInit, yInit, angle, initSpeed, map, weapon, xFire, yFire, isFiring);
		Thread th = new Thread(f);
		th.start();
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
	
	public SimpleDoubleProperty xFireProperty() {
		return xFire;
	}


	public void setxFire(SimpleDoubleProperty xFire) {
		this.xFire = xFire;
	}


	public SimpleDoubleProperty yFireProperty() {
		return yFire;
	}


	public void setyFire(SimpleDoubleProperty yFire) {
		this.yFire = yFire;
	}


	public void setIsChoosingWeapon(boolean b) {
		choosingWeapon = b;
	}
	public SimpleBooleanProperty isFiring() {
		return isFiring;
	}

	public void setIsFiring(SimpleBooleanProperty isFiring) {
		this.isFiring = isFiring;
	}
}
