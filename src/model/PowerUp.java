package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class PowerUp implements Entity, Edible {
	
	private CopyOnWriteArrayList<int[]> coordinates = new CopyOnWriteArrayList<int[]>();
	private int width;
	private int height;
	public long timer;
	
	public PowerUp(int x, int y, int width, int height) {
		coordinates.add(new int[]{x, y});
		this.width = width;
		this.height = height;
		this.timer = System.currentTimeMillis();
	}
	
	/* Paints the power up.
	 * @param Graphics.
	 */	
	@Override
	public void paint(Graphics g, int spacing) {
		Image cherry;
		cherry = new ImageIcon("assets/Cherry.png").getImage();
		g.drawImage(cherry, getX()*spacing, getY()*spacing, width, height, null);
	}
	
	/* Returns the coordinates of the power up.
	 */	
	@Override
	public CopyOnWriteArrayList<int[]> getCoordinates() { return coordinates; }
	
	/* Returns the size of the power up.
	 */	

	
	/* Returns the X position.
	 */	
	private int getX() { return getCoordinates().get(0)[0]; }
	
	/* Returns the Y position.
	 */		
	private int getY() { return getCoordinates().get(0)[1]; }
	
	/*
	 * 
	 */
	@Override
	public int getValue() { return 10; }
}
