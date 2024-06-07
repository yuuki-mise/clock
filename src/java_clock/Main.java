package java_clock;

public class Main extends Object{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            new ClockAction().perform();
          }
        });
      }
}
