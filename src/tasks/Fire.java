package tasks;

import java.util.Timer;

import views.WormView;
import application.Map;
import application.Weapon;
import application.Worm;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Fire extends Task<Void>{
	Map map;
	double xInit;
	double yInit;
	double hInitSpeed;
	double vInitSpeed;
	Weapon weapon;
	SimpleDoubleProperty xFire;
	SimpleDoubleProperty yFire;
	WormView wormv;
	Worm worm;
	
	public Fire(double angle, double initSpeed, WormView w) {
		wormv = w;
		worm = wormv.getWorm();
		map = worm.getMap();
		weapon = worm.getWeapon();
		xInit = (worm.xPosProperty().get() + 3)*5;
		yInit = (worm.yPosProperty().get() + 3)*5;
		hInitSpeed = initSpeed*Math.cos(angle);
		vInitSpeed = initSpeed*Math.sin(angle);
		xFire = new SimpleDoubleProperty();
		yFire = new SimpleDoubleProperty();
		xFire.bindBidirectional(worm.xFireProperty());
		yFire.bindBidirectional(worm.yFireProperty());
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
						if ((inBounds((int)(yFire.get()/5), (int)(xFire.get()/5)) && grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') || yFire.get()/5 > map.getYSize() + 20 || wormHit(xFire.get(), yFire.get())) {
							hasHit = true;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
				
			case GUN:
				double xf = xInit;
				double yf = yInit;
				int j = 0;
				while (!hasHit && inBounds((int)(yf/5), (int)(xf/5))) {
					if (grid[(int)(yf/5)][(int)(xf/5)] == '1' || wormHit(xf, yf)) {
						hasHit = true;
					}
					if (!hasHit) {
						xf = xInit + 0.5*hInitSpeed*j;
						yf = yInit + 0.5*vInitSpeed*j;
						j++;
					}
				}
				final double tempxf = xf;
				final double tempyf = yf;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						xFire.set(tempxf);
						yFire.set(tempyf);
					}
				});
				break;
			case PUNCH:
				if(wormv.getWorm().isOnRight().get()){
					xFire.set(xInit+30);
				}else{xFire.set(xInit-30);
				}
				yFire.set(yInit);
				break;
	
			case GRENADE:
				Timer t= new Timer();
				int k = 0;
				try {
					while (!hasHit) {
						final int k2 = k;
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								xFire.setValue(xInit + hInitSpeed*k2);
								yFire.setValue(yInit + vInitSpeed*k2 + g*k2*k2);
							}
						});
						Thread.sleep(10);
						k++;
						if ((inBounds((int)(yFire.get()/5), (int)(xFire.get()/5)) && grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') || yFire.get()/5 > map.getYSize() + 20) {
							//hasHit = true;
							 hInitSpeed= hInitSpeed/2;
							 vInitSpeed= vInitSpeed/2;
							 k=0;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				map.destroy((int)(yFire.get()/5), (int)(xFire.get()/5), weapon.getDamage());
				Worm.isFiring.set(false);
			}
		});
		return null;
	}
	
	private boolean inBounds(int y, int x) {
		return (0 <= y && y < map.getYSize() && 0 <= x && x < map.getXSize());
	}
	
	private boolean wormHit(double x, double y){
		boolean b = false;
		for (Rectangle r : WormView.hitBoxs) {
			if (wormv.getHitbox() != r && r.visibleProperty().get() && r.contains(new Point2D(x, y))) {
				b = true;
			}
		}
		return b;
	}

}
