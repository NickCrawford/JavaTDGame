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
		int myX = super.findGridX();
		int myY = super.findGridY();
		
		switch (boardMap[myY][myX]) {
		case 0: //if on a horizontal piece
			//We only need to check X directions
			if (lastX < myX) { //If I just came from a space to my left
				x += (speed * elapsedTime)/TDGame.DELAY;//then I must be moving to the right
			} else {
				x -= (speed * elapsedTime)/TDGame.DELAY; //Otherwise, move to the left
			}
		break;
		case 1: //if on a Vertical piece
			//We only need to check Y directions
			if (lastY < myY) { //If I just came from a space below me
				y -= (speed * elapsedTime)/TDGame.DELAY;//then I must be moving up
			} else {
				y += (speed * elapsedTime)/TDGame.DELAY; //Otherwise, move to the left
			}
		break;
		case 2: //if on a L Corner Piece
			//We only need to check up and right directions
			if (lastY < myY) { 
				y += (speed * elapsedTime)/TDGame.DELAY;
			} else {
				x += (speed * elapsedTime)/TDGame.DELAY;
			}
		break;
			
		}
	}
}
