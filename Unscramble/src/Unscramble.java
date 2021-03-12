import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Unscramble {

	public static void main(String[] args) throws FileNotFoundException
	{
		//words_alpha.txt is a long list of English words.
		//nine_letters.txt is a list of nine letter English words.
		System.out.println("Initializing...");
		ArrayList<String> words = initList("words_alpha.txt");
		ArrayList<String> nineLetters = initList("nine_letters.txt");
		System.out.println("Done!");
		
		String parentWord = selectRandomWord(nineLetters);
		
	}
	
	public static ArrayList<String> initList(String fileName) throws FileNotFoundException
	{
		File f = new File(fileName);
		Scanner k = new Scanner(f);
		ArrayList<String> words = new ArrayList<>();
		while (k.hasNextLine())
		{
			words.add(k.nextLine());
		}
		k.close();
		return words;
	}
	
	public static String selectRandomWord(ArrayList<String> words)
	{
		int rand = (int)(Math.random() * words.size());
		return words.get(rand);
	}
}
