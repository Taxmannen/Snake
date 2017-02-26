package model;

import java.util.ArrayList;

public abstract class ObservableEntity {

	private ArrayList<EntityObserver> observers = new ArrayList<EntityObserver>();
	
	/**
	 * @return ArrayList of observers
	 */
	public ArrayList<EntityObserver> getObservers() {
		return observers;
	}
	
	/**
	 * add observer to notify with updates
	 * @param o EntityObserver
	 */
	public void registerObserver(EntityObserver o) {
		observers.add(o);
	}
	
	/**
	 * remove observer from ArrayList
	 * @param o
	 */
	public void removeObserver(EntityObserver o) {
		observers.remove(o);
	}
	
	/**
	 * tell observers to update with new inofrmation
	 */
	public abstract void notifyObservers();
}
