import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Unit extends GameObject 
{
	protected int speed;//pixel(s) per frame
	
	protected int lastX, lastY;
	protected Point2D target;
	
	public Unit(int x, int y)
	{
		super(x, y);
		lastX = findGridX();
		lastY = findGridY();
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
	
	//Recursive?
	public boolean checkMoveTo(int[][] boardmap, int row, int col)
	{
		if (boardmap[row][col] <= GameComponent.FINAL_PATH_TILE) return true;
		
		return false;
		
	}

	public void move(int[][] boardMap, long elapsedTime) {
		
	}
}
