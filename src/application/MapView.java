package application;

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
//	GridPane ground;
	GridPane map;
//	StackPane result;
	
	public MapView(Map m) {
		squareSize = 5;
//		ground = new GridPane();
		this.m = m;
		char cases[][] = m.getMap();
		map = new GridPane();
		for (int i = 0; i < m.getYSize(); i++) {
			for (int j = 0; j < m.getXSize(); j++) {
				Rectangle rect = new Rectangle(squareSize, squareSize, Color.DEEPSKYBLUE);
				if (cases[i][j] == '1') {
					rect = new Rectangle(squareSize, squareSize, Color.PERU);
				}
				map.add(rect, j, i);
			}
		}
//		bg = new ImageView(new Image("Images/SkyBG.jpg",squareSize*(m.getXSize()),squareSize*(m.getYSize()),true,false));
//		fg = new ImageView(new Image("Images/soil.jpg",squareSize*(m.getXSize()),squareSize*(m.getYSize()),true,false));
//		fg.setClip(ground);
//		result = new StackPane();
//		result.getChildren().add(ground);
//		result.getChildren().add(bg);
//		result.getChildren().add(fg);
//		result.getChildren().add(map);
	}
	
	public GridPane getView() {
		return map;
	}
	
	public Map getMap() {
		return m;
	}
}
