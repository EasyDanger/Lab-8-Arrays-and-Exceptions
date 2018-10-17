package lab8;

import java.util.Scanner;

public class Lab8ArraysInvalidStudentInputs {
	static String[] students = { "Dick Grayson", "Mark Grayson", "Peter Parker", "Misty Knight", "Doreen Green",
			"Monica Rambeau", "Selina Kyle", "Drake Mallard", "Sakura Kasugano", "Kyo Kusanagi", "Samus Aran",
			"Rock Light", "Dante Sparda", "Dash Parr", "Johnny Storm", "Barry Allen" };
	static String[] foods = { "Popcorn", "Meat, raw", "Aunt May's baking", "American-made Chinese food", "NUTS!",
			"Photons", "Meat", "Veggies", "White rice", "Broiled fish", "Etecoons", "Energy Tanks", "Pizza",
			"Mac & cheese", "Burger King", "Anything, and everything, constantly" };
	static int[] ages = { 78, 15, 56, 43, 27, 36, 78, 27, 22, 24, 32, 31, 17, 14, 57, 62 };
	static boolean finished = false;
	static String[] firstName;
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to Sky High!");
		int indexChoice;
		do {
			do {
			System.out.println(
					"\n\nWhich numbered student would you like to learn more about? (For a list of student names, type \"names.\")");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("names")) {
				nameList();
				finished = false;

			} else if (menuChoice.matches("\\d+")) {
				indexChoice = Integer.parseInt(menuChoice);
				studentIntro(indexChoice);
				finished = true;
			} else {
				System.out.println("We didn't quite get that.");
				finished = false;
			}
		} while (!finished);
			System.out.println("Would you like to find out about another student? (\"yes\" to continue)");
			String loop = read.nextLine();
			if (loop.equalsIgnoreCase("yes")) {
				finished = false;
			}else {
				finished = true;
			}
			
		} while (!finished);
		System.out.println("Thank you for accessing the Sky High Sudent Database.\nGoodbye.");
	}

	private static void studentIntro(int indexChoice) {
		firstName = students[indexChoice - 1].split("\\s");
		System.out.println(
				students[indexChoice - 1] + " is student #" + (indexChoice) + ". What would you like to know about "
						+ firstName[0] + "?\nWe can talk about favorite foods or ages. (Enter \"foods\" or \"ages\")");
		String arrayChoice = read.nextLine();
		if (arrayChoice.equalsIgnoreCase("foods")) {
			favFood(indexChoice);
		} else if (arrayChoice.equalsIgnoreCase("ages")) {
			agesOf(indexChoice);
		} else {
			String lostChoice;
			do {
				System.out.println("We didn't quite get that. Do you still want to learn about " + firstName[0]
						+ "? (Type \"same\") \nOr would you like to go back and choose another student? (Type \"new\")");
				lostChoice = read.nextLine();
				if (lostChoice.equalsIgnoreCase("same")) {
					studentIntro(indexChoice);
				}
				// considered having this statement return to studentIntro method and pass along
				// a new input for indexChoice. But this doesn't allow for user to see list of
				// names if they wish.
				else if (lostChoice.equalsIgnoreCase("new")) {
					main(null);
				} else {
				}
			} while ((lostChoice.equalsIgnoreCase("same") == false) && (lostChoice.equalsIgnoreCase("new") == false));
		}

	}

	private static void agesOf(int indexChoice) {
		System.out.println(firstName[0] + " is " + ages[indexChoice - 1] + " years old.");
	}

	private static void favFood(int indexChoice) {
		//This sysout needs convert toLowerCase because it's easier than going back and fixind every entry in foods[].
		System.out.println(firstName[0] + " likes to eat " + foods[indexChoice - 1].toLowerCase() + ".");
	}

	private static void nameList() {
		System.out.println("Student	Student");
		System.out.println("Number	Name");
		int j = 1;
		for (String student : students) {
			System.out.println("#" + j + "	" + student);
			j += 1;
			// Testing this is where I realized that this method will automatically loop,
			// because the boolean back in the main method is still false! HApy surprise! I
			// don't have to call anything here. Must remember to verify this is the case in
			// favFood and agesOf, since this was written first.
		}
	}
}
