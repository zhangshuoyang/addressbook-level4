package seedu.address.commons.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Provides sound for events
 */
public class AudioUtil {
    public static final String SEPARATOR = File.separator;
    public static final String AUDIO_PREFIX = new File("").getAbsolutePath().concat(
            SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR +  "resources" + SEPARATOR + "audio" + SEPARATOR);

    // Helper method for checking if a file exists and can be read
    private static boolean canAccessFile(String filename) {
        File f = new File(filename);
        return f.exists() && f.canRead();
    }

    /**
     * Play a .wav format audio file that is stored in src/main/resources/images folder
     */
    public static void playClip(String clipName) throws MalformedURLException, IllegalArgumentException {
        if (clipName == null || !canAccessFile(AUDIO_PREFIX + clipName)) {
            throw new IllegalArgumentException("Unable to read audio file!");
        }
        AudioClip clip = Applet.newAudioClip(new File(AUDIO_PREFIX + clipName).toURI().toURL());
        clip.play();
    }
}
