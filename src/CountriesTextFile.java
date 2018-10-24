import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountriesTextFile {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int userChoice;
		String countryChoice;
		String fileName = "countries.txt";
		String directoryFolder = "resources";

		System.out.println("Wecome to the Countries Maintenance App!\n");

		createDirectory();
		createCountryFiles(directoryFolder, fileName);

		do {

			System.out.println("1.) See the list of countries");
			System.out.println("2.) Add a country");
			System.out.println("3.) Exit");

			userChoice = scan.nextInt();
			scan.nextLine(); //Garbage line

			if (userChoice == 1) {

				readCountries(directoryFolder, fileName);
//				System.out.println("1.) See the list of countries");
//				System.out.println("2.) Add a country");
//				System.out.println("3.) Exit");

//				userChoice = scan.nextInt();
//				scan.nextLine(); // Garbage line

			} else if (userChoice == 2) {

				System.out.println("Enter country: ");
				countryChoice = scan.nextLine();
				CountriesTextFile.writeCountries(countryChoice, directoryFolder, fileName);
//			System.out.println("1.) See the list of countries");
//			System.out.println("2.) Add a country");
//			System.out.println("3.) Exit");
//			
//				userChoice = scan.nextInt();
//				scan.nextLine(); // Garbage line

			} else if (userChoice == 3) {
				System.out.println("Thanks for playing! Bye!");

			} else {
				System.out.println("Please enter a number 1-3!");
			}

		} while (userChoice == 1 || userChoice == 2);
	}

	public static void createDirectory() {

		String dirPath = "resources";

		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {
			try { // This is an example of a checked exception.
				Files.createDirectories(folder);
				System.out.println("Folder was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went wrong with the folder creation.");
			}
		}
	}

	public static void createCountryFiles(String directoryFolder, String fileName) {

		Path filePath = Paths.get(directoryFolder, fileName);

		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("File was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went wrong!");
			}
		} else {
			return;
		}

	}

	public static void readCountries(String directoryFolder, String fileName) {

		Path filePath = Paths.get(directoryFolder, fileName);

		File file = filePath.toFile();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);

				line = reader.readLine(); // Reads the next line
			}

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");

		} catch (IOException e) {
			System.out.println("Contact customer service");
		}

	}

	public static void writeCountries(String country, String directoryFolder, String fileName) {

		Path filePath = Paths.get(directoryFolder, fileName);

		File file = filePath.toFile();

		try {

			// True parameter for the FileOutputStream constructor
			// Appends data to the end of the file
			// False rewrites over the entire file.

			PrintWriter outW = new PrintWriter(new FileOutputStream(file, true));

			outW.println(country);

			outW.close();

		} catch (FileNotFoundException e) {
			System.out.println("File was not found!");
		}

	}

}
