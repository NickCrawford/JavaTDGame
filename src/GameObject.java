import java.awt.Graphics2D;

public abstract class GameObject
{
	protected int x, y; //The top left corner of the game object
	
	public GameObject(int x, int y) {
		this.x = x;//set position
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void update(long elapsedTime)
	{
		
	}
	
	public void draw(Graphics2D g2)
	{
		
	}
	
}
