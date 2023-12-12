import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
    public static int playerX;
    public static double x;
    public static double y;
    public static float maxSpeed = 200;
    public static float maxCoordinateX = 1740;
    public static float minCoordinateX = -1740;
    public static Image shawarma;
    
    

    static {
      x = 0;
      y = 0;
      playerX = 0;
      width = 1024;
      height = 768;
      camD = 0.84;
      segL = 200;
      roadW = 2000;
      lines = new ArrayList<Line>();
      position = segL;
      startPosition = 0;
      speed = 0;
      shawarma = Toolkit.getDefaultToolkit().createImage("shaurma.png");
    }
    
    public Game() {
      Random random = new Random();
      for(int i = 0; i < 1600; i++){
        Line line = new Line();
        line.z = i * segL;
        if (i > 300 && i < 700){
          line.curve = 0.5;
        }
        if (i > 750){
          line.y = (float) (Math.sin(i / 30.0) * 1500);
        }
        if (i%100 == 0){
          int diff = random.nextInt(300);
          line.spriteX = diff;
          line.sprite = shawarma;
        }
        lines.add(line);
      }

      gamePanel = new GamePanel();
      gameWindow = new GameWindow(gamePanel);
    }
}
