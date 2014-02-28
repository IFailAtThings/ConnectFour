package ConnectFour;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel ConnectFourTitle = new JLabel("Connect Four");
		frame.getContentPane().add(ConnectFourTitle, BorderLayout.NORTH);
		JButton startButton = new JButton();
		startButton.setText("Start Game");
		frame.add(startButton);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectFour newGame = new ConnectFour();
				newGame.setVisible(true);
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
