package views;

import application.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class that generates a GridPane representing the <b>Map</b> given to the constructor.
 * @author Bastien
 */
public class MapView {
	int squareSize;
	Map m;
	GridPane map;
	
	/**
	 * Generates the elements of the GridPane according to the <b>Map</b> <code>m</code>.
	 * The size of each element (5 <i>px</i>) is set in here.
	 * 
	 * The <b>Listener</b> that redraws the map is also set in here.
	 * @param m
	 */
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
	
	/**
	 * Refresh the elements of the GridPane.
	 */
	public void redrawMap() {
		char cases[][] = m.getMap();
		for(Node node : map.getChildren()){
			if(cases[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] == '0'){
				((Rectangle) node).setFill(Color.DEEPSKYBLUE);
			}
		}
	}
	
	// ========== Getters and setters ==========
	public GridPane getView() {
		return map;
	}
	
	public Map getMap() {
		return m;
	}
}
