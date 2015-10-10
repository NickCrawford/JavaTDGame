import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DefenseMenu {

	private boolean visible;
	
	private Rectangle bounds;
	
	private Image menuImage;
	
	public DefenseMenu() {
		visible = false;
		bounds = new Rectangle();
		
		menuImage = null;
		try {
		    menuImage = ImageIO.read(new File("res/menuBack.png"));
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public void update(long elapsedTime) {
		
	}
	
	public void draw(Graphics2D g2) {
		if (visible) {
			g2.setColor(new Color(0,0,0, 200));
			g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	
			g2.drawImage(menuImage, 
					bounds.x+bounds.width/2 - menuImage.getWidth(null)/2,
					bounds.y+bounds.height/2 - menuImage.getHeight(null)/2,  null);
		}
		
	}
	
	public void setBounds(int x, int y, int width, int height) {
		bounds.setBounds(x, y, width, height);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
