package de.nordakademie.java.gameoflife.constants;

public class ErrorTexts {

	public static String getTextToErrorCode(int errorCode) {
		if (errorCode == ErrorCodes.Wrong_Type) {
			return "Die hochgeladene Datei ist keine .gol";
		}
		if (errorCode == ErrorCodes.File_To_Large) {
			return "Die hochgeladene Datei ist zu groﬂ";
		}
		if (errorCode == ErrorCodes.File_Contains_Non_ASCII_Characters) {
			return "Die hochgeladene Datei besteht nicht nur aus ASCII-Zeichen";
		}
		if (errorCode == ErrorCodes.File_Contains_Wrong_Characters) {
			return "Die hochgeladene Datei enth‰llt nicht nur 1en und 0en";
		}
		if (errorCode == ErrorCodes.Exception_While_File_Readed) {
			return "W‰hrend des Einlesens der Datei ist eine Exception geworfen worden";
		}
		if (errorCode == ErrorCodes.Not_All_Lines_Have_Equal_Length) {
			return "Nicht alle Zeilen in der Datei sind gleich lang";
		}
		return "";
	}

}
