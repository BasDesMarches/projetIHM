package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class World {
	MapView map;
	WormView[] allWorms;
	int index=0;
	WormView currentWorm;
	Group world;
	TilePane weaponChooser;
	
	public World(Map m, Worm w) {
		map = new MapView(m);
		currentWorm = new WormView(w, m);
		ImageView worm = currentWorm.getPic();
		weaponChooser = new TilePane();
		initiateWeaponChooser();
		worm.layoutXProperty().bind(currentWorm.xProperty().multiply(5));
		worm.layoutYProperty().bind(currentWorm.yProperty().multiply(5));
		
		
		world = new Group();
		world.getChildren().add(map.getView());
		world.getChildren().add(worm);
		world.getChildren().add(weaponChooser);
		world.getChildren().add(currentWorm.wormGroup);
	}
	
	private void initiateWeaponChooser(){
		for (Weapon weapon : Weapon.values()) {
			weaponChooser.getChildren().add(new ImageView(weapon.getImage()));
		}
		weaponChooser.setMaxWidth(64);
		weaponChooser.setStyle("-fx-background-color : #000000");
		weaponChooser.setVisible(false);
	}
	
	public void nextWorm(){
		if (index<allWorms.length-1){
		currentWorm = allWorms[index+1];
		index++;
		}else{currentWorm = allWorms[0];
		index = 0;
		}
	}
	
	private void damages(int dam, int rad){
		for(int i = 0; i<allWorms.length;i++)
		{
			if (Math.pow(allWorms[i].worm.xPos.get()-currentWorm.worm.xFire.get(),2)+Math.pow(allWorms[i].worm.yPos.get()-currentWorm.worm.yFire.get(),2)<Math.pow(rad, 2)){
				allWorms[i].worm.life.subtract(dam);
			}
		}
	}
	
	private void addWorm(Worm w, Map m){
		 WormView newWorm = new WormView(w, m);
		 WormView[] a = new WormView[allWorms.length+1];
		 for (int i = 0; i<allWorms.length;i++){
			 a[i]=allWorms[i];
		 }
		 a[allWorms.length] = newWorm;
		 allWorms = a;
	}
	
	private void removeWorm(int index){
		WormView[] a = new WormView[allWorms.length-1];
		 for (int i = 0; i<index;i++){
			 a[i]=allWorms[i];
		 }
		 for (int i = index+1; i<allWorms.length; i++){
			 a[i-1]=allWorms[i];
		}
		 allWorms =a;
	}
	
	
	// ========== Getters and setters ==========
	public Group getWorld() {
		return world;
	}

	public MapView getMap() {
		return map;
	}

	public WormView getCurrentWorm() {
		return currentWorm;
	}
	
	public void showWeaponChooser() {
		weaponChooser.setVisible(true);
		currentWorm.getWorm().setIsChoosingWeapon(true);
	}
	
	public void hideWeaponChooser() {
		weaponChooser.setVisible(false);
		currentWorm.getWorm().setIsChoosingWeapon(false);
	}
}
