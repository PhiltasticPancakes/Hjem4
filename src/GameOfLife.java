import java.util.Random;

public class GameOfLife {
	private static final int MARGIN = 2;
	private int size;
	private Cell[][] matrix;
	private boolean active = true;
	private boolean torus = false;

	public GameOfLife(int size) {
		this.size = size;
		matrix = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = new Cell();
			}
		}
	}

	public GameOfLife(Cell[][] initialstate) {
		matrix = initialstate;
		size = matrix[0].length;

	}
//	Currently unused
	public boolean isAlive(int x, int y) {
		return matrix[x][y].isAlive();
	}

	private int[] liveNeighbours(int x, int y) {
		int[] alives = { 0, 0 };
		Cell tempCell;
		int tempx;
		int tempy;
		boolean outOfY;
		boolean outOfX;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				outOfY = (y + j < 0 || y + j >= size);
				outOfX = (x + i < 0 || x + i >= size);
				if(torus || !(outOfX || outOfY)) {				
					tempx = (outOfX) ? size - (Math.abs(x + i)) : x + i;
					tempy = (outOfY) ? size - (Math.abs(y + j)) : y + j;
					tempCell = matrix[tempx][tempy];
					alives[tempCell.getTeam()] += !(i == 0 && j == 0) ? (tempCell.isAlive() ? 1 : 0) : 0;
				}
			}

		}
		return alives;
	}

	public void nextState() {
		matrix = getNextState();
	}


	private Cell[][] getNextState() {
		int alives[];
		Cell[][] nextState = new Cell[size][size];
		active=false;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				alives = liveNeighbours(x, y);
				int team = (alives[0] == alives[1]) ? matrix[x][y].getTeam() : (alives[0] > alives[1] ? 0 : 1);
				if (alives[team] == 3) {
					nextState[x][y] = new Cell(true, team);
				} else if (alives[team] > 3 || alives[team] < 2) {
					nextState[x][y] = new Cell(false, matrix[x][y].getTeam());
				} else {
					nextState[x][y] = matrix[x][y];
					if (nextState[x][y].isAlive() && nextState[x][y].getTeam()!=team) {
						nextState[x][y].setTeam(team);
					}

				}
				if(!nextState[x][y].equals(matrix[x][y]) && !active) {
					this.active=true;
				}
			}
		}
		return nextState;
	}

	public void setCell(int x, int y, boolean alive, int team) {
		matrix[x][y].setAlive(alive);
		matrix[x][y].setTeam(team);
	}
	
	public void initialize() {
		StdDraw.setXscale(0 - MARGIN, size + MARGIN);
		StdDraw.setYscale(0 - MARGIN, size + MARGIN);
		double penRadius = Math.pow(size, 2) / (Math.pow(size, 3) + 400);
		StdDraw.setPenRadius(penRadius);
		
		
	}

	public void draw() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j].isAlive()) {
					StdDraw.setPenColor(matrix[i][j].getTeam() == 0 ? StdDraw.BLUE : StdDraw.RED);
					StdDraw.point(j, i);
//					For printing coordinates on the game
//				} else {
//					StdDraw.setPenColor(StdDraw.BLACK);
//					StdDraw.text(j, i, "("+j+","+i+")");
				}
			}
		}
	}
	

	public boolean isActive() {
		return active;
	}
	
	public void toggleTorus(){
		torus=!torus;
	}

}