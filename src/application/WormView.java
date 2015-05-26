package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WormView {
	Worm worm;
	SimpleIntegerProperty xPos;
	SimpleIntegerProperty yPos;
	Image pic;
	
	public WormView(Worm w) {
		worm = w;
		xPos = new SimpleIntegerProperty();
		xPos.bind(worm.xPosProperty());
		yPos = new SimpleIntegerProperty();
		yPos.bind(worm.yPosProperty());
		pic = new Image("Images/Worms/wjetfly4.png",30,30,true,false);
	}
	
	public ImageView getPic() {
		return new ImageView(pic);
	}
	
	public SimpleIntegerProperty xProperty() {
		return xPos;
	}
	
	public SimpleIntegerProperty yProperty() {
		return yPos;
	}
}
