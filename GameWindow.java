import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow {
    private JFrame jframe;
    private static final int TARGET_FPS = 120;
    private static final long TARGET_FRAME_TIME = 1000 / TARGET_FPS;
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();  
        jframe.setSize(Game.width, Game.height);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        Timer timer = new Timer((int) TARGET_FRAME_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upPressed) {
                    Game.speed += 1;
                }
                if (leftPressed){
                    Game.playerX -= 20;
                }
                if (rightPressed){
                    Game.playerX += 20;
                }

                Game.gamePanel.repaint(); 
            }
        });
        timer.start();



        jframe.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e){

            }

            @Override
            public void  keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W){
                    upPressed = true;
                }
                else if (keyCode == KeyEvent.VK_A){
                    leftPressed = true;
                }
                else if (keyCode == KeyEvent.VK_D){
                    rightPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W){
                    upPressed = false;
                }
                else if (keyCode == KeyEvent.VK_A){
                    leftPressed = false;
                }
                else if (keyCode == KeyEvent.VK_D){
                    rightPressed = false;
                }
            }

            
        });
    }

}
