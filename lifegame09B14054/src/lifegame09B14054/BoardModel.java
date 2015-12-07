package lifegame;

import java.util.*;

public class BoardModel {
	
	private ArrayList<BoardListener> listeners;
	private ArrayList<boolean[][]> genList;
	
	private int _cols;
	private int _rows;
	private int _currentgen;
	private int _lastgen;
	private boolean[][] _cells;
	private int[][] _sorroundings;
	
	public BoardModel(int c,int r){
		
		listeners = new ArrayList<BoardListener>();
		
		_cols = c;
		_rows = r;
		_currentgen =0;
		_lastgen = 0;
		_cells = new boolean[_cols][_rows];
		_sorroundings = new int[_cols][_rows];
		genList = new ArrayList<boolean[][]>();
	}
	
	public int getCols(){
		return _cols;
	}
	public int getRows(){
		return _rows;
	}
	public void changeCellState(int x,int y){
		_cells[x][y] = !_cells[x][y];
		fireUpdate();
	}
	public void printForDebug(){
		for(int i = 0;i<_rows;i++){
			for(int j = 0;j<_cols;j++){
				System.out.print(_cells[j][i]?"*":".");
			}
			System.out.println();
		}
	}
	public void Next(){
		if(_currentgen == _lastgen){
			for(int i= 0;i<_cols;i++){
				for (int j = 0;j<_rows;j++){
					int sorround = 0;				
					if(i!=0      &&j!=0      &&_cells[i-1][j-1]) sorround++;
					if(i!=0      &&            _cells[i-1][j]) sorround++;
					if(i!=0      &&j!=_rows-1&&_cells[i-1][j+1])sorround++;
					if(            j!=_rows-1&&_cells[i][j+1])sorround++;
					if(i!=_cols-1&&j!=_rows-1&&_cells[i+1][j+1])sorround++;
					if(i!=_cols-1&&            _cells[i+1][j])sorround++;
					if(i!=_cols-1&&j!=0      &&_cells[i+1][j-1])sorround++;
					if(            j!=0      &&_cells[i][j-1])sorround++;
					
					_sorroundings[i][j]=sorround;
				}
			}
			boolean[][] nextgen = new boolean[_cols][_rows];
			for(int i= 0;i<_cols;i++){
				for (int j = 0;j<_rows;j++){
					nextgen[i][j] = _cells[i][j]?_sorroundings[i][j]==2||_sorroundings[i][j]==3:_sorroundings[i][j]==3;				
				}
			}
			_cells = nextgen;
			genList.add(nextgen);
			_lastgen++;
			_currentgen++;
		} else if(_currentgen<_lastgen){
			_currentgen++;			
			_cells = genList.get(_currentgen);
		} else {
			System.out.println("generation error");
		}
		fireUpdate();
		System.out.println(_currentgen);
	}
	
	public void Undo(){
		if(_currentgen>0){
			_currentgen--;
			_cells = genList.get(_currentgen);
			fireUpdate();
		} else if(_currentgen==0) {
			System.out.println("this is the first gen");
		}
		System.out.println(_currentgen);
	}
	
	public BoardModel(){
		listeners = new ArrayList<BoardListener>();	
	}
	
	public void addListener(BoardListener listener){
		listeners.add(listener);
	}
	
	private void fireUpdate(){
		for(BoardListener listener: listeners){
			listener.updated(this);
		}
	}
}
