package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ControlsState extends State {
	private Font myFont = new Font ("Helvetica", 1, 75);
	private Font myFont2 = new Font ("Helvetica", 1, 50);
	
	public ControlsState(FSM fsm, String name) {
		super(fsm, "ControlState");
	}
	
	/* paints the background, and the text of the controlls screen.
	 * @param Graphics.
	 */
	@Override
	public void paint(Graphics g) {
		Image background;
		background = new ImageIcon("assets/Background.jpg").getImage();
		g.drawImage(background, 0, 0, 1280, 760, null);
		
		g.setColor(Color.black);
		g.setFont(myFont);
		g.drawString("How To Play:", 400 , 150);
		g.setFont(myFont2);
		g.drawString("You goal is to grow as long as you can!", 180, 250);
		g.drawString("Go Back To Menu = Escape", 330, 350);
		g.drawString("Right = Right Arrow:", 390, 450);
		g.drawString("Left = Left Arrow:", 430, 550);
		g.drawString("Pause = P", 530, 650);
	}
}
