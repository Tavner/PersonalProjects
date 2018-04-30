package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener, ActionListener{

	
	
	private Timer timer;
	private int delay = 10;
	
	private brickGenerator map;
	
	
	public Game() 
	{
		map = new brickGenerator(6,9);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.setBackground(Color.red);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	private boolean play = false;
	private int score = 0;
	
	
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		
		//draws game area, border and score
		g.setColor(Color.black);
		g.fillRect(20, 20, 800, 800);
		
		g.setColor(Color.white);
		g.drawRect(19, 19, 801, 801);
		
		g.setFont(new Font("arial", Font.BOLD, 14));
		g.drawString("Score: " + score, 760, 15);
		
		//draws all the bricks
		map.draw((Graphics2D)g);
		
		//draws ball
		g.fillRect(playerX, 750, 100, 8);
		g.fillOval(ballCoordX, ballCoordY, 20,20);
		
		//game over screen
		if(ballCoordY > 800)
		{
			play = false;
			g.setFont(new Font("arial", Font.BOLD, 32));
			g.drawString("Game Over", 355, 380);
			g.drawString("Score: " + score, 370, 420);
			
		}
		
		//refreshes the game if no bricks left
		if(map.getTotalBricks() <= 0)
		{
			map = new brickGenerator(6,9);
			repaint();
		}
		
		g.dispose();
	}
	
	private int playerX = 300;
	private int ballCoordX = 120;
	private int ballCoordY = 350;
	private int ballDirX = 3;
	private int ballDirY = -3;

	private boolean left, right = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) 
		{
			//ball and paddle collision detector
			if(new Rectangle(ballCoordX, ballCoordY, 20, 20).intersects(new Rectangle(playerX, 750, 100, 8)))
			{
				ballDirX = -5 + (Math.round(((ballCoordX + 10) - playerX )/10));
				if(ballDirX < 0)
				{
					ballDirY = -6 - ballDirX;
				}
				else
				{
					ballDirY = -6 + ballDirX;
				}
			}
			
			//ball and brick collision
			brickIntersect: for(int i = 0; i<map.getMap().length; i++)
			{
				
				for(int j = 0; j<map.getMap()[0].length; j++)
				{
					if(!map.getMap()[i][j])
					{
						int brickCoordX = j * map.getBrickWidth() + 80;
						int brickCoordY = i * map.getBrickHeight() + 50;
						int brickWidth = map.getBrickWidth();
						int brickHeight = map.getBrickHeight();
						
						Rectangle brick = new Rectangle(brickCoordX, brickCoordY, brickWidth, brickHeight);
						Rectangle ball = new Rectangle(ballCoordX, ballCoordY, 20, 20);

		               
						if(ball.intersects(brick))
						{
							map.setBrickBoolean(true, i ,j);
							map.setTotalBricks(map.getTotalBricks() - 1);
							score += 1;
							
							Point pointRight = new Point(ballCoordX + 20, ballCoordY+10);
				            Point pointLeft = new Point(ballCoordX, ballCoordY+10);
				            Point pointTop = new Point(ballCoordX + 10, ballCoordY);
				            Point pointBottom = new Point(ballCoordX + 10, ballCoordY + 20 );
			
							if(brick.contains(pointRight) || brick.contains(pointLeft))
							{
								ballDirX = -ballDirX;
							}
							else if(brick.contains(pointTop) || brick.contains(pointBottom))
							{
								ballDirY = -ballDirY;
							}
							
							break brickIntersect;
						}
					}
				}
			}
			
			//ball and wall interaction
			ballCoordX += ballDirX;
			ballCoordY += ballDirY;
			if(ballCoordX < 19)
			{
				ballDirX = -ballDirX;
			}
			if(ballCoordX > 796)
			{
				ballDirX = -ballDirX;
			}
			if(ballCoordY < 20)
			{
				ballDirY = -ballDirY;
			}
			
			//paddle movement
			if(right)
			{
				if(playerX <= 719)
				{
					playerX += 5;
				}
			}
			if(left)
			{
				if(playerX >= 21)
				{
					playerX -= 5;
				}
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			play = true;
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			play = true;
			left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//game controls
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			//previously had the movement all in this statement however it caused jumpy movement
			//therefore i created a variable that set the paddle to move right until the key was let go.
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if((e.getKeyCode() == KeyEvent.VK_SPACE) && (!play))
		{
			//restarts game
			playerX = 300;
			ballCoordX = 120;
			ballCoordY = 350;
			ballDirX = 3;
			ballDirY = -3;
			map = new brickGenerator(6,9);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
