import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DefenseMenu {

	public static final int PREVIEW_SIZE = 56;
	public static final int MENU_V_MARGIN = 32;
	
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
			
			//draw preview circles
			g2.setColor(Color.DARK_GRAY);
			for(double i=Math.PI/2; i < 5*Math.PI/2; i += Math.PI/3) {
				
				double cX = bounds.x+bounds.width/2+(Math.cos(i)*menuImage.getWidth(null)/3.2);
				double cY = bounds.y+bounds.height/2+(Math.sin(i)*menuImage.getHeight(null)/3.2
						);
				
				g2.fillOval((int) cX - PREVIEW_SIZE/2, (int) cY - PREVIEW_SIZE/2, PREVIEW_SIZE, PREVIEW_SIZE);
			}
			
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(18f));
			g2.drawString("Cost", bounds.x+bounds.width/2 - 64, bounds.y+bounds.height/2 - MENU_V_MARGIN);
			g2.drawString("Range", bounds.x+bounds.width/2 - 64, bounds.y+bounds.height/2);
			g2.drawString("Damage", bounds.x+bounds.width/2 - 64, bounds.y+bounds.height/2 + MENU_V_MARGIN);
			
			
			
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

	public Tower getClickedItem(int mousex, int mousey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
