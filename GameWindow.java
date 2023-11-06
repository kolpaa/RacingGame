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
            }

            @Override
            public void keyReleased(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W){
                    upPressed = false;
                }
            }
        });
    }

}
