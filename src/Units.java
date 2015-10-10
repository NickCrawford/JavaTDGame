
public abstract class Units extends GameObject 
{
	protected int speed;//pixel(s) per frame
	
	public Units(int x, int y)
	{
		super(x, y);
	}
	
 	public int getXPos()//Not completely necessary
	{
		return super.x;
	}
	
	public int getYPos()//Not completely necessary
	{
		return super.y;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	
	public void move()
	{
				
	}
}
