import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DefenseMenu {

	public static final int PREVIEW_SIZE = 56;
	public static final int MENU_V_MARGIN = 32;
	
	private boolean visible;
	private int mousex, mousey;
	
	private Rectangle bounds;
	
	private Image menuImage;
	private ArrayList<Image> previews;
	private ArrayList<Tower> previewTowers;
	
	private Font hudFont;
	
	private Polygon statCell;
	private Polygon menuMask;
	
	public DefenseMenu(Font hudFont) {
		visible = false;
		bounds = new Rectangle();
		
		this.hudFont = hudFont;
		
		menuImage = null;
		try {
		    menuImage = ImageIO.read(new File("res/menuBack.png"));
		} catch (IOException e) {
			System.exit(0);
		}
		
		previews = new ArrayList<Image>();
		try {
		    previews.add(ImageIO.read(new File("res/WeakTower.png")));
		} catch (IOException e) {
			System.exit(0);
		}
		
		try {
		    previews.add(ImageIO.read(new File("res/StrongTower.png")));
		} catch (IOException e) {
			System.exit(0);
		}
		
		previewTowers = new ArrayList<Tower>();
		previewTowers.add(new WeakTower(0,0));
		
		
		
		//define stat cell shape
		statCell = new Polygon();
		statCell.addPoint(4, -14);
		statCell.addPoint(12,-14);
		statCell.addPoint(4, 0);
		statCell.addPoint(-4, 0);
		
		//define menu mask
		menuMask = new Polygon();
		menuMask.addPoint(120,32);
		menuMask.addPoint(361,32);
		menuMask.addPoint(479,239);
		menuMask.addPoint(361,446);
		menuMask.addPoint(120,446);
		menuMask.addPoint(0,239);
		
	}
	
	public void update(long elapsedTime, int mousex, int mousey) {
		this.mousex = mousex;
		this.mousey = mousey;
	}
	
	public void draw(Graphics2D g2) {
		if (visible) {
			g2.setColor(new Color(0,0,0, 200));
			g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	
			g2.drawImage(menuImage, 
					bounds.x+bounds.width/2 - menuImage.getWidth(null)/2,
					bounds.y+bounds.height/2 - menuImage.getHeight(null)/2,  null);
			
			//draw preview circles
			int index = 0;
			for(double i=Math.PI/2; i < 13*Math.PI/6 ; i += Math.PI/3) {
				g2.setColor(Color.DARK_GRAY);
				double cX = bounds.x+bounds.width/2+(Math.cos(i)*menuImage.getWidth(null)/3.2);
				double cY = bounds.y+bounds.height/2+(-Math.sin(i)*menuImage.getHeight(null)/3.2);
				
				Ellipse2D.Double circ = new Ellipse2D.Double();
				circ.setFrameFromCenter(new Point2D.Double(cX, cY), new Point2D.Double(cX+PREVIEW_SIZE/2, cY+PREVIEW_SIZE/2));
				String towerName = "Choose Tower";
				if (circ.contains(new Point2D.Double(mousex, mousey))) {
					g2.setColor(Color.WHITE);
					
					boolean showInfo = true;
					
					if (index == 3) {
						showInfo = false;
					} else {
						showInfo = true;
					}
					
					if (showInfo) {
						g2.drawString("Cost      "+previewTowers.get(index).getCost(), bounds.x+bounds.width/2 - 80, bounds.y+bounds.height/2);
						g2.drawString("Range     "+previewTowers.get(index).getRange(), bounds.x+bounds.width/2 - 80, bounds.y+bounds.height/2 + MENU_V_MARGIN);
						g2.drawString("Speed     "+previewTowers.get(index).getSpeed(), bounds.x+bounds.width/2 - 80, bounds.y+bounds.height/2 + 2*MENU_V_MARGIN);
					}
				}
				g2.setColor(Color.WHITE);
				g2.setFont(hudFont.deriveFont(18f));
				g2.drawString(towerName, bounds.x+bounds.width/2 - 80, bounds.y+bounds.height/2 - (int) (1.4*MENU_V_MARGIN));
				g2.fillRect(bounds.x+bounds.width/2 - 96, bounds.y+bounds.height/2 - MENU_V_MARGIN, 200, 4);
				
				
				g2.fillOval((int) cX - PREVIEW_SIZE/2, (int) cY - PREVIEW_SIZE/2, PREVIEW_SIZE, PREVIEW_SIZE);
				g2.setColor(Color.WHITE);
				
				if (index < 2)
				g2.drawImage(previews.get(index), (int) cX-previews.get(index).getWidth(null)/2, (int) cY-previews.get(index).getHeight(null)/2, null);
				index++;
			}
			
			
			
			AffineTransform saveAt = g2.getTransform();
			g2.setStroke(new BasicStroke(2));
			for(int i = 0; i <= 2; i ++) {
				for(int j = 0; j < 5; j++) {
					g2.translate(bounds.x+bounds.width/2 + j*12 + 24, bounds.y+bounds.height/2 + MENU_V_MARGIN*i);
					g2.draw(statCell);
					g2.setTransform(saveAt);
				}
			}
			g2.setTransform(saveAt);
			
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
		Tower retVal = null; ///The tower to return
		
		Polygon clone = new Polygon(menuMask.xpoints, menuMask.ypoints, menuMask.npoints);
		clone.translate(bounds.x+bounds.width/2 - menuImage.getWidth(null)/2,
				bounds.y+bounds.height/2 - menuImage.getHeight(null)/2);
		if (!clone.contains(mousex, mousey)) visible = false; //Exit out of menu if mouse click isnt inside hexagon
		
		int option = 0;
		for(double i=Math.PI/2; i < 13*Math.PI/6; i += Math.PI/3) {
			double cX = bounds.x+bounds.width/2+(Math.cos(i)*menuImage.getWidth(null)/3.2);
			double cY = bounds.y+bounds.height/2+(-Math.sin(i)*menuImage.getHeight(null)/3.2);
			
			Ellipse2D.Double circ = new Ellipse2D.Double();
			circ.setFrameFromCenter(new Point2D.Double(cX, cY), new Point2D.Double(cX+PREVIEW_SIZE/2, cY+PREVIEW_SIZE/2));
			if (circ.contains(new Point2D.Double(mousex, mousey))) {
				System.out.println("Hit at option "+option);
				switch(option) {
				case 0: retVal = new WeakTower(0,0);
				break;
				case 3: visible = false;
				break;
				default: retVal = null;
				break;
				}
				
			}
			option++;
		}
		
		return retVal;
	}
	
	
}
