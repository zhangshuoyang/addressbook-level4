package seedu.address.commons.events;

import java.io.File;

/**
 * Provides sound for events
 */
public class AudibleEvent {
    public static final String AUDIO_PREFIX = "sounds\\";

    protected String successSound;
    protected String failureSound;

    public AudibleEvent(String successSound, String failureSound) throws IllegalArgumentException {
        // check if specified audio files can be accessed
        if (successSound != null && !canAccessFile(successSound)) {
            throw new IllegalArgumentException("Success audio file cannot be read!");
        }
        if (failureSound != null && !canAccessFile(failureSound)) {
            throw new IllegalArgumentException("Failure audio file cannot be read!");
        }
        this.successSound = successSound;
        this.failureSound = failureSound;
    }

    // Helper method for checking if a file exists and can be read
    private boolean canAccessFile(String filename) {
        File f = new File(filename);
        return f.exists() && f.canRead();
    }

    public String getSuccessSound() {
        return successSound;
    }

    public String getFailureSound() {
        return failureSound;
    }
}
