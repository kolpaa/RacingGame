import java.util.ArrayList;

public class Game {
    
    private GameWindow gameWindow;
    public static GamePanel gamePanel;
    public static double camD;
    public static int segL;
    public static int roadW;
    public static ArrayList<Line> lines;
    public static int width;
    public static int height;
    public static int position;
    public static int startPosition;
    public static float speed;

    static {
      width = 1024;
      height = 768;
      camD = 0.84;
      segL = 200;
      roadW = 2000;
      lines = new ArrayList<Line>();
      position = segL;
      startPosition = 0;
      speed = 0;
    }
    
    public Game() {

      for(int i = 0; i < 1600; i++){
        Line line = new Line();
        line.z = i * segL;
        lines.add(line);
      }

      gamePanel = new GamePanel();
      gameWindow = new GameWindow(gamePanel);
      
    }
}
