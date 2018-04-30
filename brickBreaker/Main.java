package brickBreaker;

import javax.swing.JFrame;

import brickBreaker.Game;

public class Main {

	public static void main(String[] args) {
		//setup JFrame window
		JFrame window = new JFrame();
		Game game= new Game();
		
		window.setBounds(10, 10, 846, 869);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(game);
	}

}
