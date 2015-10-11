import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Bullet {

		public static final int BULLET_SPEED = 5;
		public static final int BULLET_SIZE = 4;
		private int startX, startY;
		private boolean active;
		private int x, y;
		private double rotation;
		private int range;
		
		public Bullet(int x, int y, int range, double rotation, int speed) {
			this.x = x;
			this.y = y;
			startX = x;
			startY = y;
			active = true;
			this.rotation = rotation;
			this.range = range;
			
			
		}

		public void update(long elapsedTime) {
			double xVel = Math.cos(this.rotation*Math.PI/180)*BULLET_SPEED;
			double yVel = -Math.sin(this.rotation*Math.PI/180)*BULLET_SPEED;
			
			if (Point.distance(this.x,this.y,startX, startY) >= range) {
				active = false;
			}
			
			this.x += (xVel*elapsedTime)/TDGame.DELAY; 
			this.y -= (yVel*elapsedTime)/TDGame.DELAY; 
		}

		public void draw(Graphics2D g2) {
			g2.setColor(Color.WHITE);
			if(!active)
				g2.drawRect(this.x-BULLET_SIZE/2,this.y-BULLET_SIZE/2,BULLET_SIZE,BULLET_SIZE);
			else
				g2.fillRect(this.x-BULLET_SIZE/2,this.y-BULLET_SIZE/2,BULLET_SIZE,BULLET_SIZE);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}