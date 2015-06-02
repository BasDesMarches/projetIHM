package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Shape;

public class WeaponView {
	SimpleDoubleProperty xPos;
	SimpleDoubleProperty yPos;
	Shape fire;
	
	
	public SimpleDoubleProperty getxPos() {
		return xPos;
	}
	public void setxPos(SimpleDoubleProperty xPos) {
		this.xPos = xPos;
	}
	public SimpleDoubleProperty getyPos() {
		return yPos;
	}
	public void setyPos(SimpleDoubleProperty yPos) {
		this.yPos = yPos;
	}
	public Shape getFire() {
		return fire;
	}
	public void setFire(Shape fire) {
		this.fire = fire;
	}
	
	
	

}
