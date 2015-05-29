package application;

public enum Weapon {
	ROCKET("Rocket",10,1),GUN("Gun",4,2),FIST("Fist",6,1);

	String name;
	int damage;
	int numberOfStrikes;

	private Weapon(String name, int damage, int numberOfStrikes) {
		this.name = name;
		this.damage = damage;
		this.numberOfStrikes = numberOfStrikes;
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
	
}
