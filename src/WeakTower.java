import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class WeakTower extends Tower {
	
	private double rotation;
	
	public WeakTower(int x, int y) {
		super(x, y);
		fileName = "WeakTower.png";
		rows = 1;
		columns = 1;
		size = 64;
		
		cost = 2;
		range = 128;
		speed = 100;//wait 100 update calls before firing again
		sprite = super.initSprite(fileName, rows, columns, size);
		
		rotation = 0; //Rotation in degrees, (0 is right)
		
	}

	@Override
	public void update(long elapsedTime, int[][] boardMap,
			ArrayList<GameObject> gameObjects) {
		
		///Find the closest Unit
		Unit target = null; //The target unit, closest to this tower

		double shortestDistanceSoFar = 10000000.0;
		for(GameObject obj: gameObjects) {
			if (obj instanceof Unit) {
				if (Point.distance(x+size/2, y+size/2, obj.getX(), obj.getY()) < shortestDistanceSoFar) {
					target = (Unit) obj;
					shortestDistanceSoFar = Point.distance(x, y, obj.getX(), obj.getY());
				}
			}
		}

		if (target != null) {
			rotation = Math.toDegrees(Math.atan2((target.getY() - y), (target.getX() - x)));
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.PINK);
		g2.fillRect(x,y,size,size);
		
		g2.setColor(Color.WHITE);
		g2.drawLine((int)x+size/2, (int)y+size/2,(int) (x+size/2+Math.cos(rotation*Math.PI/180)*(size/2)),(int) (y+size/2+Math.sin(rotation*Math.PI/180)*(size/2)));
	}


}
