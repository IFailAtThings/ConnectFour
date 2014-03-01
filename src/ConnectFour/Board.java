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
	private boolean gameOver;
	private int boardTopGap;

	public Board() {
		initBoard();
	}

	private void initBoard() {
		gameOver = false;
		boardTopGap = (int) ((getSize().getHeight() / 11) + 0.5);
		boardHeight = 10;
		boardWidth = 10;
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
		if((y*boardWidth)+x < 0 || board.length - 1 < (y*boardWidth) + x){
			return null;
		}
		return board[(y * boardWidth) + x]; 
	}

	@Override
	public void paintComponent(Graphics g) { // Automatically called, use this to paint.
		boardTopGap = (int) ((getSize().getHeight() / 11) + 0.5);
		super.paintComponent(g);
		if(!gameOver){
			for(int i = 0; i<boardWidth;i++){
				for(int k = 0; k<boardHeight;k++){
					if(getCounterAt(i, k).getCounterType() == CounterTypes.RedColour){
						g.setColor(Color.red);
						int x = ((int) (getSize().getWidth()/boardWidth + 0.5))*(i);
						int y = (int) ((getSize().getHeight() - ((getSize().getHeight() - boardTopGap + 0.5)/boardHeight)*(k+1)) + 0.5);
						int width = (int) (getSize().getWidth()/(2*boardWidth)+0.5);
						int height = (int) ((getSize().getHeight() - boardTopGap)/(2*boardHeight)+0.5);
						g.fillOval(x, y, width, height);
					}else{
						if(getCounterAt(i, k).getCounterType() == CounterTypes.YellowColour){
							g.setColor(Color.orange);
							int x = ((int) (getSize().getWidth()/boardWidth + 0.5))*(i);
							int y = (int) ((getSize().getHeight() - ((getSize().getHeight() - boardTopGap + 0.5)/boardHeight)*(k+1)) + 0.5);
							int width = (int) (getSize().getWidth()/(2*boardWidth)+0.5);
							int height = (int) ((getSize().getHeight() - 20)/(2*boardHeight)+0.5);
							g.fillOval(x, y, width, height);
						}
					}
				}

			}
			int xCur = ((int) (getSize().getWidth()/boardWidth + 0.5))*(currCounterX);
			int yCur = boardTopGap/2;
			int widthCur = (int) (getSize().getWidth()/(2*boardWidth)+0.5);
			int heightCur = boardTopGap/2;
			if(currCounter.getCounterType() == CounterTypes.RedColour){
				g.setColor(Color.red);
			}else{
				g.setColor(Color.orange);
			}
			g.fillOval(xCur, yCur, widthCur, heightCur);
		}else{
			if(checkWin() == CounterTypes.RedColour){
				g.setColor(Color.black);
				g.drawString("RED TEAM WINS", (int) getSize().getWidth()/2-60, (int) getSize().getHeight()/2);
			}else{
				g.setColor(Color.black);
				g.drawString("ORANGE TEAM WINS", (int) getSize().getWidth()/2-60, (int) getSize().getHeight()/2);
			}
		}
	}

	private void moveCurrLeft() {
		if(currCounterX > 0){
			currCounterX--;	
		}
		repaint();
	}

	private CounterTypes checkWin(){
		for (int i = 0; i < boardWidth; i ++) {
			for (int j = 0; j < boardHeight; j ++) {
				if(getCounterAt(i, j).getCounterType() == CounterTypes.RedColour){
					if(getCounterAt(i, j-1) != null && getCounterAt(i, j-1).getCounterType() == CounterTypes.RedColour){
						if(getCounterAt(i, j-2) != null && getCounterAt(i, j-2).getCounterType() == CounterTypes.RedColour){
							if(getCounterAt(i, j-3) != null && getCounterAt(i, j-3).getCounterType() == CounterTypes.RedColour){
								return CounterTypes.RedColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.RedColour){
					if(getCounterAt(i-1, j) != null && getCounterAt(i-1, j).getCounterType() == CounterTypes.RedColour){
						if(getCounterAt(i-2, j) != null && getCounterAt(i-2, j).getCounterType() == CounterTypes.RedColour){
							if(getCounterAt(i-3, j) != null && getCounterAt(i-3, j).getCounterType() == CounterTypes.RedColour){
								return CounterTypes.RedColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.RedColour){
					if(getCounterAt(i-1, j-1) != null && getCounterAt(i-1, j-1).getCounterType() == CounterTypes.RedColour){
						if(getCounterAt(i-2, j-2) != null && getCounterAt(i-2, j-2).getCounterType() == CounterTypes.RedColour){
							if(getCounterAt(i-3, j-3) != null && getCounterAt(i-3, j-3).getCounterType() == CounterTypes.RedColour){
								return CounterTypes.RedColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.RedColour){
					if(getCounterAt(i-1, j+1) != null && getCounterAt(i-1, j+1).getCounterType() == CounterTypes.RedColour){
						if(getCounterAt(i-2, j+2) != null && getCounterAt(i-2, j+2).getCounterType() == CounterTypes.RedColour){
							if(getCounterAt(i-3, j+3) != null && getCounterAt(i-3, j+3).getCounterType() == CounterTypes.RedColour){
								return CounterTypes.RedColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.YellowColour){
					if(getCounterAt(i, j-1) != null && getCounterAt(i, j-1).getCounterType() == CounterTypes.YellowColour){
						if(getCounterAt(i, j-2) != null && getCounterAt(i, j-2).getCounterType() == CounterTypes.YellowColour){
							if(getCounterAt(i, j-3) != null && getCounterAt(i, j-3).getCounterType() == CounterTypes.YellowColour){
								return CounterTypes.YellowColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.YellowColour){
					if(getCounterAt(i-1, j) != null && getCounterAt(i-1, j).getCounterType() == CounterTypes.YellowColour){
						if(getCounterAt(i-2, j) != null && getCounterAt(i-2, j).getCounterType() == CounterTypes.YellowColour){
							if(getCounterAt(i-3, j) != null && getCounterAt(i-3, j).getCounterType() == CounterTypes.YellowColour){
								return CounterTypes.YellowColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.YellowColour){
					if(getCounterAt(i-1, j-1) != null && getCounterAt(i-1, j-1).getCounterType() == CounterTypes.YellowColour){
						if(getCounterAt(i-2, j-2) != null && getCounterAt(i-2, j-2).getCounterType() == CounterTypes.YellowColour){
							if(getCounterAt(i-3, j-3) != null && getCounterAt(i-3, j-3).getCounterType() == CounterTypes.YellowColour){
								return CounterTypes.YellowColour;
							}
						}
					}
				}
				if(getCounterAt(i, j) != null && getCounterAt(i, j).getCounterType() == CounterTypes.YellowColour){
					if(getCounterAt(i-1, j+1) != null && getCounterAt(i-1, j+1).getCounterType() == CounterTypes.YellowColour){
						if(getCounterAt(i-2, j+2) != null && getCounterAt(i-2, j+2).getCounterType() == CounterTypes.YellowColour){
							if(getCounterAt(i-3, j+3) != null && getCounterAt(i-3, j+3).getCounterType() == CounterTypes.YellowColour){
								return CounterTypes.YellowColour;
							}
						}
					}
				}

			}
		}
		return null;
	}

	private void moveCurrRight() {
		if(currCounterX < boardWidth - 1){
			currCounterX++;
		}
		repaint();
	}

	private void placeCounter(Counter currentCounter, int x) {
		loop:
			for(int i = 0;i < boardHeight;i++){
				if(getCounterAt(x, i).getCounterType() == CounterTypes.NoColour){
					board[(i*boardWidth) + x].setCounterType(currentCounter.getCounterType());
					break loop;
				}
			}
	swapPlayer();
	currCounterX = (int) ((boardWidth/2)+0.5);
	if(checkWin() == CounterTypes.RedColour){
		gameOver = true;
	}
	if(checkWin() == CounterTypes.YellowColour){
		gameOver = true;
	}
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

			if(gameOver){
				return;
			}

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
