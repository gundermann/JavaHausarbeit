package util.fileLoader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.nordakademie.java.gameoflife.exceptions.FileReadingErrorException;
import de.nordakademie.java.gameoflife.utils.fileLoader.FileLoader;

public class FileLoaderTest {

	private static File testFileGOL;
	private static File testFileNotExisting;
	private FileLoader fileLoader;

	@BeforeClass
	public static void setUpBefore() throws Exception {
		testFileGOL = new File("test.gol");
		if (!testFileGOL.exists()) {
			testFileGOL.createNewFile();
		}
		testFileNotExisting = new File("test2.gol");
		FileWriter fileWriter = new FileWriter(testFileGOL);
		fileWriter.write("1001001");
		fileWriter.write(System.getProperty("line.separator"));
		fileWriter.write("0010010");
		fileWriter.flush();
		fileWriter.close();
	}
	
	@Before
	public void setUp(){
		fileLoader = new FileLoader();
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		testFileGOL.delete();
	}

	@Test
	public void testGettingCellsWithoutReadingFile() {
		assertTrue("cells sind null initialisiert",
				fileLoader.getCells() == null);
	}

	@Test
	public void testNullFileReading() {
		String errorMessage = "";
		try {
			fileLoader.readFile(null);
		} catch (FileReadingErrorException e) {
			errorMessage = e.getErrorMessage();
		}
		assertTrue("Abbruch der Fileauswahl gibt richtigen ErrorCode zurï¿½ck",
				errorMessage.equals("Es wurde kein File ausgewählt"));
		assertTrue("cells sind null nach fehlerhaftem Einlesen",
				fileLoader.getCells() == null);
	}

	@Test
	public void testExceptionWhileFileReading() {
		String errorMessage = "";
		try {
			fileLoader.readFile(testFileNotExisting);
		} catch (FileReadingErrorException e) {
			errorMessage = e.getErrorMessage();
		}
		assertTrue(
				"Bei Exception fliegt richtiger ErrorCode",
				errorMessage
						.equals("Während des Einlesens der Datei ist eine Exception geworfen worden"));
	}

	@Test
	public void testCorrectFileReading() {
		boolean didntThrowException = true;
		try {
			fileLoader.readFile(testFileGOL);
		} catch (FileReadingErrorException e) {
			e.printStackTrace();
			didntThrowException = false;
		}
		assertTrue(didntThrowException == true);
		assertTrue("cells sind nicht null nach korrektem Einlesen",
				fileLoader.getCells() != null);
	}
}
