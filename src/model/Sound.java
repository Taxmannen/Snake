package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound
{
    private Clip song;

    public Sound(String filename) {
    	try {
			song = AudioSystem.getClip();
			File file = new File("assets/" + filename);
			AudioInputStream is = AudioSystem.getAudioInputStream(file);
			song.open(is);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    /* Loops a specific track.
	 */	
    public void playSound() {
    	song.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /* Stops a specific track from playing.
	 */	
    public void stopSound() {

    	song.stop();
    }
    
    /* Plays a specific track one time.
	 */	
    public void playSoundOnce() {
    	song.start();
    }
    
    /* A function that can be used to change the volume of a specific track.
     * @param volume - takes a float of how much you want to lower the volume.
	 */	
    public void changeVolume(float volume) {
    	FloatControl fc = (FloatControl)song.getControl(FloatControl.Type.MASTER_GAIN);
    	fc.setValue(volume);
    }
}