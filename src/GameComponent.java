import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;


public class GameComponent extends JComponent implements MouseListener {

	public static final int GRID_SIZE = 64; //width/height of grid squres on map
	
	private long curTime;
	
	private ArrayList<GameObject> gameObjects; //Arraylist of GameObjects in the foreground
	
	private int[][] boardMap; //2D array of ints that represent board tiles.
	private ArrayList<Image> tiles = new ArrayList<Image>();
	
	public GameComponent(long curTime) {
		this.curTime = curTime;
		
		boardMap = TEST_MAP;

		initTiles();
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
		
		//draw grid
		g2.setColor(Color.GRAY);
		for(int i = 0; i < this.getWidth(); i += GRID_SIZE) {//columns
			g2.drawLine(i, 0, i, this.getHeight());
		}
		for(int j = 0; j < this.getHeight(); j += GRID_SIZE) {//rows
			g2.drawLine(0, j, this.getWidth(), j);
		}
		
		int imageIndex = 0;
		//Iterate through tile array
		for(int x=0; x < boardMap.length; x++) {
			for(int y=0; y < boardMap[x].length; y++) {
				switch(boardMap[x][y]) {
				case 0: imageIndex = 0;
					break;
				case 1: imageIndex = 1;
					break;
				case 2: imageIndex = 2;
					break;
				case 3: imageIndex = 3;
					break;
				default: imageIndex = -1;
					break;
				}
				g2.drawImage(tiles.get(imageIndex), y*GRID_SIZE, x*GRID_SIZE, null);
			}
		}


	}

	public void update(long nextCurTime) {
		long elapsedTime = nextCurTime - curTime;
		
		for(GameObject obj: gameObjects) {
			obj.update(elapsedTime);
		}
		
		curTime = nextCurTime;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
