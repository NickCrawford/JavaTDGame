import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class AdUnit extends Unit
{
	
	public AdUnit(int x, int y)
	{
		super(x, y);
		speed = 2;
		
		fileName = "Units.png";
		rows = 2;
		columns = 2;
		size = 64;
		
		sprite = super.initSprite(fileName, rows, columns, size);
	}

	//render
	public void draw(Graphics2D g2)
	{
		g2.drawImage(sprite.get(0), (int)x, (int)y, null);
	}

	@Override
	public void update(long elapsedTime, int[][] boardMap,
			ArrayList<GameObject> gameObjects) {
		//move(boardMap, elapsedTime);
	}
}

