package application;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ToggleSet {
	VBox set;
	ImageView thumbnail;
	Label label;
	RadioButton button;
	
	public ToggleSet(String name, ToggleGroup tg) {
		thumbnail = new ImageView(new Image("Images/Thumbnails/" + name + ".png"));
		thumbnail.setFitHeight(120);
		thumbnail.setPreserveRatio(true);
		label = new Label(name);
		button = new RadioButton();
		button.setUserData(name);
		button.setToggleGroup(tg);
		button.setVisible(false);
		set = new VBox(5, thumbnail, label);
		set.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.setSelected(true);
			}
		});
	}
	
	public VBox getSet() {
		return set;
	}
}
