import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class AdUnit extends Units
{
	int spriteNumber;
	public AdUnit(int x, int y, int type)
	{
		super(x, y);
		super.speed = 2;
		
		fileName = "Units.png";
		rows = 2;
		columns = 2;
		size = 64;
		
		spriteNumber = type;
		
		sprite = super.initSprite(fileName, rows, columns, size);
	}

	//tick
	public void update(long elapsedTime)
	{
		
	}
	//render
	public void draw(Graphics2D g2)
	{
		g2.drawImage(sprite.get(spriteNumber), (int)x, (int)y, null);
	}
}

