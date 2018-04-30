package brickBreaker;

import java.awt.Color;
import java.awt.Graphics2D;

public class brickGenerator {
	
	private boolean map[][];
	private int brickWidth;
	private int brickHeight;
	private int totalBricks;
	
	
	public brickGenerator(int row, int col)
	{
		//creates the bricks, elements set to false by default in boolean array so false = brick, no need to populate.
		setMap(new boolean[row][col]);
		setBrickWidth(680/col);
		setBrickHeight(200/row);
		setTotalBricks(col*row);
	}
	
	public void draw(Graphics2D g)
	{
		//draws all the bricks
		g.setColor(Color.white);
		for(int i = 0; i < getMap().length; i++) 
		{
			for(int j = 0; j < getMap()[0].length; j++)
			{
				if(!getMap()[i][j])
				{
					g.fillRect(j * getBrickWidth()  + 80, i * getBrickHeight() + 50, getBrickWidth()-2, getBrickHeight()-2);
				}
			}
		}
	}
	
	public void setBrickBoolean(boolean value, int row, int col)
	{
		getMap()[row][col] = value;
	}

	public int getTotalBricks() {
		return totalBricks;
	}

	public void setTotalBricks(int totalBricks) {
		this.totalBricks = totalBricks;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public void setBrickHeight(int brickHeight) {
		this.brickHeight = brickHeight;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public void setBrickWidth(int brickWidth) {
		this.brickWidth = brickWidth;
	}

	public boolean[][] getMap() {
		return map;
	}

	public void setMap(boolean map[][]) {
		this.map = map;
	}
}
