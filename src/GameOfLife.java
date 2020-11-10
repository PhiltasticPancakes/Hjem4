import java.util.Random;

public class GameOfLife {
	private static final int MARGIN = 2;
	private int size;
	private int[][] matrix;

	public GameOfLife(int n) {
		Random rand = new Random();
		this.size = n;
		this.matrix = new int[n][n];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				setCell(i, j, rand.nextBoolean());
			}
		}
	}

	public GameOfLife(int[][] initialstate) {
		this.matrix = initialstate;
		this.size = matrix[0].length;

	}

	public boolean isAlive(int x, int y) {
		return matrix[x][y] == 1;
	}

	private int liveNeighbours(int x, int y) {
		int alives = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				alives += (x + i >= 0 && x + i < size && y + j >= 0 && y + j < size && !(i == 0 && j == 0))
						? matrix[x + i][y + j]
						: 0;
			}
		}

		return alives;
	}

	public void nextState() {
		int alives;
		int[][] nextState = new int[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				alives = liveNeighbours(x, y);
				// System.out.println("alives at: " + i + "," + j + ": " + alives);
				if (alives == 3) {
					nextState[x][y] = 1;
				} else if (alives > 3 || alives < 2) {
					nextState[x][y] = 0;
				} else {
					nextState[x][y] = matrix[x][y];
				}
			}
		}
		matrix = nextState;
	}

	public void setCell(int x, int y, boolean alive) {
		matrix[x][y] = (alive) ? 1 : 0;
	}

	public void draw() {
		StdDraw.setXscale(0 - MARGIN, size + MARGIN);
		StdDraw.setYscale(0 - MARGIN, size + MARGIN);
		double penRadius = Math.pow(size, 2)/(Math.pow(size, 3)+400);
		StdDraw.setPenRadius(penRadius);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j] == 1) {
					StdDraw.point(j, i);
				}
			}
		}
	}

}