import java.util.*;
import java.io.*;

public class GameOfLifeMain {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		GameOfLife board=null;
		int choice=0;
		while(choice!=1 && choice !=2) {
			System.out.println("Enter number corresponding to action: \n"
					+ "1 to load initial state from file \n"
					+ "2 to generate a random initial state with given size \n");
			try {
				choice=sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("invalid input, try again");
				sc.nextLine();
				continue;
			}
			System.out.println((choice==1 || choice== 2)? "" : "Input not valid, try again");
		}
		
		if(choice==1) {
			File file;
			while(board==null) {
				System.out.print("Enter name of premade file: ");

				try {
					file = new File(sc.next());
					System.out.println();
					board = new GameOfLife(loadFile(file));
					System.out.println("File loaded succesfully!");
				} catch (Exception e) {
					System.out.println("Invalid filename");
				}
				sc.nextLine();
			}
		} else {
			System.out.println("Generating random game \n");
			while(board==null) {
				System.out.print("Enter size of grid as positive integer: ");
				try {
					board = new GameOfLife(sc.nextInt());
					sc.nextLine();
				} catch (Exception e) {
					System.out.println("Invalid input, try again");
					sc.nextLine();
				}	
		}
		choice=0;
		while(choice!=2) {
			choice=0;
			System.out.println("Torus is currently " + (board.hasTorus()? "on" : "off"));
			System.out.println("Enter number corresponding to action: \n"
					+ "1 to toggle torus \n"
					+ "2 to start game \n");
			try {
				choice=sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("invalid input, try again");
				sc.nextLine();
				continue;
			}
			System.out.println((choice==1 || choice== 2)? "" : "Input not valid, try again");
			if(choice==1) {
				board.toggleTorus();
			}
		}
		
		
			
		}
		int i = 0;
		board.initialize();
		while (board.isActive()) {
			StdDraw.show(0);
			StdDraw.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(-1, -1, "" + i);
			board.draw();
			StdDraw.show(40);
			board.nextState();
			i++;
		}
		System.out.println("Thanks for playing. Press 0 to exit");
		if (sc.nextInt() == 0) {
			System.exit(0);
		}
	}

	public static Cell[][] loadFile(File file) throws FileNotFoundException {

		List<String> allCells = new ArrayList<>();

		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			allCells.add(line);
		}

		int size = allCells.size();
		Cell[][] matrix = new Cell[size][size];

		for (int y = 0; y < size; y++) {
			String line = allCells.get(y);
			int x = 0;
			int i = 0;
			while (i < line.length()) {
				char c = line.charAt(i);
				if (c != ' ') {
					matrix[size - y - 1][x] = new Cell(c != '0', 0);
					x++;
				}
				i++;
			}
		}
		return matrix;
	}

}
