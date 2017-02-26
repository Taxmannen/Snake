package main;

import model.*;
import states.FSM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image dbi;
	private Graphics dbg;
	public static Level level = new Level();
	public static int cell = 16;
	public static Board board = new Board(cell);
	
	static boolean pause = false;
	private boolean play = false;
	Thread thread;
	Image background;
	
	// Variables for delta time ticker method.
	private long timeBefore; 
	final double ticks = 20D;
	double ns = 1000000000 / ticks;
	double delta = 0;
	private Font myFont = new Font ("Helvetica", 1, 15);
	
	public GUI() {
		int width = 1280, height = 760;
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setTitle("Snake");
		setVisible(true);
		addKeyListener(new Keyboard());
		addMouseListener(new Mouse());
		background = new ImageIcon("assets/Background.jpg").getImage();
		level.generateLevel();
		board.createGameField(width, height);
		gameStart();
	}
	
	/**
	 * Draw current score and highscore to frame
	 * @param g
	 */
	public void drawScore (Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		g.drawString("Score: " + board.getPlayer().getScore(), 40, 75);
		g.drawString("Highscore: " + board.getHighScoreHolder() + " " + board.getHighScore() , 40, 100);
	}
	
	/** Clears the graphics when a object is being moved.
	 * @param Graphics.
	 */
	public void paint(Graphics g) {
		dbi = createImage(getWidth(), getHeight());
		dbg = dbi.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbi, 0, 0, this);
		
	}
	
	/** Paint the components of the current state
	 *@param Graphics.
	 */
	public synchronized void paintComponent(Graphics g) {
		if(FSM.currentState.getName() == "PlayState") {
			play = true;
			paintGame(g);
			drawScore(g);
		}
		
		if(pause) {
			Font myFont = new Font ("Times New Roman", 1, 75);
			g.setColor(Color.black);
			g.setFont(myFont);
			g.drawString("The Game Is Paused!", 300, 400);
		}
		FSM.currentState.paint(g);
		repaint();
	}
	
	/**
	 * method that checks when to update the game
	 */
	@Override
	public void run() {	
		timeBefore = System.nanoTime();
		while(true){
			long timeNow = System.nanoTime();
			delta += (timeNow - timeBefore) / ns;
			timeBefore = timeNow;
			if (delta >= 1 ) {
				if (!pause)
					tick();
				delta--;
			}
		}
	}
	
	/** Paints the background and calls paintAllEntites.
	 * @param Graphics.
	 */
	private void paintGame(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 760, this); 
		paintAllEntities(g);
	}
	
	/** Paint the entities.
	 * @param Graphics.
	 */
	private void paintAllEntities(Graphics g) {
			
		for (Entity e : board.getEntityList()) {
			e.paint(g, board.getCellSize());
		}
	}
	
	
	private void gameStart() {
		start();
	}
	
	private void start() {
		if(play = true) {
			thread = new Thread(this, "Game loop");
			thread.start();
		}
	}
	
	private void tick() {
		Keyboard.handleEvent();
		board.getPlayer().updatePosition();
	}
	
}