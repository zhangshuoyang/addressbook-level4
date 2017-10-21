package seedu.address.commons.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

/**
 * Utility class for loading audio
 */
class SoundLoader {
    private SoundList soundList;
    private String    filename;

    public SoundLoader(SoundList currentList, String newFilename) {
        soundList = currentList;
        filename  = newFilename;
        addSound();
    }

    /**
     * Add an audio clip to a soundList container
     */
    private void addSound() {
        try {
            String tempKey = filename;
            AudioClip audioClip = Applet.newAudioClip(new File(filename).toURI().toURL());
            soundList.putClip(tempKey, audioClip);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
