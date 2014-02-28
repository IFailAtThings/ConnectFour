package ConnectFour;

import java.awt.Color;
import java.awt.Graphics;
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
		boardHeight = 6;
		boardWidth = 7;
		setFocusable(true); // Allows the user to click on the frame and allows the frame to register key events.
		board = new Counter[boardWidth*boardHeight];
		addKeyListener(new TAdapter());
		for(int i = 0; i<boardWidth*boardHeight;i++){
			board[i] = new Counter(CounterTypes.NoColour);
		}
		currCounter = new Counter(CounterTypes.RedColour);
	}

	private int squareWidth() { // Get the width of each square, allows for easier board size changing.
		return (int) getSize().getWidth() / boardWidth; 
	}
	
	private int squareHeight() { // Get the width of each square, allows for easier board size changing.
		return (int) (getSize().getHeight() - 20) / boardHeight; 
	}
	
	private Counter getCounterAt(int x, int y) { // Gets the counter at a specified position on the board.
		return board[(y * boardWidth) + x]; 
	}

	@Override
	public void paintComponent(Graphics g) { // Automatically called, use this to paint.
		super.paintComponent(g);
		for(int i = 0; i<boardWidth;i++){
			for(int k = 0; k<boardHeight;k++){
				if(getCounterAt(i, k).getCounterType() == CounterTypes.RedColour){
					g.setColor(Color.red);
					g.fillRect(i*squareWidth(), (int) ((getSize().getHeight() - 20) - k*squareHeight()), squareWidth(), squareHeight());
				}else{
					g.setColor(Color.red);
					if(getCounterAt(i, k).getCounterType() == CounterTypes.YellowColour){
						g.fillRect(i*squareWidth(), (int) ((getSize().getHeight() - 20) - k*squareHeight()), squareWidth(), squareHeight());
					}
				}
			}
			
		}
		if(currCounter.getCounterType() == CounterTypes.RedColour){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.yellow);
		}
		g.fillRect(currCounterX*squareWidth(), 0, squareWidth(), 20);
	}
	
	private void moveCurrLeft() {
		if(currCounterX > 0){
			currCounterX--;	
		}
		repaint();
	}
	
	private void moveCurrRight() {
		if(currCounterX < boardWidth){
			currCounterX++;
		}
		repaint();
	}
	
	private void placeCounter(Counter currentCounter, int x) {
		for(int i = 0;i > boardHeight;i++){
			if(getCounterAt(x, i).getCounterType() == CounterTypes.NoColour){
				board[(i*boardWidth) + x].setCounterType(currentCounter.getCounterType());
			}
		}
		swapPlayer();
		currCounterX = (int) ((boardWidth/2)+0.5);
		repaint();
	}
	
	private void swapPlayer() { // Swap the next player.
		if(currCounter.getCounterType() == CounterTypes.RedColour){
			currCounter.setCounterType(CounterTypes.YellowColour);
		}else{
			currCounter.setCounterType(CounterTypes.RedColour);
		}
		repaint();
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
