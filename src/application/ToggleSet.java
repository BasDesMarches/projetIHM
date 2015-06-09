package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ToggleSet {
	VBox set;
	ImageView thumbnail;
	Text label;
	RadioButton button;
	
	public ToggleSet(String name, ToggleGroup tg) {
		thumbnail = new ImageView(new Image("Images/Thumbnails/" + name + ".png"));
		thumbnail.setFitHeight(120);
		thumbnail.setPreserveRatio(true);
		label = new Text(name);
		label.wrappingWidthProperty().bind(thumbnail.fitWidthProperty());
		label.setId("tgLabel");
		button = new RadioButton();
		button.setUserData(name);
		button.setToggleGroup(tg);
		button.setVisible(false);
		set = new VBox(5, thumbnail, label);
		set.setAlignment(Pos.CENTER);
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
	
	public void setSelected(boolean b) {
		button.setSelected(b);
	}
}
