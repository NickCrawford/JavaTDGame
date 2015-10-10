import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;


public class GameComponent extends JComponent implements MouseListener {

	public static final int GRID_SIZE = 64; //width/height of grid squres on map
	
	//static ints used for easy game status management.
	public static final int DEFENSE = 0;
	public static final int OFFENSE = 1;
	public static final int PLAYING = 2;
	
	private long curTime;
	
	private ArrayList<GameObject> gameObjects; //Arraylist of GameObjects in the foreground
	private Camera cam;
	
	private int[][] boardMap; //2D array of ints that represent board tiles.
	private ArrayList<Image> tiles = new ArrayList<Image>();
	
	//mouse coordinates
	private int mousex, mousey;//The mouse's position on the JComponent's screen coordinate system.
	private int mouseXWorld, mouseYWorld; //The mouse position in the world's coordinate system
	
	//Game status
	private int status; //0-Defense, 1-Offense, 2- playing
	private int roundNum;
	
	//Player Settings
	private int offenseCredits;
	private int defenseCredits;
	
	//Fonts
	private Font hudFont;
	
	public GameComponent(long curTime) {
		this.curTime = curTime;
		
		boardMap = TEST_MAP;

		initTiles();
		
		gameObjects = new ArrayList<GameObject>();
		
		WeakTower testTower = new WeakTower(128,256);
		gameObjects.add(testTower);
		
		cam = new Camera((boardMap.length*GRID_SIZE)/2, (boardMap[0].length*GRID_SIZE)/2);
		
		mousex = 0;
		mousey = 0;
		
		status = DEFENSE;
		roundNum = 1;
		
		offenseCredits = 3;
		defenseCredits = 3;
		
		initFonts();
		
	}
	
	private void initFonts() {
		try {
			hudFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/HUD.ttf")).deriveFont(14f);
		} catch (FontFormatException | IOException e) {
			System.out.println("Couldnt find 'HUD.ttf'");
			hudFont = new Font("Verdana", Font.PLAIN, 16);
		}
		
	}
	
	private void initTiles() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage("TileSet.jpeg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);

		for(int i = 0; i < 128; i += GRID_SIZE) {
			for(int j = 0; j < 128; j += GRID_SIZE) {
				tiles.add(ss.grabSprite(j,i,GRID_SIZE,GRID_SIZE));
			}
		}

	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; //Cast to Graphics 2D
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		AffineTransform saveAt = g2.getTransform();
		g2.translate(-cam.getCenterX()+cam.getWidth()/2, -cam.getCenterY()+cam.getHeight()/2);
		
		//Iterate through tile array
		for(int x=0; x < boardMap.length; x++) {
			for(int y=0; y < boardMap[x].length; y++) {
				g2.drawImage(tiles.get(boardMap[x][y]), y*GRID_SIZE, x*GRID_SIZE, null);
			}
		}

		//draw each GameObject
		for(GameObject obj: gameObjects) {
			obj.draw(g2);
		}
		
		g2.setColor(new Color(255,255,255, 128));
		//Drawing highlights
		for(int x=0; x < boardMap.length; x++) {
			for(int y=0; y < boardMap[x].length; y++) {
				//Highlighting tiles
				if (mouseYWorld >= y*GRID_SIZE && mouseYWorld < (y+1)*GRID_SIZE) {
					if (mouseXWorld >= x*GRID_SIZE && mouseXWorld < (x+1)*GRID_SIZE) {//check if mouse is within x bounds
						g2.fillRect(x*GRID_SIZE, y*GRID_SIZE, GRID_SIZE, GRID_SIZE);
					}
				}
			}
		}
	
		cam.draw(g2); //Draw the camera
		
		g2.setTransform(saveAt);
		
		//GUI
		g2.setColor(Color.BLACK);
		g2.drawString("X: "+mouseXWorld+" Y: "+mouseYWorld, mousex, mousey);
		
		g2.setFont(hudFont.deriveFont(22f));
		
		String statusString;
		switch(status) {
			case DEFENSE: statusString = "Defense";
						  g2.setColor(Color.BLUE);
			break;
			case OFFENSE: statusString = "Offense";
						  g2.setColor(Color.RED);
			break;
			case PLAYING: statusString = "Play";
					 	  g2.setColor(Color.WHITE);
			break;
			default: statusString = "?";
			break;
		}
		g2.drawString(statusString, 16, 32);
		
		g2.setColor(Color.WHITE);
		g2.setFont(hudFont);
		
		g2.drawString("Round "+roundNum, 16, 54);
		
		

	}

	public void update(long nextCurTime) {
		long elapsedTime = nextCurTime - curTime;
		////
		
		for(GameObject obj: gameObjects) {
			obj.update(elapsedTime);
		}
		
		//Get mouse position, pass to camera for movement
		if (this.getMousePosition() != null) {
			mousex = this.getMousePosition().x;
			mousey = this.getMousePosition().y;
			
			//Calculate mouse in world space
			mouseXWorld = mousex + cam.getCenterX()-cam.getWidth()/2;
			mouseYWorld = mousey + cam.getCenterY()-cam.getHeight()/2;
			
		}
		
		cam.setSize(this.getWidth(), this.getHeight());
		cam.update(mousex, mousey, elapsedTime);
		
		if(status == DEFENSE) {
			
		}
		
		////
		curTime = nextCurTime;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (status == DEFENSE) {
			int gridSpaceX = mouseXWorld / GRID_SIZE;
			int gridSpaceY = mouseYWorld / GRID_SIZE;
			
			gameObjects.add(new WeakTower(gridSpaceX * GRID_SIZE, gridSpaceY * GRID_SIZE));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public static final int[][] TEST_MAP = {
		{1,0,1,1,1},
		{1,0,0,0,1},
		{1,1,1,0,1},
		{1,0,0,0,1},
		{1,0,1,1,1}
	};
}
