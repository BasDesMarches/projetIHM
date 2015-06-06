package views;

import application.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapView {
	int squareSize;
	Map m;
	ImageView bg;
	ImageView fg;
	GridPane map;
	
	public MapView(Map m) {
		squareSize = 5;
		this.m = m;
		map = new GridPane();
		char cases[][] = m.getMap();
		for (int i = 0; i < m.getYSize(); i++) {
			for (int j = 0; j < m.getXSize(); j++) {
				Rectangle rect = new Rectangle(squareSize, squareSize, Color.DEEPSKYBLUE);
				if (cases[i][j] == '1') {
					rect = new Rectangle(squareSize, squareSize, Color.PERU);
				}
				map.add(rect, j, i);
			}
		}
		m.getHasChanged().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					redrawMap();
					m.getHasChanged().set(false);
				}
			}
		});
	}
	
	public void redrawMap() {
		char cases[][] = m.getMap();
		for(Node node : map.getChildren()){
			if(cases[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] == '0'){
				((Rectangle) node).setFill(Color.DEEPSKYBLUE);
			}
		}
	}
	
	public GridPane getView() {
		return map;
	}
	
	public Map getMap() {
		return m;
	}
}
