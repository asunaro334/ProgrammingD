package lifegame;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable{
	static public void main(String[] args){
		SwingUtilities.invokeLater(new Main());
	}
	public void run(){
		
		int cols=6,rows=10;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,300));
		frame.setMinimumSize(new Dimension(300,200));
		
		BoardModel model = new BoardModel(cols,rows);
		base.setLayout(new BorderLayout());
		BoardView view = new BoardView(cols,rows,base);
		base.add(view,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		base.add(buttonPanel,BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		frame.pack();
		frame.setVisible(true);
	}
}
