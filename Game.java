import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
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
    public static double dx;
    public static float maxSpeed = 200;
    public static float maxCoordinateX = 1740;
    public static float minCoordinateX = -1740;
    public static BufferedImage shawarma;
    public static BufferedImage ded;
    public static int[] snowFlakesX;
    public static int[] snowFlakesY;
    public static boolean gameOver;
    
    

    static {
      gameOver = false;
      x = 0;
      dx = 0;
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
      try {
        shawarma = ImageIO.read(new File("shaurma.png"));
      } catch (IOException e) {
        System.out.println(22);
        e.printStackTrace();
      }
      try {
        ded = ImageIO.read(new File("ded.png"));
      } catch (IOException e) {
        System.out.println(22);
        e.printStackTrace();
      }
      snowFlakesX = new int[100];
      snowFlakesY = new int[100];
    }
    
    public Game() {
      Random random = new Random();
      for(int i = 0; i < 100; i++){
          snowFlakesX[i] = random.nextInt(width);
          snowFlakesY[i] = random.nextInt(height);
      }



      for(int i = 0; i < 30000; i++){
        Line line = new Line();
        line.z = i * segL;
        if (i > 300 && i < 700 || i > 1801 && i < 1900 || i > 3700 && i < 3800){
          line.curve = 0.9;
        }

        if (i > 900 && i < 1100 || i > 2200 && i < 2600){
          line.curve = -0.3;
        }

        if (i > 1600 && i < 1800 || i > 3000 && i < 3500){
          line.curve = -0.6;
        }

        if (i > 3600 && i < 3700 || i > 4100 && i < 4150){
          line.curve = -0.9;
        }


        if (i%100 == 0){
          int diff = random.nextInt(300);
          line.spriteX = diff;
          line.sprite = shawarma;
        }

        if (i%70 == 0){
          int diff = random.nextInt(100);
          line.dedX = diff;
          line.ded = ded;
        }

        lines.add(line);
      }

      gamePanel = new GamePanel();
      gameWindow = new GameWindow(gamePanel);
    }
}


