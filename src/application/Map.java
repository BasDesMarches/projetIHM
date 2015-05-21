package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map{
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
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void destroy(int xPos, int yPos, int rad)
	{
		for(int i=xPos-rad;i<=xPos+rad;i++){
			for (int j=yPos-rad;i<=yPos+rad;i++)
			{
				if (Math.pow(i-xPos,2)+Math.pow(j-yPos,2)< Math.pow(rad,2))
					map[i][j]=0;
			}
		}
		
	}
	
}
