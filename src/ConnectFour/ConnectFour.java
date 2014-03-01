package ConnectFour;

import javax.swing.JFrame;

import ConnectFour.Board;

public class ConnectFour extends JFrame{

	public ConnectFour() {
		initUI();
	}
	
	   private void initUI() {
	        Board board = new Board(); // Create a new board object.
	        add(board); // Add this board to the frame, will be added above the status bar.

	        setSize(1000, 1000); //Set size of tetris game to 200 by 400.
	        setTitle("Connect Four"); // Add a title of Tetris
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cause the program to exit current game but not whole program on close.
	        setLocationRelativeTo(null); // Centres the frame.
	   }
}
