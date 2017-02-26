package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class Apple implements Entity, Edible{
	private CopyOnWriteArrayList<int[]> coordinates = new CopyOnWriteArrayList<int[]>();
	
	private int width;
	private int height;
	private int value;
	
	public Apple (int x, int y, int width, int height) {
		coordinates.add(new int[]{x, y});
		this.width = width;
		this.height = height;
		value = 10;
	}
	
	public CopyOnWriteArrayList<int[]> getCoordinates() {
		return coordinates;
	}
	
	@Override
	public int getValue() { return value; }

	@Override
	public void paint(Graphics g, int spacing) {
		Image apple;
		apple = new ImageIcon("assets/Apple.png").getImage();
		g.drawImage(apple, getX()*spacing, getY()*spacing, width, height, null);
	}
	
	private int getX() {
		return getCoordinates().get(0)[0];
	}

	private int getY() {
		return getCoordinates().get(0)[1];
	}

	
}
