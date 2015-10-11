import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class GameObject
{
	protected int x, y; //The top left corner of the game object in coordinate space
	
	//Sprite Sheet handling
	protected String fileName; //Name of the image file to be loaded
	protected int rows, columns; //The number of rows and columns in the sprite sheet
	protected int size;//The size of the square that contains each image in the sprite sheet.
	protected ArrayList<Image> sprite;
	
	public GameObject(int x, int y) {
		this.x = x;//set position
		this.y = y;
		
	}
	
	/** Method used to load an sprite sheet and splice it into seperate images. Then returns and arraylist of images.
	 * @param fileName The name of the image file to load eg. 'TileSet.jpeg'
	 * @param rows The number of rows in the sprite sheet
	 * @param columns The number of Columns in the sprite sheet
	 * @param size the size of each image in the sprite sheet (a square)
	 * @return
	 */
	protected ArrayList<Image> initSprite(String fileName, int rows, int columns, int size) {
		ArrayList<Image> sprite = new ArrayList<Image>();//Initialize sprite arraylist
		
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage(fileName);
		} catch (IOException e) {
			System.out.println("Couldn't read file: "+fileName);
			return null;
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);

		for(int i = 0; i < rows*size; i += size) {
			for(int j = 0; j < columns*size; j += size) {
				sprite.add(ss.grabSprite(j,i,size,size));
			}
		}
		
		return sprite;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public abstract void update(long elapsedTime, int[][] boardMap, ArrayList<GameObject> gameObjects);
	
	public abstract void draw(Graphics2D g2);
	
}
