import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Bullet extends GameObject{

		private int bulletSpeed;
		private int bulletSize;
		private int startX, startY;
		private boolean active;
		private double rotation;
		private int range;
		
		public Bullet(int x, int y, int range, double rotation, int speed, int bulletSpeed, int bulletSize) {
			super(x,y);
			super.x = x;
			super.y = y;
			startX = x;
			startY = y;
			active = true;
			this.rotation = rotation;
			this.range = range;
			this.bulletSpeed = bulletSpeed;
			this.bulletSize = bulletSize;
			
			
		}

		public void draw(Graphics2D g2) {
			g2.setColor(Color.WHITE);
			if(active)
				g2.fillRect(this.x-bulletSize/2,this.y-bulletSize/2,bulletSize,bulletSize);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@Override
		public void update(long elapsedTime, int[][] boardMap,
				ArrayList<GameObject> gameObjects) {
			double xVel = Math.cos(this.rotation*Math.PI/180)*bulletSpeed;
			double yVel = -Math.sin(this.rotation*Math.PI/180)*bulletSpeed;
			
			if (Point.distance(this.x,this.y,startX, startY) >= range) {
				active = false;
			}
			
			this.x += (xVel*elapsedTime)/TDGame.DELAY; 
			this.y -= (yVel*elapsedTime)/TDGame.DELAY; 
		}
	}