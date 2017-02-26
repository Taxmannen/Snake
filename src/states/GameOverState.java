package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.GUI;
import model.Board;
import model.Sound;

public class GameOverState extends State {
	
	private Font myFont = new Font ("Helvetica", 1, 75);
	private Font myFont2 = new Font ("Helvetica", 1, 50);
	
	public GameOverState(FSM fsm, String name) {
		super(fsm, "GameOverState");
		Sound sound = new Sound("Death.wav");
		sound.changeVolume(-10);
		sound.playSoundOnce();
		reset();
	}
	/* Resets the values from the last game.
	 */
	public void reset() {
		GUI.level.removeWalls();
		GUI.level.setScore(0);
		GUI.level.setLevel(1);
		GUI.level.generateLevel();
		GUI.board = new Board(GUI.cell);
		GUI.board.createGameField(1280, 760);
	}
	
	/* paints the background, and the text for the gameover screen.
	 * @param Graphics. 
	 */
	@Override
	public void paint(Graphics g) {
		Image background;
		background = new ImageIcon("assets/Background.jpg").getImage();
		g.drawImage(background, 0, 0, 1280, 760, null);
		g.setColor(Color.black);
		g.setFont(myFont);
		g.drawString("Game Over!", 400 , 150);
		g.setFont(myFont2);
		g.drawString("Press escape to return to the menu!", 200, 275);
	}
}
