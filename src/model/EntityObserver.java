package model;

public interface EntityObserver {

	/**
	 * Method called by ObservableEntity
	 * @param c ControlledCharacter
	 */
	public void update(ControlledCharacter c);
}
