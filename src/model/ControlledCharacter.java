package model;

public abstract class ControlledCharacter extends ObservableEntity implements Entity {
	
	/**
	 * 
	 * @return ControlledCharacter's score.
	 */
	public abstract int getScore();	
	
	
	/**
	 * switch direction 90 degrees counter clock-wise.
	 */
	public abstract void turnLeft();
	
	/**
	 * switch direction 90 degrees clock-wise
	 */
	public abstract void turnRight();
	
	/**
	 * move forward in current direction
	 */
	public abstract void updatePosition();
	
	/**
	 * 
	 * @return object with update suggestions
	 */
	protected abstract ChangeObject getUpdateSuggestion();
	
	/**
	 * executes code that depends on the passed Entity
	 * @param e Entity
	 */
	public abstract void collision(Entity e);
	
	/**
	 * Check the living state of ControlledCharacter
	 * @return true if alive, otherwise false
	 */
	public abstract boolean isAlive();

}