import java.io.FileNotFoundException;

public class Letters {
	public static void main(String[] args) 
	throws FileNotFoundException {
		int letters = 0;
		for(String word: args) {
			letters+=word.length();
		}
		System.out.println(letters);
		
	}
}
