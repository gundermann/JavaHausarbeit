package de.nordakademie.java.gameoflife.exceptions;

/*
 * Eine Exception dieser Art wird geworfen, wenn es zu einem Fehler beim hochladen kommt.
 * Die Fehlermeldung wird beim Aufruf direkt übergeben.
 * 
 * @autor Christian Leppelt
 */

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
