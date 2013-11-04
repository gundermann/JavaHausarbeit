package de.nordakademie.java.gameoflife.utils.fileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;

public abstract class FileValidator {

	private static FileReader fileReader;
	private static BufferedReader bufferedReader;

	public static void validate(File file) throws IOException,
			FileReadingErrorException {
		if (isFileToLarge(file)) {
			throw new FileReadingErrorException(
					"Die hochgeladene Datei ist zu groß");
		}
		if (!isFileTypeCorrect(file)) {
			throw new FileReadingErrorException(
					"Die hochgeladene Datei ist keine .gol");
		}
		if (fileContainsNonASCIICharacter(file)) {
			throw new FileReadingErrorException(
					"Die hochgeladene Datei besteht nicht nur aus ASCII-Zeichen");
		}
		if (fileContainsNotJustZerosAndOnes(file)) {
			throw new FileReadingErrorException(
					"Die hochgeladene Datei enthällt nicht nur 1en und 0en");
		}
		if (notAllLinesEquallyLong(file)) {
			throw new FileReadingErrorException(
					"Nicht alle Zeilen in der Datei sind gleich lang");
		}
	}

	private static boolean notAllLinesEquallyLong(File file) throws IOException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		int charactersPerLine = 0;
		if (line != null) {
			charactersPerLine = line.length();
		}
		while (line != null) {
			if (line.length() != charactersPerLine) {
				bufferedReader.close();
				fileReader.close();
				return true;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		fileReader.close();
		return false;
	}

	private static boolean fileContainsNotJustZerosAndOnes(File file)
			throws IOException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			if (stringContainsNotOnlyZerosAndOnes(line)) {
				bufferedReader.close();
				fileReader.close();
				return true;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		fileReader.close();
		return false;
	}

	private static boolean fileContainsNonASCIICharacter(File file)
			throws IOException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			if (stringContainsAnNoASCIICharacter(line)) {
				bufferedReader.close();
				fileReader.close();
				return true;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		fileReader.close();
		return false;

	}

	private static boolean stringContainsAnNoASCIICharacter(String string) {
		return !string.matches("\\p{ASCII}*");
	}

	private static boolean isFileToLarge(File file) {
		return file.length() > 256000;
	}

	private static boolean isFileTypeCorrect(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") == -1) {
			return false;
		}
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		return fileType.equals(".gol");
	}

	private static boolean stringContainsNotOnlyZerosAndOnes(String string) {
		for (char character : string.toCharArray()) {
			if (character != '1' && character != '0') {
				return true;
			}
		}
		return false;
	}

}
