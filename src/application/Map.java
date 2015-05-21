package application;

public class Map{
	int map[][];
	String mapFile;
	
	public Map(String s) {
		mapFile = s;
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
