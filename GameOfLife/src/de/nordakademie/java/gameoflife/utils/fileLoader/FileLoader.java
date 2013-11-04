package de.nordakademie.java.gameoflife.utils.fileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;

public abstract class FileLoader {

	private static FileReader fileReader;
	private static BufferedReader bufferedReader;
	private static int[][] cells = null;

	public static void readFile(File file) throws FileReadingErrorException {
		if (file != null) {
			try {
				FileValidator.validate(file);
				readCellsFromFile(file);
			} catch (IOException e) {
				throw new FileReadingErrorException(
						"Während des Einlesens der Datei ist eine Exception geworfen worden");
			}
		} else {
			throw new FileReadingErrorException("Es wurde kein File ausgewählt");
		}
	}

	private static void readCellsFromFile(File file) throws IOException {
		int lineNumber = countLines(file);
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		int charactersPerLine = line.length();
		cells = new int[lineNumber][charactersPerLine];
		for (int currentLineNumber = 0; currentLineNumber < lineNumber; currentLineNumber++) {
			writeLineInCells(line, currentLineNumber, charactersPerLine);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}

	private static void writeLineInCells(String line, int lineNumber,
			int charactersInLine) {
		for (int currentCharNumber = 0; currentCharNumber < charactersInLine; currentCharNumber++) {
			char currentChar = line.charAt(currentCharNumber);
			cells[lineNumber][currentCharNumber] = Character
					.getNumericValue(currentChar);
		}
	}

	private static int countLines(File file) throws IOException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		int lineNumber = 0;
		while (line != null) {
			lineNumber++;
			line = bufferedReader.readLine();
		}
		return lineNumber;
	}

	public static int[][] getCells() {
		return cells;
	}
}
