package logic.fileLoader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.nordakademie.java.gameoflife.constants.ErrorCodes;
import de.nordakademie.java.gameoflife.logic.fileLoader.FileLoader;

public class FileLoaderTest {

	private static File testFileGOL;

	@BeforeClass
	public static void setUp() throws Exception {
		testFileGOL = new File("test.gol");
		if (!testFileGOL.exists()) {
			testFileGOL.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(testFileGOL);
		fileWriter.write("1001001");
		fileWriter.write(System.getProperty("line.separator"));
		fileWriter.write("0010010");
		fileWriter.flush();
		fileWriter.close();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		testFileGOL.delete();
	}

	@Test
	public void testGettingCellsWithoutReadingFile() {
		assertTrue("cells sind null initialisiert",
				FileLoader.getCells() == null);
	}

	@Test
	public void testNullFileReading() {
		assertTrue(
				"Abbruch der Fileauswahl gibt richtigen ErrorCode zurück",
				FileLoader.readFileAndReturnErrorCode(null) == ErrorCodes.Filechoosing_Was_Aborted);
		assertTrue("cells sind null nach fehlerhaftem Einlesen",
				FileLoader.getCells() == null);
	}

	@Test
	public void testCorrectFileReading() {
		assertTrue(FileLoader.readFileAndReturnErrorCode(testFileGOL) == ErrorCodes.No_Error);
		assertTrue("cells sind nicht null nach korrektem Einlesen",
				FileLoader.getCells() != null);
	}
}
