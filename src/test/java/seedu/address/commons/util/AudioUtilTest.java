package seedu.address.commons.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

public class AudioUtilTest {
    private static AudioUtil audioUtility = new AudioUtil();

    //@@author lancehaoh
    @Test
    public void checkFileNotFoundThrowsIoException() {
        boolean exceptionWasThrown = false;
        // try to open a non-existent file
        try {
            audioUtility.playClip("???????.wav");
        } catch (IOException e) {
            exceptionWasThrown = true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        // IO Exception was not thrown, this is a failing case
        if (!exceptionWasThrown) {
            assertTrue(false);
        }
    }

    //@@author lancehaoh
    @Test
    public void checkInvalidFileFormatUnsupportedAudioFileException() {
        boolean exceptionWasThrown = false;
        // attempt to open an invalid file as a audio file
        try {
            audioUtility.playClip("notavalidfile.png");
            assertTrue(false);
        } catch (UnsupportedAudioFileException e) {
            exceptionWasThrown = true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        // IO Exception was not thrown, this is a failing case
        if (!exceptionWasThrown) {
            assertTrue(false);
        }
    }
}
