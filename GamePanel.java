import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    public GamePanel(){

    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Game.startPosition = (int)(Game.position / Game.segL);


      Game.speed -= 0.3;



      if (Game.speed > 0){
        Game.position += Game.speed;
      }
      else{
        Game.speed = 0;
      }
      Game.x = Game.dx = 0;
      
      int camH = 1500 + (int)Game.lines.get(Game.startPosition).y;

      int maxY = Game.height;


      for(int n = Game.startPosition; n < Game.startPosition + 300; n++){
          Line l = Game.lines.get(n%1600);
          l.project(Game.playerX - (int)(Game.x), camH, Game.position);
          Game.x += Game.dx;
          Game.dx += l.curve;

          Line p = Game.lines.get((n-1)%1600);
          if (n == 1){
            p.project(Game.playerX - (int)(Game.x), camH, 0);
            Game.x += Game.dx;
            Game.dx += p.curve;
          }



          Color grass = (n/3)%2 == 0 ? new Color(16, 200, 16) : new Color(0, 154, 0);
          Color rumble = (n/3)%2 == 0 ? new Color(255, 255, 255) : new Color(0, 0, 0);
          Color road = (n/3)%2 == 0 ? new Color(107, 107, 107) : new Color(105, 105, 105);


          new MyQuad(n, new int[] {0, (int)p.Y, Game.width, 0, (int)l.Y, Game.width} , grass, g);
          new MyQuad(n, new int[] {(int)p.X, (int)p.Y, (int)(p.W*1.2), (int)l.X, (int)l.Y, (int)(l.W*1.2)} , rumble, g);
          new MyQuad(n, new int[] {(int)p.X, (int)p.Y, (int)p.W, (int)l.X, (int)l.Y, (int)l.W} , road, g);

      }


    }
}
