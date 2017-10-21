package seedu.address.commons.events;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;

/**
 * Provides sound for command success and failure
 */
public abstract class AudibleEvent {
    protected String successSound;
    protected String failureSound;
    private String audioPathPrefix = "sounds\\";

    public AudibleEvent(String successSound, String failureSound) throws IOException {
        requireNonNull(successSound);
        requireNonNull(failureSound);

        // check if specified audio files can be accessed
        if (!canAccessFile(successSound) || !canAccessFile(failureSound)) {
            throw new IOException("Audio file cannot be read!");
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
