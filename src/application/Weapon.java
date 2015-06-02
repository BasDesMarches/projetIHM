package application;

import javafx.scene.image.Image;

public enum Weapon {
	ROCKET("Rocket",10,1,"Images/Weapons/bazooka.1.png"),GUN("Gun",4,2,"Images/Weapons/handgun.1.png"),PUNCH("Punch",6,1,"Images/Weapons/firepnch.1.png");

	String name;
	int damage;
	int numberOfStrikes;
	Image image;

	private Weapon(String name, int damage, int numberOfStrikes, String imagePath) {
		this.name = name;
		this.damage = damage;
		this.numberOfStrikes = numberOfStrikes;
		image = new Image(imagePath);
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int getNumberOfStrikes() {
		return numberOfStrikes;
	}
	
	public Image getImage() {
		return image;
	}
}
