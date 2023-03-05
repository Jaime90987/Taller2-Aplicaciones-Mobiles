package src.java;

import java.util.Scanner;
import java.io.File;

public class Main {
  public static void main(String[] args) {

    System.out.print("\033[H\033[2J");

    Scanner scan = new Scanner(System.in);
    String input;
    File audioPath;

    while (true) {
      System.out.print("Ingrese el nombre del archivo de audio (formato wav/aiff): ");
      input = scan.nextLine();

      audioPath = new File("src/resources/audio/" + input);

      if (!audioPath.exists()) {
        System.out.println("El archivo no existe, por favor intente de nuevo.\n");
      } else if (!audioPath.isFile()) {
        System.out.println("Solo se aceptan archivos, por favor intente de nuevo.\n");
      } else if (!input.endsWith(".wav") && !input.endsWith(".WAV") && !input.endsWith(".aiff")
          && !input.endsWith(".AIFF")) {
        System.out.println("Formato de archivo no soportado o no especificado, por favor intente de nuevo.\n");
      } else {
        break;
      }
    }

    scan.close();

    Player playerGUI = new Player(audioPath);
    Thread threadPlayer = new Thread(playerGUI);

    threadPlayer.start();
    System.out.println("\n" + threadPlayer.getName());

  }

}