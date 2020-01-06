package helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * Class Helper represents a static method
 * to taking input values from standard keyboard
 * @author Kok Heng
 * @date 13 November 2019
 */
public class Helper {

	/*
	 * Read keyboard input values
	 * catch IOException if any unexpected operation occur. 
	 * @return the string representation of this input value.
	 */
	public static String readInput(String prompt) {
		BufferedReader br = null;
		String input = null;
		do {
			System.out.print(prompt);
			try {
				br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("Error! Invalid input, try again");
				System.err.println(e.getMessage());
			}
		}while (input == null);
		return input.trim(); // omitted any white space 
	}

	/*
	 * Read keyboard input values, matching any regular expression patterns
	 * catch IOException if any unexpected operation occur. 
	 * @return the string representation of this input value.
	 */
	public static String readInput(String prompt, String regexp) {
		BufferedReader br = null;
		String input = null;
		do{
			System.out.print(prompt);
			try {
				br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("Error! Invalid input, try again.");
				System.err.println(e.getMessage());
			}
		}while (input == null || ! input.matches(regexp));
		return input.trim(); // omitted any white space
	}

	/*
	 * Read keyboard input values, take object as a type
	 * catch IOException if any unexpected operation occur. 
	 * @return the string representation of this input value.
	 */
	public static String readInput(String prompt, Object ...args) {
		List<String> argsList = Arrays.asList(args).stream().map(arg->arg.toString().toLowerCase()).collect(Collectors.toList());
		BufferedReader br = null;
		String input = null;
		do {
			System.out.print(prompt);
			try {
				br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("Error! Invalid input, try again.");
				System.err.println(e.getMessage());
			}
		}while (input == null || ! argsList.contains(input));
		return input.trim(); // omitted any white space
	}
	/*
	 * This method to prompt to return back to menu.
	 */
	public static void backToMenu(){
		readInput("Please Press ENTER key to return to menu.");
	}

	/**
	 * Generates a string representation appointment id,
	 * by a given length of n
	 * @param n the length of appointment id
	 * @return the string representation of this appointment id.
	 */
	public static String generateAppointmentId(int n) {
		String[] characters = {"1","2","3","4","5","6","7","8","9", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"
				,"q","r","s","t","u","v","w","x","y","z"};
		String randomId = "";

		for (int i = 0; i < n; i=i+1) {
			randomId += characters[new Random().nextInt(characters.length-1)];
		}
		return randomId;
	}

}
