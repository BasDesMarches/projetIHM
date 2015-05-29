package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	char map[][];
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
			map = new char[ySize][xSize];
			for (int i = 0; i < ySize; i++) {
				String str = br.readLine();
				str.getChars(0, xSize, map[i], 0);
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

	public void destroy(int yPos, int xPos, int rad) {
		for (int i = yPos - rad; i <= yPos + rad; i++) {
			for (int j = xPos - rad; j <= xPos + rad; j++) {
				if (Math.pow(i - yPos, 2) + Math.pow(j - xPos, 2) < Math.pow(rad, 2) && inBounds(i,j)){
					map[i][j] = '0';
				}
			}
		}
	}
	
	private boolean inBounds(int i, int j) {
		return (0 <= i && i < ySize && 0 <= j && j < xSize);
	}

	// ========== Getters and setters ==========
	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public char[][] getMap() {
		return map;
	}
}
