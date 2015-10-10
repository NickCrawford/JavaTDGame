import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


public class Camera {

	public static final int MOVE_MARGIN = 128;//The amount of space from each edge of the camera bounds that allows the camera to move
	
	private Rectangle bounds;
	
	private int moveSpeed;
	
	/**
	 * 
	 * @param x Center of bounds
	 * @param y Center y of bounds
	 */
	public Camera(int x, int y) {
		bounds = new Rectangle(x,y,0,0);
		
		moveSpeed = 8;
	}
	
	public void update(int mousex, int mousey, long elapsedTime) {
		//Check mouse
		if (mousex < MOVE_MARGIN) {
			bounds.translate(-(int) ((moveSpeed*elapsedTime)/TDGame.DELAY), 0);
		} else if (mousex > getWidth()-MOVE_MARGIN) {
			bounds.translate((int) ((moveSpeed*elapsedTime)/TDGame.DELAY), 0);
		}
		
		if (mousey < MOVE_MARGIN) {
			bounds.translate(0,-(int) ((moveSpeed*elapsedTime)/TDGame.DELAY));
		} else if (mousey > getHeight()-MOVE_MARGIN) {
			bounds.translate(0, (int) ((moveSpeed*elapsedTime)/TDGame.DELAY));
		}
	}
	
	public void setSize(int width, int height) {
		double x = bounds.getCenterX();
		double y = bounds.getCenterY();
		bounds.setFrameFromCenter(x, y, x+width/2, y+height/2);
	}
	
	public int getCenterX() {
		return (int) bounds.getCenterX();
	}
	
	public int getCenterY() {
		return (int) bounds.getCenterY();
	}
	
	public int getWidth() {
		return (int) bounds.getWidth();
	}
	
	public int getHeight() {
		return (int) bounds.getHeight();
	}
	
	public void draw(Graphics2D g2) {
		int x = (int) bounds.getCenterX();
		int y = (int) bounds.getCenterY();
		
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(x, y-16, x, y+16);
		g2.drawLine(x-16, y, x+16, y);
		
		g2.draw(bounds);
	}
	
}
