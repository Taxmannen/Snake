package model;

/**
 * object that stores update values
 * @author Freddy
 *
 */
public class ChangeObject {
	protected int x;
	protected int y;
	protected Entity e;
	
	public ChangeObject(int x, int y, Entity e) {
		this.x = x;
		this.y = y;
		this.e = e;
	}
}
