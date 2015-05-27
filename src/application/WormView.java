package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WormView {
	Worm worm;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	Image pic;
	
	public WormView(Worm w, Map map) {
		worm = w;
		xPos = new SimpleIntegerProperty();
		xPos.bindBidirectional(worm.xPosProperty());
		yPos = new SimpleIntegerProperty();
		yPos.bindBidirectional(worm.yPosProperty());
		pic = new Image("Images/Worms/wjetfly4.png",30,30,true,false);
		while ((map.getMap()[yPos.get() + 4][xPos.get() + 2]) == '1') {
			yPos.set(yPos.get() - 1);
		}
		while ((map.getMap()[yPos.get() + 5][xPos.get() + 2]) == '0') {
			yPos.set(yPos.get() + 1);
		}
	}
	
	public ImageView getPic() {
		return new ImageView(pic);
	}

	public Worm getWorm() {
		return worm;
	}

	public SimpleIntegerProperty xProperty() {
		return xPos;
	}
	
	public SimpleIntegerProperty yProperty() {
		return yPos;
	}
}
