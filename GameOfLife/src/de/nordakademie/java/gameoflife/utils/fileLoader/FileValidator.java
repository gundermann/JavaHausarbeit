package de.nordakademie.java.gameoflife.utils.fileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;

public class FileValidator {

	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public void validate(File file) throws IOException,
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

	private boolean notAllLinesEquallyLong(File file) throws IOException {
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

	private boolean fileContainsNotJustZerosAndOnes(File file)
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

	private boolean fileContainsNonASCIICharacter(File file)
			throws IOException {
		boolean fileContainsNonASCIICharacter = false;
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			if (stringContainsAnNoASCIICharacter(line)) {
				bufferedReader.close();
				fileReader.close();
				fileContainsNonASCIICharacter = true;
				break;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		fileReader.close();
		return fileContainsNonASCIICharacter;

	}

	private boolean stringContainsAnNoASCIICharacter(String string) {
		return !string.matches("\\p{ASCII}*");
	}

	private boolean isFileToLarge(File file) {
		return file.length() > 256000;
	}

	private boolean isFileTypeCorrect(File file) {
		boolean isFileTypeCorrent = false;
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1) {
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			isFileTypeCorrent = fileType.equals(".gol");
		}
		return isFileTypeCorrent;
	}

	private boolean stringContainsNotOnlyZerosAndOnes(String string) {
		boolean stringContainsWrongCharacter = false;
		for (char character : string.toCharArray()) {
			if (character != '1' && character != '0') {
				stringContainsWrongCharacter = true;
			}
		}
		return stringContainsWrongCharacter;
	}

}
