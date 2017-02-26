package states;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Button {
	
	private Image image;
	private int width, height, x, y;
	private State state;
	
	public Button(String name, int x, int y, int width, int height, State state) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.state = state;
		image = new ImageIcon("assets/" + name).getImage();
	}
	
	/* Paint the buttons
	 * @param Graphics.
	 */
	public void paint(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}
	 
	/* Returns the x position.
	 */
	public int getX()       { return x;      }
	
	/* Returns the y position.
	 */
	public int getY()       { return y;      }
	
	/* Returns the width.
	 */
	public int getWidth()   { return width;  }
	
	/* Returns the height.
	 */
	public int getHeight()  { return height; }
	
	/* Returns the state.
	 */
	public State getState() { return state;  }
}
