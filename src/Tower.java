
public abstract class Tower extends GameObject {

	protected int speed;//ticks between firing 1-Fastest     100-slowest
	protected int cost;//The cost in credits
	protected int range; //The radius of the tower's range
	protected String description;//Description of the twoer to be displayed in the menu
	
	public Tower(int x, int y ) {
		super(x, y);
	}
	
	public String getDescription() {
		return description;
	}	

	public int getCost() {
		return cost;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}
	
	public static float lerp(float a, float b, float f)
	{
	    return a + f * (b - a);
	}

	
	
	
}
