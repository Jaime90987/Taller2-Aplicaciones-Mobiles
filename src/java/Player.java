package src.java;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Player extends JFrame implements ActionListener, Runnable {

  private static JLabel labelTitle, labelImage;
  private static JButton button;
  private static ImageIcon appIcon, playIcon, stopIcon;
  private File audio;

  Player(File audio) {
    this.audio = audio;
    initialize();
  }

  private void initialize() {

    setTitle("SoundSet");
    setSize(430, 640);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((screenSize.getWidth() - getWidth()) / 2) - 300;
    int y = getLocation().y;
    setLocation(x, y);

    appIcon = new ImageIcon("src/resources/img/playerIcon.png");
    setIconImage(appIcon.getImage());

    labelTitle = new JLabel("Player");
    labelTitle.setFont(new Font("Calibri", 3, 25));

    labelImage = new JLabel(appIcon);

    playIcon = new ImageIcon("src/resources/img/playButton.png");
    button = new JButton(playIcon);
    button.setOpaque(false);
    button.setBackground(new Color(0, 0, 0, 0));
    button.setForeground(Color.BLACK);
    button.setBorderPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setFocusable(false);
    button.addActionListener(this);

    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
    getContentPane().setBackground(Color.WHITE);
    getContentPane().add(labelTitle);
    getContentPane().add(labelImage);
    getContentPane().add(button);

  }

  public void actionPerformed(ActionEvent e) {

    stopIcon = new ImageIcon("src/resources/img/sound.gif");
    button.setDisabledIcon(stopIcon);
    button.setEnabled(false);
    UIManager.getLookAndFeelDefaults().put("Button.disabledText", Color.BLACK);

    Audio player = new Audio(audio, button);
    Thread threadAudio = new Thread(player);

    TickTack consolePrint = new TickTack(player.getAudioLengthTime());
    Thread threadConsole = new Thread(consolePrint);

    ApiRequest callApiGUI = new ApiRequest();
    Thread threadCallApi = new Thread(callApiGUI);

    threadAudio.start();
    threadConsole.start();
    threadCallApi.start();

    System.out.println(threadAudio.getName());
    System.out.println(threadConsole.getName());
    System.out.println(threadCallApi.getName());

  }

  @Override
  public void run() {
    setVisible(true);
  }
}
