package mazeGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Cell {
	private int i,j = 0;
	private int w = Maze.getW();
	private int cols = Maze.getCols();
	private int rows = Maze.getRows();
	public boolean[] wall = {true, true, true, true};
	private boolean visited = false;
	
	Random rand = new Random();
	
	//set up colours
	Color purple = new Color(255, 0 , 255, 100);
	Color white = new Color(255,255,255,255);
	
	public void show(Graphics g){
		int x = i*w;
		int y= j*w;
		
		if(visited)
		{
			g.setColor(purple);
			g.fillRect(x, y, w, w);
		}
		
		//draw walls
		if (wall[0])
		{
			g.setColor(white);
			g.drawLine(x, y, x + w, y);
		}
		if (wall[1])
		{
			g.setColor(white);
			g.drawLine(x + w, y, x + w, y + w);
		}
		if (wall[2])
		{
			g.setColor(white);
			g.drawLine(x + w, y + w, x, y + w);
		}
		if (wall[3])
		{
			g.setColor(white);
			g.drawLine(x, y + w, x, y);
		}
		
		
	}
	
	
	public int index(int i, int j) {
		return (i + (j * cols));
	}
	
	
	public Cell checkNeighbours() {
		// picks a random neighbour as the next cell
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		
		if(!(j-1 < 0))
		{
			Cell top = Maze.grid[index(i, j - 1)];
			if (!top.visited)
			{
				neighbours.add(top);
			}
		}
		if(!(i+1 > cols-1))
		{
			Cell right = Maze.grid[index(i+1, j)];
			if (!right.visited)
			{
				neighbours.add(right);
			}
		}
		if(!(j+1 > rows - 1))
		{
			Cell bottom = Maze.grid[index(i, j+1)];
			if (!bottom.visited)
			{
				neighbours.add(bottom);
			}
		}
		if(!(i-1 < 0))
		{
			Cell left = Maze.grid[index(i - 1, j)];
			if (!left.visited)
			{
				neighbours.add(left);
			}
		}
		
		if (neighbours.size() > 0)
		{
			int r = rand.nextInt(neighbours.size());
			return neighbours.get(r);
		}
		else {
			return null;
		}
		
	}
	
	

	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean[] getWall() {
		return wall;
	}

	public void setWall(boolean[] wall) {
		this.wall = wall;
	}
	
}
