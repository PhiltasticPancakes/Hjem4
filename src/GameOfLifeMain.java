import java.util.*;
import java.io.*;
public class GameOfLifeMain {
	public static void main(String[] args)
	throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter size of grid: ");
		int size = sc.nextInt();
		GameOfLife board = new GameOfLife(size);
		for(int i=0; i<100; i++) {
			StdDraw.clear();
			StdDraw.text(0, 0, ""+i);
			board.nextState();
			board.draw();
			StdDraw.show(500);
		}


	}
	public int[][] loadFile(String fileName) {
		Scanner input;
		while(true) {
			try {
				input = new Scanner(new File(fileName));
				break;
			} catch (Exception e) {
				System.out.println("File not found");
			}
		}
		Scanner linjeScan;
		String line = input.nextLine();
		//This line finds the amounth of numbers in a line, assuming only 1 whitepspace char between each number
		int size=(line.length()+1)/2;
		int[][] matrix = new int[size][size];
		
		Scanner lineScan = new Scanner(line);
		for(int j = 0; j<size; j++) {
			matrix[0][j]=lineScan.nextInt();
		}
		for(int i = 1; i<size;i++) {
			lineScan=new Scanner(input.nextLine());
			for(int j = 0; j<size; j++) {
				matrix[i][j]=lineScan.nextInt();
			}

		}
		return matrix;

		
	}

}
