import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    
    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();
        
        jframe.setSize(Game.width, Game.height);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e){

            }

            @Override
            public void  keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W){
                    System.out.println("ueueueu");
                    Game.position += 200;
                }
            }

            @Override
            public void keyReleased(KeyEvent e){

            }
        });
    }

}
