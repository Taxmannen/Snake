package model;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class Walls implements Entity{
	
	private CopyOnWriteArrayList<int[]> walls = new CopyOnWriteArrayList<int[]>();
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
 	
	public Walls(int x, int y, int width, int height) {
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		for (int alignOnY = 0; alignOnY < height; alignOnY++) {
			for (int alignOnX = 0; alignOnX < width; alignOnX++) {
				makePartOfWall(x + alignOnX, y + alignOnY);
			}
		}
	}
	
	/* Adds the coordinates of the wall.
	 * @param int - x. 
	 * @param int - y.
	 */	
	private void makePartOfWall(int x, int y) {
		walls.add(new int[]{x, y});	
	}
	
	/* Paints the wall.
	 * @param Graphics. 
	 * @param int - spacing.
	 */	
	public void paint(Graphics g, int spacing) {	
		Image wall;
		wall = new ImageIcon("assets/Wall.png").getImage();
		ListIterator<int[]> it = walls.listIterator();
		while (it.hasNext()) {
			int[] part = it.next();
			g.drawImage(wall, part[0]*spacing, part[1]*spacing, spacing, spacing, null);
		}
		
	}
	
	/* Returns the x position.
	 */	
	public int getX()      { return x;      }
	
	/* Returns the y position.
	 */	
	public int getY()      { return y;      }
	
	/* Returns the width.
	 */	
	public int getWidth()  { return width;  }
	
	/* Returns the height.
	 */	
	public int getHeight() { return height; }
	
	/* Sets the x position.
	 * @param int x - the walls x positions.
	 */	
	public void setX(int x)           { this.x = x;           }
	
	/* Sets the y position.
	 * @param int y - the walls x positions.
	 */	
	public void setY(int y)           { this.y = y;           }
	
	/* Sets the width.
	 * @param int width - the walls width.
	 */	
	public void setWidth(int width)   { this.width = width;   }
	
	/* Sets the height.
	 * @param int height - the walls height.
	 */	
	public void setHeight(int height) { this.height = height; }
	
	/* Returns the walls coordinates.
	 */	
	public CopyOnWriteArrayList<int[]> getCoordinates() { return walls; }
	
	/* Returns the walls size.
	 */	
}