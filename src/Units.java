
public abstract class Units extends GameObject 
{
	protected int speed;//pixel(s) per frame
	
	public int getXPos()
	{
		return super.x;
	}
	
	public int getYPos()
	{
		return super.y;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public double update(long elapsedTime, int spd)
	{
		double updateMotion = (spd * elapsedTime)/100;
		return updateMotion;
	}
	
	public void move()
	{
		while(//on screen)
		{
					
		}
				
	}
}
