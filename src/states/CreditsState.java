package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CreditsState extends State {
	private Font myFont = new Font ("Helvetica", 1, 75);
	private Font myFont2 = new Font ("Helvetica", 1, 25);
	public static Button button[] = new Button[2];
	public static int page = 1;
	
	public CreditsState(FSM fsm, String name) {
		super(fsm, "CreditsState");
		button[0] = new Button("NextButton.png", 1150, 625, 100, 100, null);
		button[1] = new Button("BackButton.png", 40, 625, 100, 100, null);
		
	}
	
	/* paints the background, and the text of the credits screen.
	 * @param Graphics. 
	 */
	@Override
	public void paint(Graphics g) {
		Image background;
		background = new ImageIcon("assets/Background.jpg").getImage();
		g.drawImage(background, 0, 0, 1280, 760, null);
		g.setColor(Color.black);
		g.setFont(myFont);
		g.drawString("Credits to:", 450 , 150);
		g.setFont(myFont2);
		
		if(page == 1) {
			button[0].paint(g);
			g.drawString("Apple.wav: https://freesound.org/people/wadaltmon/sounds/275015/", 30, 250);
			g.drawString("Death.wav: https://freesound.org/people/cabled_mess/sounds/350987/", 30, 325);
			g.drawString("Turn.wav: https://freesound.org/people/Taira%20Komori/sounds/213446/", 30, 400);
			g.drawString("PowerUp.wav: https://freesound.org/people/RandomationPictures/sounds/138491/", 30, 475);
			g.drawString("Apple: http://www.clipartkid.com/images/7/apple-clipart-image-galleries-imagekb-com-lKYgP0-clipart.png", 30, 550);
		} else {
			button[1].paint(g);
			g.drawString("Cherry: http://opengameart.org/content/fruits", 30, 250);
			g.drawString("Wall: http://opengameart.org/content/hand-painted-stone-wall-texture", 30, 325);
			g.drawString("Back & Next: Button: http://opengameart.org/content/casual-game-button-pack", 30, 400);
			g.drawString("Snake: https://pixabay.com/sv/orm-gr%C3%B6n-cartoon-spotted-svans-306109/", 30, 475);
			g.drawString("Background: http://opengameart.org/content/tower-defense-grass-background", 30, 550);
		}
	}
}
