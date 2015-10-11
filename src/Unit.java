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

	public void move(int[][] boardMap, long elapsedTime)
	{
		int mainX = super.x / 64;
		int mainY = super.y / 64;
		int prmnX = mainX;
		int prmnY = mainY;
		boolean leftOfMain;
		boolean aboveMain;
		boolean rightOfMain;
		boolean belowMain;
		
		if((super.x % 64) == 0 && (super.y % 64) == 0)//determins if unit is exactly in grid
		{
			//
			
			if((mainX - 1) < 0)
			{
				leftOfMain = false;
			}
			else
			{
				leftOfMain = boardMap[mainX - 1][mainY] <= GameComponent.FINAL_PATH_TILE;
			}
			
			if((mainY - 1) < 0)
			{
				aboveMain = false;
			}
			else
			{
				aboveMain = boardMap[mainX][mainY - 1] <= GameComponent.FINAL_PATH_TILE;
			}
			
			if((mainX + 1) > boardMap.length)
			{
				rightOfMain = false;
			}
			else
			{
				rightOfMain = boardMap[mainX + 1][mainY] <= GameComponent.FINAL_PATH_TILE;
			}
			
			if((mainY + 1) > boardMap[mainX].length)
			{
				belowMain = false;
			}
			else
			{
				belowMain = boardMap[mainX][mainY + 1] <= GameComponent.FINAL_PATH_TILE;
			}
			
			//////////////////////////////////////
			
			if(leftOfMain && (mainX - 1 != prmnX))
			{
				super.x--;
			}
			else if(aboveMain && (mainY - 1 != prmnY))
			{
				super.y--;
			}
			else if(rightOfMain && (mainX + 1 != prmnX))
			{
				super.x++;
			}
			else if(belowMain && (mainY + 1 != prmnY))
			{
				super.y++;
			}
			
		}
		else if((super.x % 64) == 0)//in transit
		{
			super.y++;
		}
		else if((super.y % 64) == 0)//in transit
		{
			super.x++;
		}
	}
}
