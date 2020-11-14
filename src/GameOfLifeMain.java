import java.util.*;
import java.io.*;

public class GameOfLifeMain {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Play game from file? (y/n)");
		String premade = sc.next();
		GameOfLife board = null;
		if (premade.contentEquals("y")) {
			String filename = null;
			File file = null;
			boolean fileFound = false;
			while (!fileFound) {
				System.out.print("Enter name of premade file: ");
				filename = sc.next();
				file = new File(filename);
				fileFound = file.exists();
				if (!fileFound) {
					System.out.println("File not found, try again ");
				}
			}
			Cell[][] loadFile = loadFile(file);
			board = new GameOfLife(loadFile);

		} else {
			System.out.print("Enter size of grid: ");
			int size = sc.nextInt();
			board = new GameOfLife(size);
		}
		int i=0;
		while (board.isActive()) {
			StdDraw.show(0);
			StdDraw.clear();
			StdDraw.text(0, 0, "" + i);
			board.nextState();
			board.draw();
			StdDraw.show(1);
			i++;
		}
		System.out.println("Thanks for playing. Press 0 to exit");
		if (sc.nextInt()==0) {
			System.exit(0);
		}
	}

	public static Cell[][] loadFile(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String line = input.nextLine();
		// This line finds the amount of numbers in a line, assuming only 1 whitespace
		// char between each number
		int size = (line.length() + 1) / 2;
		Cell[][] matrix = new Cell[size][size];

		Scanner lineScan = new Scanner(line);
		for (int j = 0; j < size; j++) {
			matrix[0][j] = new Cell(lineScan.nextInt() == 1, 0);
		}
		for (int i = 1; i < size; i++) {
			lineScan = new Scanner(input.nextLine());
			for (int j = 0; j < size; j++) {
				matrix[i][j] = new Cell(lineScan.nextInt() == 1, 0);
			}

		}
		return matrix;
	}
}
