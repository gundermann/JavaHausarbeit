package util.fileLoader;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;
import de.nordakademie.java.gameoflife.utils.fileLoader.FileValidator;

public class FileValidatorTest {

	private static File testFileGOL;
	private static File testFileJPG;
	private static File testFileNothing;
	private static FileWriter fileWriter;

	@BeforeClass
	public static void setUp() throws Exception {
		testFileGOL = new File("test.gol");
		if (!testFileGOL.exists()) {
			testFileGOL.createNewFile();
		}
		testFileJPG = new File("test.jpg");
		if (!testFileJPG.exists()) {
			testFileJPG.createNewFile();
		}
		testFileNothing = new File("test");
		if (!testFileNothing.exists()) {
			testFileNothing.createNewFile();
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		testFileGOL.delete();
		testFileJPG.delete();
		testFileNothing.delete();
	}

	@Test
	public void validateCorrectFileTest() {
		boolean didntThrowException = true;
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001");
			fileWriter.write(System.getProperty("line.separator"));
			fileWriter.write("0010010");
			fileWriter.flush();
			fileWriter.close();
			FileValidator.validate(testFileGOL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
			e.printStackTrace();
		}
		assertTrue("korrekte Dateien werfen keine Fehler",
				didntThrowException == true);
	}

	@Test
	public void validateFiletypeTest() {
		String errorMessage = "";
		try {
			FileValidator.validate(testFileGOL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			errorMessage = e.getErrorMessage();
			e.printStackTrace();
		}
		assertTrue(".gol-Dateien werfen keine Fehler",
				!errorMessage.equals("Die hochgeladene Datei ist keine .gol"));

		try {
			FileValidator.validate(testFileJPG);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			errorMessage = e.getErrorMessage();
			e.printStackTrace();
		}
		assertTrue("falsche Dateitypen werfen Fehler",
				errorMessage.equals("Die hochgeladene Datei ist keine .gol"));

		try {
			FileValidator.validate(testFileNothing);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			errorMessage = e.getErrorMessage();
			e.printStackTrace();
		}
		assertTrue("ungetypte Dateien werfen Fehler",
				errorMessage.equals("Die hochgeladene Datei ist keine .gol"));
	}

	@Test
	public void validateFilesizeTest() {
		boolean didntThrowException = true;
		try {
			BufferedImage bimage = new BufferedImage(4500, 4500,
					BufferedImage.TYPE_INT_RGB);
			ImageIO.write(bimage, "jpg", testFileJPG);
			FileValidator.validate(testFileJPG);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
			assertTrue("zu groﬂe Dateien werfen Fehler", e.getErrorMessage()
					.equals("Die hochgeladene Datei ist zu groﬂ"));
			e.printStackTrace();
		}
		assertTrue("zu groﬂe Dateien werfen Fehler",
				didntThrowException == false);

	}

	@Test
	public void validateLinelengthTest() {
		boolean didntThrowException = true;
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001");
			fileWriter.write(System.getProperty("line.separator"));
			fileWriter.write("10010011");
			fileWriter.flush();
			fileWriter.close();
			FileValidator.validate(testFileGOL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
			assertTrue(
					"ungleich lange Zeilen werfen Fehler",
					e.getErrorMessage().equals(
							"Nicht alle Zeilen in der Datei sind gleich lang"));
			e.printStackTrace();
		}
		assertTrue("ungleich lange Zeilen werfen Fehler",
				didntThrowException == false);
	}

	@Test
	public void validateCharactersTest() {
		boolean didntThrowException = true;
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001A");
			fileWriter.flush();
			fileWriter.close();
			FileValidator.validate(testFileGOL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
			assertTrue(
					"Zeichen ungleich 0 und 1 werfen Fehler",
					e.getErrorMessage()
							.equals("Die hochgeladene Datei enth‰llt nicht nur 1en und 0en"));
			e.printStackTrace();
		}
		assertTrue("Zeichen ungleich 0 und 1 werfen Fehler",
				didntThrowException == false);
	}

	@Test
	public void validateASCIITest() {
		boolean didntThrowException = true;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							testFileGOL.getName()), "US-ASCII"));
			testFileGOL.delete();
			testFileGOL.createNewFile();
			bufferedWriter.write("0100101");
			bufferedWriter.close();
			bufferedWriter = null;
			FileValidator.validate(testFileGOL);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
		}
		assertTrue("ASCII Zeichen werfen keine Fehler",
				didntThrowException == true);
		try {
			testFileGOL.delete();
			testFileGOL.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							testFileGOL.getName()), "UTF-16"));
			bufferedWriter.write("0100101");
			bufferedWriter.close();
			bufferedWriter = null;
			FileValidator.validate(testFileGOL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileReadingErrorException e) {
			didntThrowException = false;
			assertTrue(
					"Non_ASCII Zeichen werfen Fehler",
					e.getErrorMessage()
							.equals("Die hochgeladene Datei besteht nicht nur aus ASCII-Zeichen"));
			e.printStackTrace();
		}
		assertTrue("Non_ASCII Zeichen werfen Fehler",
				didntThrowException == false);
	}

}
