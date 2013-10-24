package de.nordakademie.java.gameoflife.logic.fileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.nordakademie.java.gameoflife.constants.ErrorCodes;

public abstract class FileLoader {

	private static FileReader fileReader;
	private static BufferedReader bufferedReader;
	private static int[][] cells = null;

	public static int readFileAndReturnErrorCode(File file) {
		int errorCode = ErrorCodes.Filechoosing_Was_Aborted;
		if (file != null) {
			try {
				errorCode = FileValidator.validateAndReturnErrorCode(file);
				readFile(file, errorCode);
			} catch (IOException e) {
				e.printStackTrace();
				errorCode = ErrorCodes.Exception_While_File_Readed;
			}
		}
		return errorCode;
	}

	private static void readFile(File file, int errorCode) throws IOException {
		if (errorCode == ErrorCodes.No_Error) {
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
