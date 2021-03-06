package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
//import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class TeamSelectorView {
//	static ToggleGroup redGroup;
//	static ToggleGroup blueGroup;
//	static ToggleGroup yellowGroup;
//	static ToggleGroup greenGroup;
	ObservableList<String> redWormNames = FXCollections.observableArrayList(
			new String("Ralph"),
			new String("Regan"),
			new String("Rick"),
			new String("Ronald"),
			new String("Rudy"));
	ObservableList<String> blueWormNames = FXCollections.observableArrayList(
			new String("Bart"),
			new String("Benny"),
			new String("Billy"),
			new String("Bob"),
			new String("Buddy"));
	ObservableList<String> yellowWormNames = FXCollections.observableArrayList(
			new String("Yahn"),
			new String("Yessir"),
			new String("Yves"),
			new String("Yohan"),
			new String("Yu"));
	ObservableList<String> greenWormNames = FXCollections.observableArrayList(
			new String("Gary"),
			new String("George"),
			new String("Gin"),
			new String("Gotham"),
			new String("Gui"));
	
	TextField name;
	ChoiceBox<Color> colorSelector;
	ListView<String> wormNamesView;
	VBox teamSelector;
	TextField tf;
	
	public TeamSelectorView(String name, Color def) {
		this.name = new TextField(name);
		tf = new TextField();
		colorSelector = new ChoiceBox<Color>();
		colorSelector.setId("colorSelector");
		wormNamesView = new ListView<String>();
		wormNamesView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				wormNamesView.edit(wormNamesView.getSelectionModel().getSelectedIndex());
			}
		});
		wormNamesView.setOnEditStart(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(EditEvent<String> event) {
				if (wormNamesView.getEditingIndex() != -1) {
					tf.setText(wormNamesView.getItems().get(wormNamesView.getEditingIndex()));
				} else {
					tf.setText("");
				}
			}
		});
		tf.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER && wormNamesView.getEditingIndex() != -1) {
					String str = new String(tf.getText());
					wormNamesView.getItems().set(wormNamesView.getEditingIndex(), str);
					wormNamesView.edit(-1);
				}
			}
		});
		wormNamesView.setEditable(true);
		setBox(colorSelector, def);
		teamSelector = new VBox(10, this.name, new Label("Team color :"),colorSelector, wormNamesView, tf);
	}
	
	private void setBox(ChoiceBox<Color> box, Color def) {
		box.setConverter(new StringConverter<Color>() {
			@Override
			public String toString(Color object) {
				String str = null;
				if (object == Color.YELLOW) {
					str = "Yellow";
				} else if (object == Color.RED) {
					str = "Red";
				} else if (object == Color.BLUE) {
					str = "Blue";
				} else if (object == Color.GREEN) {
					str = "Green";
				}
				return str;
			}
			@Override
			public Color fromString(String string) {
				Color color = null;
				if (string.equals("Yellow")) {
					color = Color.YELLOW;
				} else if (string.equals("Red")) {
					color = Color.RED;
				} else if (string.equals("Blue")) {
					color = Color.BLUE;
				} else if (string.equals("Green")) {
					color = Color.GREEN;
				}
				return color;
			}
		});
		box.getItems().addAll(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
		box.valueProperty().addListener(new ChangeListener<Color>() {
			@Override
			public void changed(ObservableValue<? extends Color> observable,
					Color oldValue, Color newValue) {
				if (newValue == Color.YELLOW) {
					box.setStyle("-fx-border-color: #bbbb11;");
					wormNamesView.setItems(yellowWormNames);
				} else if (newValue == Color.RED) {
					box.setStyle("-fx-border-color: #cc1111;");
					wormNamesView.setItems(redWormNames);
				} else if (newValue == Color.BLUE) {
					box.setStyle("-fx-border-color: #1111cc;");
					wormNamesView.setItems(blueWormNames);
				} else if (newValue == Color.GREEN) {
					box.setStyle("-fx-border-color: #11aa11;");
					wormNamesView.setItems(greenWormNames);
				}
			}
		});
		box.setValue(def);
	}
	
	public VBox getSelector() {
		return teamSelector;
	}
	
	public String getName() {
		return name.getText();
	}
	
	public Color getColor() {
		return colorSelector.getValue();
	}
	
	public ObservableList<String> getWormNames() {
		return wormNamesView.getItems();
	}
}
