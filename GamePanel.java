import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;



public class GamePanel extends JPanel{

    Image background;
    Image car;
    Image shavarma;
    Image EnemyCar;


    public static Image resizeImage(Image originalImage, int newWidth, int newHeight) {
      BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = resizedImage.createGraphics();
      g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
      g2.dispose();
      return resizedImage;
  }

    public GamePanel(){
      background = Toolkit.getDefaultToolkit().createImage("district.jpg");
      car = Toolkit.getDefaultToolkit().createImage("car.png");
    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(background, 0,0, null);
      Game.startPosition = (int)(Game.position / Game.segL);


      Game.speed -= 0.3;

      if (Game.speed > 0){
        Game.position += Game.speed;
      }
      else{
        Game.speed = 0;
      }
      Game.x = Game.y = 0;
      
      int camH = 1500 + (int)Game.lines.get(Game.startPosition).y;

      int maxY = Game.height;


      for(int n = Game.startPosition; n < Game.startPosition + 300; n++){
          Line l = Game.lines.get(n%1600);
          l.project(Game.playerX - (int)(Game.x), camH, Game.position);
          Game.x += Game.y;
          Game.y += l.curve;

          Line p = Game.lines.get((n-1)%1600);
          if (n == 1){
            p.project(Game.playerX - (int)(Game.x), camH, 0);
            Game.x += Game.y;
            Game.y += p.curve;
          }

          if (l.Y >= maxY && l.Y < Game.height){
            continue;
          }
          maxY = (int)l.Y;


          Color grass = (n/3)%2 == 0 ? new Color(170, 166, 165) : new Color(195, 197, 209);
          Color rumble = (n/3)%2 == 0 ? new Color(255, 255, 255) : new Color(0, 0, 0);
          Color road = (n/3)%2 == 0 ? new Color(114, 105, 90) : new Color(140, 129, 111);


          new MyQuad(n, new int[] {0, (int)p.Y, Game.width, 0, (int)l.Y, Game.width} , grass, g);
          new MyQuad(n, new int[] {(int)p.X, (int)p.Y, (int)(p.W*1.2), (int)l.X, (int)l.Y, (int)(l.W*1.2)} , rumble, g);
          new MyQuad(n, new int[] {(int)p.X, (int)p.Y, (int)p.W, (int)l.X, (int)l.Y, (int)l.W} , road, g);

      }
      g.drawImage(car, 400, 500, null);
       int dist = 0;
       for(int n = Game.startPosition + 300; n > Game.startPosition; n--){
           // Line l = Game.lines.get(n%1600).drawSprite(g);
           dist++;
           Line p = Game.lines.get(n%1600);
           Image test = p.sprite;
            if (test == null){
              continue;
            }

            //Image res_s = test.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

           Image ress = resizeImage(test, (int)(1 + dist * 0.4) , (int)(1 + dist * 0.4) );
           g.drawImage(ress, (int)(p.X) - (int)(p.W) - 200 - p.spriteX, (int)p.Y - ress.getWidth(null), null);
       }

  }

}