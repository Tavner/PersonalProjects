package mazeGenerator;

import javax.swing.JFrame;

public class Main {
	
	//had to declare these here because the JPanel was not initialising before the constructor ran in maze
	public static int width;
	public static int height;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setBounds(10, 10, 1000, 1000);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		width = window.getContentPane().getWidth();
		height = window.getContentPane().getHeight();
		Maze maze = new Maze();
		window.add(maze);
		
	}

}
