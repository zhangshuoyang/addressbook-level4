package seedu.address.commons.util;

import java.applet.AudioClip;
import java.util.Hashtable;

/**
 * Container for audio files
 */
class SoundList extends Hashtable {

    private String currentlyPlaying = new String();

    private boolean isPlaying;
    private AudioClip tempClip;

    public SoundList(int lengthOfSoundList, String[] soundList) {
        super(lengthOfSoundList); //Initialize Hashtable with capacity of 5 entries.
        startLoading(soundList);
        isPlaying = false;
    }

    public void startLoading(String[] filename) {
        for (int i = 0; i < filename.length; i++) {
            new SoundLoader(this, filename[i]);
        }
    }

    public AudioClip getClip(String name) {
        return (AudioClip) get(name);
    }

    public void putClip(String name, AudioClip clip) {
        put(name, clip);
    }

    /**
     * Plays an audio clip (that is supported by Java)
     *
     * @param key object that maps to a particular audio clip in the underlying container
     */
    public void playSound(String key) {
        tempClip = getClip(key);
        if (tempClip != null && this.isPlaying) { /* something currently playing, stop it first */
            stopSound(this.currentlyPlaying);
            playSound(key);
        } else {
            tempClip.play();
            this.currentlyPlaying = key;
            this.isPlaying = true;
        }
    }

    public void miscSound(String key) {
        AudioClip t = getClip(key);
        t.play();
    }

    /**
     * Stops any audio clip that is currently playing
     *
     * @param key object that maps to a particular audio clip in the underlying container
     */
    public void stopSound(String key) {
        tempClip = getClip(key);
        if (this.isPlaying) {
            tempClip.stop();
            this.currentlyPlaying = new String();
            this.isPlaying = false;
        }
    }

    public String getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(String currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
}
