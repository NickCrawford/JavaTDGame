import java.awt.Color;
import java.awt.Graphics2D;


public class Camera {

	private int x, y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.drawLine(x, y-16, x, y+16);
		g2.drawLine(x-16, y, x+16, y);
	}
}
