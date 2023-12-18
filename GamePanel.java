import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

  Image background;
  Image car;
  Image shavarma;
  Image ded;
  Image gameOver;
  private Image snowflake;

  public static Image resizeImage(Image originalImage, int newWidth, int newHeight) {
    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImage.createGraphics();
    g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
    g2.dispose();
    return resizedImage;
  }

  public GamePanel() {
    background = Toolkit.getDefaultToolkit().createImage("district.jpg");
    car = Toolkit.getDefaultToolkit().createImage("car.png");
    snowflake = Toolkit.getDefaultToolkit().createImage("snowflake.png");
    ded = Toolkit.getDefaultToolkit().createImage("ded.png");
    gameOver = Toolkit.getDefaultToolkit().createImage("wordart.png");
  }

  public void paintComponent(Graphics g) {
    if (Game.gameOver){
      Image resizedGameOver = resizeImage(gameOver, 1024, 500);
      g.drawImage(resizedGameOver, 0, 0,null);
      return;
    }
    super.paintComponent(g);
    g.drawImage(background, 0, 0, null);

    for (int i = 0; i < Game.snowFlakesX.length; i++) {
      g.drawImage(snowflake, Game.snowFlakesX[i], Game.snowFlakesY[i], null);
    }

    Game.startPosition = (int) (Game.position / Game.segL);

    Game.speed -= 0.3;

    if (Game.speed > 0) {
      Game.position += Game.speed;
    } else {
      Game.speed = 0;
    }
    Game.x = Game.dx = 0;
    int camH = 1500 + (int) Game.lines.get(Game.startPosition).y;

    int maxY = Game.height;

    if (Game.playerX > Game.roadW || Game.playerX < -Game.roadW){
      Game.speed = Math.min(40, Game.speed);
    }



    for (int n = Game.startPosition; n < Game.startPosition + 300; n++) {
      Line l = Game.lines.get(n % 30000);
      l.project(Game.playerX - (int) (Game.x), camH, Game.position);
      Game.x += Game.dx;
      Game.dx += l.curve;
      Line p = Game.lines.get((n - 1) % 30000);
      if (n == 1) {
        p.project(Game.playerX - (int) (Game.x), camH, 0);
        Game.x += Game.dx;
        Game.dx += p.curve;

      }




      if (n == Game.startPosition){
          Game.playerX -=  Game.dx * 30;
      }
      



      if (l.Y >= maxY && l.Y < Game.height) {
        continue;
      }
      maxY = (int) l.Y;

      Color grass = (n / 3) % 2 == 0 ? new Color(170, 166, 165) : new Color(195, 197, 209);
      Color rumble = (n / 3) % 2 == 0 ? new Color(255, 255, 255) : new Color(0, 0, 0);
      Color road = (n / 3) % 2 == 0 ? new Color(114, 105, 90) : new Color(140, 129, 111);

      new MyQuad(n, new int[] { 0, (int) p.Y, Game.width, 0, (int) l.Y, Game.width }, grass, g);
      new MyQuad(n, new int[] { (int) p.X, (int) p.Y, (int) (p.W * 1.2), (int) l.X, (int) l.Y, (int) (l.W * 1.2) },
          rumble, g);
      new MyQuad(n, new int[] { (int) p.X, (int) p.Y, (int) p.W, (int) l.X, (int) l.Y, (int) l.W }, road, g);

    }
    g.drawImage(car, 400, 500, null);
    int dist = 0;
    for (int n = Game.startPosition + 300; n > Game.startPosition; n--) {
      // Line l = Game.lines.get(n%1600).drawSprite(g);
      dist++;
      Line p = Game.lines.get(n % 30000);
      Image test = p.sprite;
      Image newDed = p.ded;

      if (test != null) {
        Image res = resizeImage(test, (int) (1 + dist * 0.4), (int) (1 + dist * 0.4));
        g.drawImage(res, (int) (p.X) - (int) (p.W) - 200 - p.spriteX, (int) p.Y - res.getWidth(null), null);
      }

       if (newDed != null) {
        Image res = resizeImage(newDed, (int) (1 + dist * 0.4), (int) (1 + dist * 0.4));
        if (Math.abs(((int) (p.X)  - p.dedX) - Game.playerX) < 320 && Math.abs((int) p.Y - res.getWidth(null) - Game.height) < 320) {
          Game.gameOver = true;
          break;
        }
        g.drawImage(res, (int) (p.X)  - p.dedX, (int) p.Y - res.getWidth(null), null);

        //System.out.println(Math.abs(((int) (p.X)  - p.dedX) - Game.playerX));

      }
          
    }

  }



}

