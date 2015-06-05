package tasks;

import application.Map;
import application.Weapon;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;

public class Fire extends Task<Void>{
	Map map;
	double xInit;
	double yInit;
	double hInitSpeed;
	double vInitSpeed;
	Weapon weapon;
	SimpleDoubleProperty xFire;
	SimpleDoubleProperty yFire;
	SimpleBooleanProperty isFiring;
	
	public Fire(double xi, double yi, double angle, double initSpeed, Map m, Weapon w, SimpleDoubleProperty xFireProperty, SimpleDoubleProperty yFireProperty, SimpleBooleanProperty firing) {
		xInit = xi;
		yInit = yi;
		hInitSpeed = initSpeed*Math.cos(angle);
		vInitSpeed = initSpeed*Math.sin(angle);
		map = m;
		weapon = w;
		xFire = new SimpleDoubleProperty();
		yFire = new SimpleDoubleProperty();
		isFiring = new SimpleBooleanProperty();
		xFire.bindBidirectional(xFireProperty);
		yFire.bindBidirectional(yFireProperty);
		isFiring.bindBidirectional(firing);
	}

	@Override
	protected Void call() throws Exception {
		boolean hasHit = false;
		char[][] grid = map.getMap();
		double g = 0.01;
		switch (weapon) {
			case ROCKET:
				int i = 0;
				try {
					while (!hasHit) {
						final int i2 = i;
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								xFire.setValue(xInit + hInitSpeed*i2);
								yFire.setValue(yInit + vInitSpeed*i2 + g*i2*i2);
							}
						});
						Thread.sleep(10);
						i++;
						if ((inBounds((int)(yFire.get()/5), (int)(xFire.get()/5)) && grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') || yFire.get()/5 > map.getYSize() + 20) {
							hasHit = true;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
				
			case GUN:
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						xFire.setValue(xInit);
						yFire.setValue(yInit);
					}
				});
				int j = 0;
				while (!hasHit && inBounds((int)(yFire.get()/5), (int)(xFire.get()/5))) {
					if (grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') {
						hasHit = true;
					}
					j++;
					final int j2 = j;
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							xFire.setValue(xInit + hInitSpeed*j2);
							yFire.setValue(yInit + vInitSpeed*j2);
						}
					});
				}
				break;
	
			default:
				break;
		}
		map.destroy((int)(yFire.get()/5), (int)(xFire.get()/5), weapon.getDamage());
		isFiring.set(false);
		return null;
	}
	
	private boolean inBounds(int y, int x) {
		return (0 <= y && y < map.getYSize() && 0 <= x && x < map.getXSize());
	}
}
