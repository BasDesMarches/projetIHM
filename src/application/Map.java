package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map{
	int map[][];
	String mapFile;
	int xSize;
	int ySize;
	
	public Map(String s) {
		mapFile = s;
		loadMap();
	}
	
	private void loadMap() {
		File f = new File("Ressources/Map/" + mapFile);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			xSize = Integer.parseInt(br.readLine());
			ySize = Integer.parseInt(br.readLine());
			map = new int[xSize][ySize];
			for (int i = 0; i < ySize; i++) {
				for (int j = 0; j < xSize; j++) {
					map[j][i] = br.read();
				}
				br.read();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
}
