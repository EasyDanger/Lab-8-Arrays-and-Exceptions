package lab8;

import java.util.Scanner;
//This exercise was kind of awkward for me, because I didn't notice it specifically asks for exceptions until I was mostly done with the code. I relied so heavily on exceptions in my earlier labs, I've been trying to move towards better validation beforehand. I don't even think an IllegalArgumentException is possible in this current code. Let me know if it is.

public class Lab8ArraysInvalidStudentInputs {
	// The exercise is largely about arrays, so I created the arrays first, under
	// the assumption that they would be used in multiple methods for different
	// reasons.
	// Array of student names.
	static String[] students = { "Dick Grayson", "Mark Grayson", "Peter Parker", "Misty Knight", "Doreen Green",
			"Monica Rambeau", "Selina Kyle", "Drake Mallard", "Sakura Kasugano", "Kyo Kusanagi", "Samus Aran",
			"Rock Light", "Dante Sparda", "Dash Parr", "Johnny Storm", "Barry Allen" };
	// Array of student's favorite foods. I realized that an array of their super
	// powers explanations would've taken forever, so I made these up. Well, I made
	// some of them up.
	static String[] foods = { "Popcorn", "Meat, raw", "Aunt May's baking", "American-made Chinese food", "NUTS!",
			"Photons", "Meat", "Veggies", "White rice", "Broiled fish", "Etecoons", "Energy Tanks", "Pizza",
			"Mac & cheese", "Burger King", "Anything, and everything, constantly" };
	// Theoretically, I could make these integers equal to the current year minus
	// the year the characters were introduced. However, while not strictly
	// complicated to do, it would definitely fall outside of the purview of this
	// exercise. Though, it will always bother me a little that this program will
	// soon be out of date.
	static int[] ages = { 78, 15, 56, 43, 27, 36, 78, 27, 22, 24, 32, 31, 17, 14, 57, 62 };
	// This boolean was used multiple times within different loops, until I got
	// tired of moving it and put it here, static. The plan was to use it to call
	// loops in different methods as necessary, but it was only ever necessary to
	// loop this way in the main method.
	static boolean finished = false;
	// Array created by splitting up the names from the students[] array. This was
	// used in multiple methods to allow usage of first names for students.
	static String[] firstName;
	// integer used to access the same index in each array. Same variable keeps the
	// arrays parallel.
	static int indexChoice;
	// A Scanner! This is used in multiple methods, as main[] and studentIntro[]
	// provide two different menus.
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Sky High!");
		// Two do loops are used so that the menu can loop without accessing the start
		// over text every time..
		do {
			// The try catch loop here will catch inputs that exceed the lengths of these
			// arrays.
			try {
				do {
					// User prompt.
					System.out.println(
							"\nWhich numbered student out of our available 16 would you like to learn more about?\nIf you know a student's name, you can enter it here instead of a number.\n(For a list of student names and numbers, type \"names.\")");
					// menuChoice is the main input. It will be used in some way to parse which
					// student's info is accessed.
					String menuChoice = read.nextLine();
					// These options represent the menu. First statement calls the nameList() if
					// desired.
					if (menuChoice.equalsIgnoreCase("names")) {
						nameList();
						// This boolean will force the loop back so the user can choose a student.
						finished = false;
					}
					// This checks whether the name typed by the user can be found in the
					// nameList[]. If it's there, the program proceeds as if the index was entered.
					else if (stringInArrayAt(students, menuChoice) > -1) {
						indexChoice = stringInArrayAt(students, menuChoice) + 1;
						studentIntro(indexChoice);
						finished = true;
					}
					// Statement just straight up accesses the index of the nameList[] array. Will
					// pass this value to subsequent methods.
					else if (menuChoice.matches("\\d+")) {
						indexChoice = Integer.parseInt(menuChoice);
						studentIntro(indexChoice);
						finished = true;
					}
					// Statement validates the user input. If we can't understand it, we loop until
					// we do.
					else {
						System.out.println("We didn't quite get that.");
						finished = false;
					}
					// If the menu needs to loop on here, it does without asking about another
					// student, since no first student has been accessed, yet.
				} while (!finished);
				// Prompts to look up another student.
				System.out.println("Would you like to find out about another student? (\"yes\" to continue)");
				// String only exists for this version of the loop.
				String loop = read.nextLine();
				// May consider writing a method to accept "yes," y,", their capitals, etc.
				if (loop.equalsIgnoreCase("yes")) {
					finished = false;
				} else {
					finished = true;
				}
			}
			// Here's the catch, catching the out of bounds exception.
			catch (ArrayIndexOutOfBoundsException ex) {
				// Checks which way out of bounds the input is and responds appropriately.
				if (indexChoice >= students.length) {
					System.out.println(
							"Thanks for your faith in our school! But there are, unfortunately, only 16 students enrolled here. Please search between 1 and 16.");
				} else {
					System.out.println(
							"Aw, come on! Have a little faith in our school! We've got 16 students to choose from.");
				}
				// Still looping the same way.
				finished = false;
			}
		} while (!finished);
		// When it' sall said and done, we say goodbye.
		System.out.println("Thank you for accessing the Sky High Sudent Database.\nGoodbye.");
	}

//Method where the user chooses what they want to find out.
	private static void studentIntro(int indexChoice) {
		// This split allows us to refer to students uy first name only.
		firstName = students[indexChoice - 1].split("\\s");
		// Prompts user for which information they want.
		System.out.println(
				students[indexChoice - 1] + " is student #" + (indexChoice) + ". What would you like to know about "
						+ firstName[0] + "?\nWe can talk about favorite foods or ages. (Enter \"foods\" or \"ages\")");
		String arrayChoice = read.nextLine();
		// Checks that the input matches one of the given options.
		if (arrayChoice.equalsIgnoreCase("foods")) {
			favFood(indexChoice);
		} else if (arrayChoice.equalsIgnoreCase("ages")) {
			agesOf(indexChoice);
		}
		// If there's no match, we loop, like before.
		else {
			String lostChoice;
			do {
				System.out.println("We didn't quite get that. Do you still want to learn about " + firstName[0]
						+ "? (Type \"same\") \nOr would you like to go back and choose another student? (Type \"new\")");
				lostChoice = read.nextLine();
				// This will loop the user by re-calling this method with the same parameters.
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
			}
			// This is probably how we should loop in these cases. It's cheap, but it's
			// better than treating "n" and every other input as the same, with the only
			// difference being "y" or "yes." Consider this for the future, though it may be
			// more verbose.
			while ((lostChoice.equalsIgnoreCase("same") == false) && (lostChoice.equalsIgnoreCase("new") == false));
		}
	}

//Method simply prints out a message with the students' age and goes back.
	private static void agesOf(int indexChoice) {
		System.out.println(firstName[0] + " is " + ages[indexChoice - 1] + " years old.");
	}

//Method simply prints out a messae with the students' favorite food.
	private static void favFood(int indexChoice) {
		// This sysout needs convert toLowerCase because it's easier than going back and
		// Fixing every entry in foods[].
		System.out.println(firstName[0] + " likes to eat " + foods[indexChoice - 1].toLowerCase() + ".");
	}

//Method displays a chart with all the available names and numbers of students.
	private static void nameList() {
		// There's a dash between those two words, top and bottom.
		System.out.println("Student	Student");
		System.out.println("Number	Name");
		int j = 1; // <-- Necessary to display number.
		for (String student : students) {
			System.out.println("#" + j + "	" + student);
			j += 1;// <-- by iterating it here.

			// Testing this is where I realized that this method will automatically loop,
			// because the boolean back in the main method is still false! Happy surprise! I
			// don't have to call anything here. Must remember to verify this is the case in
			// favFood and agesOf, since this was tested first.
		}
	}

//Consider adding this to, at least, our version of Validator class. Method returns the index of a particular given string.	
	public static int stringInArrayAt(String ar[], String str) {
		int index = -1;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i].equals(str)) {
				index = i;
			}
		}
		return index;
	}
}
