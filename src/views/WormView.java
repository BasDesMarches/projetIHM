package views;

import java.util.ArrayList;

import tasks.Fire;
import application.Map;
import application.Worm;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WormView {
	Worm worm;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	ImageView pic;
	ImageView weapon;
	Rectangle lifeBg = new Rectangle(30,5, Color.BLACK);
	Rectangle lifeValue = new Rectangle(30,5, Color.GREEN);
	Rectangle hitbox = new Rectangle(30,30);
	ImageView sel;
	Text name = new Text();
	ImageView bullet = new ImageView();
	Image im = new Image("Images/Worms/bull1.png");
	Group wormGroup = new Group();
	static public ArrayList<Rectangle> hitBoxs = new ArrayList<Rectangle>();
	static public ArrayList<Worm> worms = new ArrayList<Worm>();
	static public ArrayList<WormView> wormViews = new ArrayList<WormView>();

	public WormView(Worm w, Map map, Color teamColor) {
		worm = w;
		worms.add(worm);
		wormViews.add(this);
		xPos = new SimpleIntegerProperty();
		xPos.bindBidirectional(worm.xPosProperty());
		yPos = new SimpleIntegerProperty();
		yPos.bindBidirectional(worm.yPosProperty());
		pic = new ImageView(new Image("Images/Worms/test2.gif"));
		pic.setViewport(new Rectangle2D(15, 15, 30, 30));
		pic.scaleXProperty().bind(new When(w.isOnRight()).then(-1).otherwise(1));
		pic.layoutXProperty().bind(this.xProperty().multiply(5));
		pic.layoutYProperty().bind(this.yProperty().multiply(5));
		weapon = new ImageView(worm.getWeapon().getImage());
		weapon.visibleProperty().bind(worm.isCurrentWorm().and(worm.isChoosingWeapon().not()));
		lifeBg.xProperty().bind(xPos.multiply(5));
		lifeBg.yProperty().bind(yPos.multiply(5).add(-10));
		lifeValue.xProperty().bind(xPos.multiply(5));
		lifeValue.yProperty().bind(yPos.multiply(5).add(-10));
		lifeValue.widthProperty().bind(worm.lifeProperty().multiply(0.3));
		hitbox.xProperty().bind(xPos.multiply(5));
		hitbox.yProperty().bind(yPos.multiply(5));
		hitbox.setFill(Color.TRANSPARENT);
		hitBoxs.add(hitbox);
		name.setText(worm.getName());
		name.layoutXProperty().bind(this.xProperty().multiply(5));
		name.layoutYProperty().bind(this.yProperty().multiply(5).add(-15));
		name.setFont(Font.font("Verdana",11));
		name.setFill(teamColor);
		sel = new ImageView(new Image("Images/Worms/sel.png"));
		sel.xProperty().bind(xPos.multiply(5).add(6));
		sel.yProperty().bind(yPos.multiply(5).add(-45));
		sel.visibleProperty().bind(worm.isCurrentWorm());
		
		while (yPos.get() >= 0 && (map.getMap()[yPos.get() + 4][xPos.get() + 2]) == '1') {
			yPos.set(yPos.get() - 1);
		}
		while ((yPos.get() + 5 < 120) && (map.getMap()[yPos.get() + 5][xPos.get() + 2]) == '0') {
			yPos.set(yPos.get() + 1);
		}

		bullet.setImage(im);
		bullet.setViewport(new Rectangle2D(15, 15, 30, 30));
		bullet.layoutXProperty().bind(worm.xFireProperty().subtract(15));
		bullet.layoutYProperty().bind(worm.yFireProperty().subtract(15));
		bullet.visibleProperty().bind(Worm.isFiring.and(worm.isCurrentWorm()).and(worm.isBulletInBounds()));
		wormGroup.getChildren().addAll(pic,hitbox,name, bullet, lifeBg, lifeValue, sel, weapon);
		wormGroup.visibleProperty().bind(w.isAliveProperty());
//		wormGroup.setVisible(false);
		
	}
	
	private Image weaponImageSel(){
		Image a = new Image("Images/Worms/bull1.png");
		switch(worm.getWeapon()){
		case GRENADE:
			a = new Image("Images/Worms/grenade.png");
			break;
		default:
			break;
		}
		return a;
	}

	public void fire(double angle, double initSpeed) {
		if (Worm.isFiring.get()) {
			return;
		}
		im =weaponImageSel();
		bullet.setImage(im);
		Worm.isFiring.set(true);
		worm.xFireProperty().set((xPos.get() + 3)*5);
		worm.yFireProperty().set((yPos.get() + 3)*5);
		Fire f = new Fire(angle, initSpeed, this);
		Thread th = new Thread(f);
		th.start();
	}
	
	// ========== Getters and setters ==========
	public ImageView getPic() {
		return pic;
	}

	public Worm getWorm() {
		return worm;
	}
	
	public Group getWormGroup() {
		return wormGroup;
	}

	public SimpleIntegerProperty xProperty() {
		return xPos;
	}
	
	public SimpleIntegerProperty yProperty() {
		return yPos;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}
	
	public void setWeaponImage(Image image) {
		weapon.setImage(image);
	}
}
