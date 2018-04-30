package mazeGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Maze extends JPanel implements ActionListener {

	private Timer timer;
	private int delay = 1;
	
	private static int cols,rows;
	private static int w = 20;
	
	Cell current;
	
	public static Cell[] grid;
	
	Stack<Cell> stack = new Stack<Cell>();
	
	public Maze() 
	{
		//setting up JPanel
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.setBackground(Color.black);
		
		//calculate number of cells
		setCols((int)(Main.width/getW()));
		setRows((int)(Main.height/getW()));
		grid = new Cell[getCols() * getRows()];
		
		//generate cells
		for (int j = 0; j < getRows(); j++)
		{
			for (int i = 0; i < getCols(); i++)
			{
				
				Cell cell = new Cell();
				cell.setI(i);
				cell.setJ(j);
				grid[i+(j*getCols())] = cell;
			}
		}
		
		current = grid[0];
		
		//starts timer to trigger action performed procedure
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		//draws cells
		for (int i=0; i<grid.length; i++)
		{
			grid[i].show(g);
		}
		
		//depth first search with recursive backtracker algorithm to generate maze
		current.setVisited(true);
		Cell next = current.checkNeighbours();
		if (next != null)
		{
			next.setVisited(true);
			
			stack.push(current);
			
			removeWalls(current, next);
			current = next;
		}
		else if (stack.size() > 0)
		{
			current = stack.pop();
		}
	}
	
	public void removeWalls(Cell a,Cell b) {
		
		int x = a.getI() - b.getI();
		if(x == 1)
		{
			a.wall[3] = false;
			b.wall[1] = false;
		}
		else if(x == -1)
		{
			a.wall[1] = false;
			b.wall[3] = false;
		}
		
		int y = a.getJ() - b.getJ();
		if(y == 1)
		{
			a.wall[0] = false;
			b.wall[2] = false;
		}
		else if(y == -1)
		{
			a.wall[2] = false;
			b.wall[0] = false;
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		repaint();
	}

	public static int getW() {
		return w;
	}

	public void setW(int w) {
		Maze.w = w;
	}

	public static int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		Maze.cols = cols;
	}

	public static int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		Maze.rows = rows;
	}
}
