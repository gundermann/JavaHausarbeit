package logic.fileLoader;

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

import de.nordakademie.java.gameoflife.constants.ErrorCodes;
import de.nordakademie.java.gameoflife.logic.fileLoader.FileValidator;

public class FileValidatorTest {

	private static File testFileGOL;
	private static File testFileJPG;
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
	}

	@AfterClass
	public static void tearDown() throws Exception {
		testFileGOL.delete();
		testFileJPG.delete();
	}

	@Test
	public void validateCorrectFileTest() {
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001");
			fileWriter.write(System.getProperty("line.separator"));
			fileWriter.write("0010010");
			fileWriter.flush();
			fileWriter.close();
			assertTrue(
					"korrekte Dateien werfen keine Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) == ErrorCodes.No_Error);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateFiletypeTest() {
		try {
			assertTrue(
					".gol-Dateien werfen keine Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) != ErrorCodes.Wrong_Type);
			assertTrue(
					"falsche Dateitypen werfen Fehler",
					FileValidator.validateAndReturnErrorCode(testFileJPG) == ErrorCodes.Wrong_Type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateFilesizeTest() {
		try {
			BufferedImage bimage = new BufferedImage(4500, 4500,
					BufferedImage.TYPE_INT_RGB);
			ImageIO.write(bimage, "jpg", testFileJPG);
			assertTrue(
					"zu roﬂe Dateien werfen Fehler",
					FileValidator.validateAndReturnErrorCode(testFileJPG) == ErrorCodes.File_To_Large);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void validateLinelengthTest() {
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001");
			fileWriter.write(System.getProperty("line.separator"));
			fileWriter.write("10010011");
			fileWriter.flush();
			fileWriter.close();
			assertTrue(
					"ungleich lange Zeilen werfen Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) == ErrorCodes.Not_All_Lines_Have_Equal_Length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateCharactersTest() {
		try {
			fileWriter = new FileWriter(testFileGOL);
			fileWriter.write("1001001A");
			fileWriter.flush();
			fileWriter.close();
			assertTrue(
					"Zeichen ungleich 0 und 1 werfen Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) == ErrorCodes.File_Contains_Wrong_Characters);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateASCIITest() {
		try {
			testFileGOL.delete();
			testFileGOL.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							testFileGOL.getName()), "US-ASCII"));
			bufferedWriter.write("0100101");
			bufferedWriter.close();
			bufferedWriter = null;
			assertTrue(
					"ASCII Zeichen werfen keine Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) != ErrorCodes.File_Contains_Non_ASCII_Characters);
			testFileGOL.delete();
			testFileGOL.createNewFile();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(testFileGOL.getName()), "UTF-16"));
			bufferedWriter.write("0100101");
			bufferedWriter.close();
			bufferedWriter = null;
			assertTrue(
					"Non_ASCII Zeichen werfen Fehler",
					FileValidator.validateAndReturnErrorCode(testFileGOL) == ErrorCodes.File_Contains_Non_ASCII_Characters);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
