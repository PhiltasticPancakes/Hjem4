import java.util.Random;

public class GameOfLife {
	private static final int MARGIN = 2;
	private int size;
	private Cell[][] matrix;

	public GameOfLife(int n) {
		this.size = n;
		this.matrix = new Cell[n][n];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j]=new Cell();
			}
		}
	}

	public GameOfLife(Cell[][] initialstate) {
		this.matrix = initialstate;
		this.size = matrix[0].length;

	}
	// might be redundant
	public boolean isAlive(int x, int y) {
		return matrix[x][y].isAlive();
	}

	private int[] liveNeighbours(int x, int y) {
		int[] alives = {0,0};
		Cell tempCell;
		int tempx;
		int tempy;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				tempx = (x+i<0 || x+i>=size)? size-(Math.abs(x+i)) : x+i;
				tempy = (y+j<0 || y+j>=size)? size-(Math.abs(y+j)) : y+j;
				tempCell = matrix[tempx][tempy];
				alives[tempCell.getTeam()]+=!(i==0 && j==0)? (tempCell.isAlive()? 1 : 0) : 0;
				} 
			
			}
		return alives;
	}



	public void nextState() {
		int alives[];
		Cell[][] nextState = new Cell[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				alives = liveNeighbours(x, y);
				int team = (alives[0]==alives[1])? matrix[x][y].getTeam() : (alives[0]>alives[1]? 0 : 1);
				if (alives[team] == 3) {
					nextState[x][y] = new Cell(true, (team==0)? 0 : 1);
				} else if (alives[team] > 3 || alives[team] < 2) {
					nextState[x][y] = new Cell(false, matrix[x][y].getTeam());
				} else {
					nextState[x][y] = matrix[x][y];
					if(nextState[x][y].isAlive()) {
						nextState[x][y].setTeam(team);
					}
					
				}
			}
		}
		matrix = nextState;
	}

	public void setCell(int x, int y, boolean alive, int team) {
		matrix[x][y].setAlive(alive);
		matrix[x][y].setTeam(team);
	}
	
	

	public void draw() {
		StdDraw.setXscale(0 - MARGIN, size + MARGIN);
		StdDraw.setYscale(0 - MARGIN, size + MARGIN);
		double penRadius = Math.pow(size, 2)/(Math.pow(size, 3)+400);
		StdDraw.setPenRadius(penRadius);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j].isAlive()) {
					StdDraw.setPenColor(matrix[i][j].getTeam()==0? StdDraw.BLUE : StdDraw.RED);
					StdDraw.point(j, i);
				}
			}
		}
	}

}