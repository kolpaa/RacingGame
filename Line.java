import java.awt.Graphics;
import java.awt.Image;

public class Line {
    public float x, y, z;
    public double X, Y, W;
    public double scale, curve, clip;
    public Image sprite;
    public int spriteX;

    public Line() {
        curve = x = y = z = 0;
    }

    public void project(int camX, int camY, int camZ) {
        scale = Game.camD / (z - camZ);
        X = (1 + scale * (x - camX)) * Game.width / 2;
        Y = (1 - scale * (y - camY)) * Game.height / 2;
        W = scale * Game.roadW * Game.width / 2;
    }

    public void drawSprite(Graphics g) {
        Image s = sprite;
        if (s == null) {
            return;
        }
        int w = s.getWidth(null);
        int h = s.getHeight(null);

        double destX = X + scale * spriteX * Game.width / 2;
        double destY = Y + 4;
        double destW = w * W / 266;
        double destH = h * W / 266;

        destX += destW * spriteX;
        destY += destH * (-1);

        double clipH = destY + destH - clip;
        if (clipH < 0) {
            clipH = 0;
        }
        if (clipH > destH) {
            return;
        }
        // System.out.println(Y);
        g.drawImage(s, (int) destX, (int) destY, (int) (destW / w), (int) (destH / h), null);
    }

}