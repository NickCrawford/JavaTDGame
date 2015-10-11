
public abstract class Tower extends GameObject {

	protected int attackDamage;
	protected int cost;
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
	
}
