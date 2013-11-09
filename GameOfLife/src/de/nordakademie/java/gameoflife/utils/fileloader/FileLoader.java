package de.nordakademie.java.gameoflife.utils.fileloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;

public class FileLoader {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private int[][] cells = null;

	public void readFile(File file) throws FileReadingErrorException {
		if (file != null) {
			try {

				FileValidator validator = new FileValidator();
				validator.validate(file);
				readCellsFromFile(file);
			} catch (IOException e) {
				throw new FileReadingErrorException(
						"W\u00E4hrend des Einlesens der Datei ist ein Fehler aufgetreten");
			}
		} else {
			throw new FileReadingErrorException("Abbruch");
		}
	}

	private void readCellsFromFile(File file) throws IOException {
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

	private void writeLineInCells(String line, int lineNumber,
			int charactersInLine) {
		for (int currentCharNumber = 0; currentCharNumber < charactersInLine; currentCharNumber++) {
			char currentChar = line.charAt(currentCharNumber);
			cells[lineNumber][currentCharNumber] = Character
					.getNumericValue(currentChar);
		}
	}

	private int countLines(File file) throws IOException {
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

	public int[][] getCells() {
		return cells;
	}
}
