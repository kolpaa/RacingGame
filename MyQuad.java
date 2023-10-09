import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MyQuad{
    private int[] xPoints; // x-координаты точек
    private int[] yPoints; 
    private Color mycolor = new Color(255, 255, 0);

    public MyQuad(int[] Points, Color myColor, Graphics g){
        xPoints = new int[4];
        yPoints = new int[4];

        this.xPoints[0] = Points[0] - Points[2];
        this.xPoints[1] = Points[3] - Points[5];
        this.xPoints[2] = Points[3] + Points[5];
        this.xPoints[3] = Points[0] + Points[2];
        this.yPoints[0] = Points[1];
        this.yPoints[1] = Points[4];
        this.yPoints[2] = Points[4];
        this.yPoints[3] = Points[1];
    
        this.mycolor = myColor;
        draw(g);
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(mycolor);
        g2d.fillPolygon(xPoints, yPoints, xPoints.length);     
    }
}
