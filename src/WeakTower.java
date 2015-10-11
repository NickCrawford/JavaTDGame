import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class WeakTower extends Tower {
	
	private double rotation;
	private double timeSinceLastFire;
	
	private ArrayList<Bullet> bullets;
	
	public WeakTower(int x, int y) {
		super(x, y);
		fileName = "WeakTower.png";
		rows = 1;
		columns = 1;
		size = 64;
		
		description = "Weak Tower";
		cost = 1;
		range = 128;
		speed = 50;//wait 100 update calls before firing again
		timeSinceLastFire = speed;
		sprite = super.initSprite(fileName, rows, columns, size);
		
		rotation = 0; //Rotation in degrees, (0 is right)
		
		bullets = new ArrayList<Bullet>();
		
	}

	@Override
	public void update(long elapsedTime, int[][] boardMap,
			ArrayList<GameObject> gameObjects) {
		
		///Find the closest Unit
		Unit target = null; //The target unit, closest to this tower

		double shortestDistanceSoFar = 10000000.0;
		for(GameObject obj: gameObjects) {
			if (obj instanceof Unit) {
				double dist = Point.distance(x+size/2, y+size/2, obj.getX()+obj.getSize()/2, obj.getY()+obj.getSize()/2);
				
				if (dist <= shortestDistanceSoFar) {
					if (dist <= range)  {
						target = (Unit) obj;
						shortestDistanceSoFar = dist;
					} else {
						target = null;
						rotation = Tower.lerp((float) rotation, 90f, (float) (0.2*elapsedTime/TDGame.DELAY));
					}
				}
			}
		}

		//If there exists a target
		if (target != null) {
			rotation = Math.toDegrees(Math.atan2((target.getY() - y), (target.getX() - x)));//rotate towards it
		
			if (timeSinceLastFire >= speed) {
				timeSinceLastFire = 0;
				bullets.add(new Bullet(this.x+size/2, this.y+size/2, range, rotation, speed, 5, 4));
			}
			
		}
		
		for (Bullet b : bullets) {
			b.update(elapsedTime);
		}
		
		timeSinceLastFire ++;
	}

	
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform save = g2.getTransform();
		g2.rotate(rotation*Math.PI/180, x+size/2, y+size/2);
		g2.drawImage(sprite.get(0), x,y,null);
		g2.setTransform(save);
		
		g2.setColor(Color.WHITE);
		
		//g2.drawLine((int)x+size/2, (int)y+size/2,(int) (x+size/2+Math.cos(rotation*Math.PI/180)*(size/4)),(int) (y+size/2+Math.sin(rotation*Math.PI/180)*(size/4)));
		
		//g2.drawOval(x-range/2-size/2, y-range/2-size/2, range*2, range*2);
		
		for(Bullet b: bullets) {
			b.draw(g2);
		}
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}


}

