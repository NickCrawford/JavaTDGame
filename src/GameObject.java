import java.awt.Graphics2D;

public abstract class GameObject
{
	protected int x;
	
	protected int y;
	
	protected int[] mapPos = {0,0};
	
	public GameObject(int origX, int origY, int mapPosX, int mapPosY)
	{
		x = origX;
		y = origY;
		mapPos[0] = mapPosX;
		mapPos[1] = mapPosY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void updateMapPos()
	{
		mapPos[0] = x/64;
		mapPos[1] = y/64;
	}
	
	public void update(long elapsedTime)
	{
		
	}
	
	public void draw(Graphics2D g2)
	{
		
	}
	
}
