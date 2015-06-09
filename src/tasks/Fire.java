package tasks;

import views.WorldView;
import application.Map;
import application.Weapon;
import application.Worm;
import javafx.application.Platform;
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
	WorldView wV;
	//Worm worm;
	
	public Fire(double angle, double initSpeed, WorldView ww) {
		wV = ww; 
		Worm worm = wV.getTeamView().get(wV.getCurrentTeamIndex()).getMembers().get(wV.getCurrentWormIndex()).getWorm();
		//worm = w;
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
						if ((inBounds((int)(yFire.get()/5), (int)(xFire.get()/5)) && grid[(int)(yFire.get()/5)][(int)(xFire.get()/5)] == '1') || yFire.get()/5 > map.getYSize() + 20/*||wormHit()*/) {
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
					if (grid[(int)(yf/5)][(int)(xf/5)] == '1'|| wormHit()) {
						hasHit = true;
					}
					xf = xInit + hInitSpeed*j;
					yf = yInit + vInitSpeed*j;
					j++;
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
	
			default:
				break;
		}
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				map.destroy((int)(yFire.get()/5), (int)(xFire.get()/5), weapon.getDamage()); 
				for (int i = 0;i < wV.getTeamView().size(); i++ ){
					for (int j = 0;j < wV.getTeamView().get(i).getMembers().size();i++){
						findWorm(i,j).yPosAdjust();
					}
				}	
				Worm.isFiring.set(false);
			}
		});
		return null;
	}
	
	private boolean inBounds(int y, int x) {
		return (0 <= y && y < map.getYSize() && 0 <= x && x < map.getXSize());
	}
	
	private boolean wormHit(){
		boolean a = false;
		for (int i = 0;i < wV.getTeamView().size(); i++ ){
			for (int j = 0;j < wV.getTeamView().get(i).getMembers().size();i++){
				if (i!=wV.getCurrentTeamIndex()&&j!=wV.getCurrentWormIndex()){
					if (wV.getTeamView().get(i).getMembers().get(j).getHitbox().intersects(wV.getCurrentWorm().getFireBox().getLayoutBounds())){
						a=true;
					}
				}
			}
		}
		return a;
	}
	
	/*
	private void damages(int dam, int rad) {
		for (int i = 0; i < allWorms.length; i++) {
			if (Math.pow(allWorms[i].getWorm().xPosProperty().get() - currentWorm.getWorm().xFireProperty().get(), 2) + Math.pow(allWorms[i].getWorm().yPosProperty().get() - currentWorm.getWorm().yFireProperty().get(), 2) < Math.pow(rad, 2)) {
				allWorms[i].getWorm().lifeProperty().subtract(dam);
			}
			if (allWorms[i].getWorm().lifeProperty().get()<=0){
				removeWorm(i);
			}
		
		}
	}
	*/
	
	private Worm findWorm(int i, int j){
		return wV.getTeamView().get(i).getMembers().get(j).getWorm();
	}
	}
