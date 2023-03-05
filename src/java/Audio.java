package src.java;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Audio implements Runnable {

  private File audio;
  private AudioInputStream audioInput;
  private Clip clip;
  private JButton button;

  Audio(File audio, JButton button) {
    this.audio = audio;
    this.button = button;
    initialize();
  }

  private void initialize() {
    try {

      if (audio.exists()) {
        audioInput = AudioSystem.getAudioInputStream(audio);
        clip = AudioSystem.getClip();
        clip.open(audioInput);

      } else {
        JOptionPane.showMessageDialog(null, "La canción " + audio + " no se ha encontrado.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public long getAudioLengthTime() {
    return clip.getMicrosecondLength();
  }

  @Override
  public void run() {

    clip.start();

    while (clip.getMicrosecondLength() > clip.getMicrosecondPosition()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        System.out.println("Error: " + e);
      }
    }

    clip.stop();
    clip.close();

    ImageIcon playIcon = new ImageIcon("src/resources/img/playButton.png");
    button.setIcon(playIcon);
    button.setEnabled(true);

  }

}
