package src.java;

public class TickTack implements Runnable {

  private long audioLength;
  private long currentTime;
  private long startTime;

  TickTack(long audioLength) {
    this.audioLength = (audioLength / 1000);
    this.currentTime = 0;
    this.startTime = System.currentTimeMillis();
  }

  @Override
  public void run() {

    while (audioLength > currentTime) {

      currentTime = System.currentTimeMillis() - startTime;

      System.out.println("Tick");
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("Tack");
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
