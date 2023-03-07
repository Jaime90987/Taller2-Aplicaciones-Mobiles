package src.java;

import java.net.URL;
import java.util.Scanner;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class ApiRequest extends JFrame implements Runnable {

  private static JTextArea textAreaApiResponse;
  private static JLabel labelTitle;
  private static JScrollPane scrollPane;
  private static ImageIcon appIcon;

  ApiRequest() {

    setTitle("Api Call");
    setSize(570, 600);
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((screenSize.getWidth() - getWidth()) / 2) + 300;
    int y = getLocation().y;
    setLocation(x, y);

    appIcon = new ImageIcon("src/resources/img/apiIcon.png");
    setIconImage(appIcon.getImage());

    labelTitle = new JLabel("Api Response");
    labelTitle.setFont(new Font("Calibri", 3, 25));
    labelTitle.setBackground(new Color(255, 255, 255));

    textAreaApiResponse = new JTextArea(25, 42);
    textAreaApiResponse.setBackground(new Color(255, 255, 255));
    textAreaApiResponse.setMargin(new Insets(10, 10, 10, 10));
    textAreaApiResponse.setEditable(false);

    scrollPane = new JScrollPane(textAreaApiResponse);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
    getContentPane().setBackground(Color.WHITE);
    getContentPane().add(labelTitle);
    getContentPane().add(scrollPane);

    addWindowListener(new WindowAdapter() {

      @Override
      public void windowOpened(WindowEvent e) {

        try {

          InputStream response = new URL("https://jsonplaceholder.typicode.com/posts?_start=0&_limit=50").openStream();
          Scanner scanner = new Scanner(response);
          String responseBody = scanner.useDelimiter("\\A").next();
          textAreaApiResponse.setText(responseBody);
          scanner.close();

        } catch (Exception ex) {
          System.out.println("Error: " + ex);
        }
      }

    });
  }

  @Override
  public void run() {
    setVisible(true);
  }

}