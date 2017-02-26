package states;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.GUI;

public class MenuState extends State {

	public static Button button[] = new Button[3];
	public static int menuCounter;
	
	public MenuState(FSM fsm) {
		super(fsm, "MenuState");
		FSM.setState(this);
		button[0] = new Button("PlayButton.png", 75, 300, 300, 100, new PlayState(fsm, null));
		button[1] = new Button("ControlsButton.png", 75, 450, 300, 100, new ControlsState(fsm, null));
		button[2] = new Button("CreditsButton.png", 75, 600, 300, 100, new CreditsState(fsm, null));
		if(menuCounter == 0) {
			menuCounter++;
			new GUI();
		}
	}
	
	/* paints the assets, and the text for the menuscreen.
	 * @param Graphics. 
	 */
	@Override
	public void paint(Graphics g) {
		Image background;
		background = new ImageIcon("assets/Background.jpg").getImage();
		g.drawImage(background, 0, 0, 1280, 760, null);
		
		Image snake;
		snake = new ImageIcon("assets/Snake.png").getImage();
		g.drawImage(snake, 680, 300, 400, 400, null);
		
		Image header;
		header = new ImageIcon("assets/Header.png").getImage();
		g.drawImage(header, 400, 25, 500, 200, null);
		
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(5));
		g2.drawLine(0, 220, 1280, 220);
		g2.drawLine(450, 220, 450, 750);
		button[0].paint(g);
		button[1].paint(g);
		button[2].paint(g);
	}
}
