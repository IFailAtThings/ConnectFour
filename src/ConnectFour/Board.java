package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel{
	
	private Counter[] board;
	private int currCounterX;
	private int boardWidth;
	private int boardHeight;
	private Counter currCounter;
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		setFocusable(true); // Allows the user to click on the frame and allows the frame to register key events.
		board = new Counter[boardWidth*boardHeight];
		addKeyListener(new TAdapter());
	}

	private int squareWidth() { // Get the width of each square, allows for easier board size changing.
		return (int) getSize().getWidth() / boardWidth; 
	}
	
	private int squareHeight() { // Get the width of each square, allows for easier board size changing.
		return (int) getSize().getHeight() / boardHeight; 
	}
	
	private Counter getCounterAt(int x, int y) { // Gets the counter at a specified position on the board.
		return board[(y * boardWidth) + x]; 
	}

	private void moveCurrLeft() {
		if(currCounterX > 0){
			currCounterX--;	
		}
	}
	
	private void moveCurrRight() {
		if(currCounterX < boardWidth){
			currCounterX++;
		}
	}
	
	private void placeCounter(Counter currentCounter, int x) {
		for(int i = boardHeight*boardWidth + x;i<=0;i=i-boardWidth){
			if(board[i].getCounterType() == CounterTypes.NoColour){
				board[i+boardWidth].setCounterType(CounterTypes.NoColour);
				board[i].setCounterType(currentCounter.getCounterType());
			}
		}
		swapPlayer();
		currCounterX = (int) ((boardWidth/2)+0.5);
	}
	
	private void swapPlayer() { // Swap the next player.
		if(currCounter.getCounterType() == CounterTypes.RedColour){
			currCounter.setCounterType(CounterTypes.YellowColour);
		}else{
			currCounter.setCounterType(CounterTypes.RedColour);
		}
	}
	
	class TAdapter extends KeyAdapter { // External class for keyListener

		@Override
		public void keyPressed(KeyEvent e) { // on key press

			int keycode = e.getKeyCode(); // Easier to handle.
			switch (keycode) { // less if statements.
			case KeyEvent.VK_LEFT: // if you press the left arrow key then..
				moveCurrLeft();
				break; 

			case KeyEvent.VK_RIGHT: // if you press the right arrow key then..
				moveCurrRight();
				break;

			case KeyEvent.VK_SPACE: // if you press the space key then..
				placeCounter(currCounter, currCounterX);
				break;

			}
		}
	}
}
