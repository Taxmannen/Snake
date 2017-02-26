package model;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Entity {
	
	/**
	 * Get coordinate pairs for every part of the object
	 * @return list with coordinate pairs
	 */
	public CopyOnWriteArrayList<int[]> getCoordinates();
	
	/**
	 * paint the object
	 * @param g
	 * @param spacing Determines where one part of the object ends, and the next one starts
	 */
	public void paint(Graphics g, int spacing);
	
}
