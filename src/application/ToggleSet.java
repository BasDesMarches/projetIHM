package application;

import javafx.beans.binding.When;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ToggleSet {
	VBox set;
	ImageView thumbnail;
	Text label;
	RadioButton button;
	
	public ToggleSet(String name, ToggleGroup tg) {
		button = new RadioButton();
		button.setUserData(name);
		button.setToggleGroup(tg);
		button.setVisible(false);
		
		DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.web("#222222"), 10, 0.8, 0, 0);
		shadow.colorProperty().bind(new When(button.selectedProperty()).then(Color.web("#bb1111")).otherwise(Color.web("#222222")));
		
		thumbnail = new ImageView(new Image("Images/Thumbnails/" + name + ".png"));
		thumbnail.setFitHeight(120);
		thumbnail.setPreserveRatio(true);
		thumbnail.setEffect(shadow);
		
		label = new Text(name);
		label.wrappingWidthProperty().bind(thumbnail.fitWidthProperty());
		label.setId("tgLabel");
		label.setFill(Color.web("#bbbbbb"));
		
		set = new VBox(5, thumbnail, label);
		set.setAlignment(Pos.CENTER);
		set.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.setSelected(true);
			}
		});
	}
	
	public void showEffect() {
		
	}
	
	public void hideEffect() {
		
	}
	
	public VBox getSet() {
		return set;
	}
	
	public void setSelected(boolean b) {
		button.setSelected(b);
	}
}
