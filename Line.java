
public class Line {
    public float x, y, z;
    public double X, Y, W;
    public double scale, curve;

    public Line(){
        curve = x = y = z = 0;
    }

    public void project(int camX, int camY, int camZ){
        scale = Game.camD / (z - camZ);
        X = (1 + scale * (x - camX)) * Game.width/2;
        Y = (1 - scale * (y - camY)) * Game.height/2;
        W = scale * Game.roadW * Game.width/2;
    }

}
