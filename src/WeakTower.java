import java.awt.Color;
import java.awt.Graphics2D;


public class WeakTower extends Tower {
	
	public WeakTower(int x, int y) {
		super(x, y);
		fileName = "WeakTower.png";
		rows = 1;
		columns = 1;
		size = 64;
		cost = 2;
		sprite = super.initSprite(fileName, rows, columns, size);
		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.PINK);
		g2.fillRect(x,y,size,size);
	}

}
