package de.nordakademie.java.gameoflife.exceptions;

public class FileReadingErrorException extends Exception {

	private final String errorMessage;

	public FileReadingErrorException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
