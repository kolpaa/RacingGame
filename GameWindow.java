import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWindow {
    private JFrame jframe;
    private static final int TARGET_FPS = 120;
    private static final long TARGET_FRAME_TIME = 1000 / TARGET_FPS;
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;

    public GameWindow(GamePanel gamePanel) {
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
                    if (Game.speed < Game.maxSpeed) {
                        Game.speed += 1;
                    }
                }
                if (downPressed) {
                    Game.speed -= 1;
                }
                if (leftPressed && (upPressed | downPressed)) {
                    if (Game.speed > 10){
                        if (Game.playerX > Game.minCoordinateX)
                            Game.playerX -= 20;
                    }
                }
                if (rightPressed) {
                    if (Game.speed > 10){
                        if (Game.playerX < Game.maxCoordinateX) {
                        Game.playerX += 20;
                        }
                    }
                }
                Game.gamePanel.repaint();
            }
        });
        timer.start();

        jframe.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    upPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    downPressed = true;
                } else if (keyCode == KeyEvent.VK_A) {
                    leftPressed = true;
                } else if (keyCode == KeyEvent.VK_D) {
                    rightPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    upPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    downPressed = false;
                } else if (keyCode == KeyEvent.VK_A) {
                    leftPressed = false;
                } else if (keyCode == KeyEvent.VK_D) {
                    rightPressed = false;
                }
            }

        });
    }

}
