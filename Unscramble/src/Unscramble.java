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
		String scrambled = scrambleWord(parentWord);
		ArrayList<String> guessed = new ArrayList<String>();
		int score = 0;
		Scanner k = new Scanner(System.in);
		
		while (true)
		{
			System.out.println("Scrambled word: " + scrambled + " Score: " + score);
			System.out.println("Input unscrambled word or \"0\" to quit:");
			String input = k.nextLine();
			if (input.equals("0"))
			{
				break;
			}
			else if (guessed.contains(input))
			{
				System.out.println("Already guessed!");
			}
			else
			{
				int wordScore = scoreWord(input, parentWord, words);
				if (wordScore == 0)
				{
					System.out.println("Invalid word!");
				}
				
				else
				{
					System.out.println("Word scored " + wordScore);
					guessed.add(input);
				}
				score += wordScore;
			}
		}
		System.out.println("Final score: " + score);
		System.out.println("9 letter word was " + parentWord);
		k.close();
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
	
	public static String scrambleWord(String word)
	{
		ArrayList<String> letters = new ArrayList<String>();
		String scrambled = "";
		for (int i = 0; i < word.length(); i++)
		{
			letters.add(word.substring(i, i + 1));
		}
		while (letters.size() > 0)
		{
			int rand = (int)(Math.random() * letters.size());
			scrambled += letters.remove(rand);
		}
		return scrambled;
	}
	
	public static int scoreWord(String word, String parent, ArrayList<String> allWords)
	{
		word = word.toLowerCase();
		if (allWords.contains(word) && checkWord(word, parent))
		{
			return word.length();
		}
		else
		{
			return 0;
		}
	}
	
	public static boolean checkWord(String word, String parent)
	{
		if (word.length() > parent.length())
		{
			return false;
		}
		for (int i = 0; i < word.length(); i++)
		{
			if (parent.indexOf(word.charAt(i)) != -1)
			{
				parent = parent.substring(0, parent.indexOf(word.charAt(i))) + parent.substring(parent.indexOf(word.charAt(i)) + 1, parent.length());
			}
			else
			{
				return false;
			}
		}
		return true;
	}
}
