package application;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.When;
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
	SimpleBooleanProperty currentWorm;
	boolean choosingWeapon;
	SimpleDoubleProperty xFire;
	SimpleDoubleProperty yFire;
	public static SimpleBooleanProperty isFiring = new SimpleBooleanProperty(false);
	SimpleBooleanProperty bulletInBounds;
	static List<Worm> allWorms= new ArrayList<Worm>();
	//int paceCounter;
	//new Team team;
	
	public Worm(String name, Map m, int x, int y, Weapon w){
		this.name = name;
		map = m;
		xPos = new SimpleIntegerProperty(x);
		yPos = new SimpleIntegerProperty(y);
		onRight = new SimpleBooleanProperty(true);
		currentWorm = new SimpleBooleanProperty(false);
		choosingWeapon = false;
		weapon = w;
		life = new SimpleIntegerProperty(100);
		xFire = new SimpleDoubleProperty(x);
		yFire = new SimpleDoubleProperty(y);
		bulletInBounds = new SimpleBooleanProperty();
		bulletInBounds.bind(new When(xFire.greaterThan(15).and(yFire.greaterThan(15))).then(true).otherwise(false));
		//allWorms.add(this);
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
	
	public void yPosAdjust(){
		int i= yPos.get();
		int j= xPos.get();
		while((i + 5 < 120) && map.getMap()[i + 5][j + 2]==('0')){
			i++;
		}
		
	}
	
	public void moveRight() {
		onRight.set(true);
		elementalMove();
	}
	
	public void moveLeft() {
		onRight.set(false);
		elementalMove();
	}
	
	/*
	public void fire(double angle, double initSpeed) {
		if (isFiring.get()) {
			return;
		}
		isFiring.set(true);
		xFire.set((xPos.get() + 3)*5);
		yFire.set((yPos.get() + 3)*5);
		Fire f = new Fire(angle, initSpeed,this);
		Thread th = new Thread(f);
		th.start();
	}
	*/
	
	public void  wound(int i){
		life.set(life.get()-i);
	}
	
	// ========== Getters and setters ==========
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

	public Map getMap() {
		return map;
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
	
	public SimpleBooleanProperty isCurrentWorm() {
		return currentWorm;
	}


	public void setCurrentWorm(boolean b) {
		currentWorm.set(b);
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
	
	public SimpleBooleanProperty isBulletInBounds() {
		return bulletInBounds;
	}
}
