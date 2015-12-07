package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;

public class BoardView extends JPanel{
	
	static private int _cols,_rows;
	static private JPanel _base;
	
	public BoardView(int cols,int rows,JPanel base){
		_cols = cols;
		_rows = rows;
		_base = base;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		int side = this.getWidth()/_cols < this.getHeight()/_rows?
				this.getWidth()/_cols : this.getHeight()/_rows;
		
		if(this.getWidth()/_cols < this.getHeight()/_rows){
			for(int i = 0;i < _cols+1;i++){ //縦線
				g.drawLine(side*i/_cols, this.getHeight()/2-side*_rows/2, side*i/_cols,this.getHeight()/2+side*_rows/2);
			}
			for(int i = 0;i < _rows+1;i++){//横線
				g.drawLine(0,side*i/_rows + this.getHeight()/2-side*_rows/2,this.getWidth(),side*i/_rows + this.getHeight()/2-side*_rows/2);
			}
		}else{		
			for(int i = 0;i < _cols+1;i++){ //縦線
				g.drawLine(side*i/_cols + this.getWidth()/2-side*_cols/2, 0, side*i/_cols + this.getWidth()/2-side*_cols/2,this.getHeight());
			}
			for(int i = 0;i < _rows+1;i++){//横線
				g.drawLine(this.getWidth()/2-side*_cols/2,side*i/_rows,this.getWidth()/2+side*_cols/2,side*i/_rows);
			}
		}
	}
}
