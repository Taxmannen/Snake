package model;

import main.GUI;

public class Level {
	
	private int level = 0;
	private int size = 3;
	private int score = 0;
	
	public Level() {
		level = 1;
	}
	
	/**
	 * Creates walls in different formations depending on score
	 */
	public void generateLevel () {
		if(level == 1) {
			size=2;
		}
		
		if(level == 2) {
			GUI.board.addEntity(new Walls(20, 3, 40, 2)); 	// Top wall
			GUI.board.addEntity(new Walls(20, 45, 40, 2)); 	// Bottom wall
			GUI.board.addEntity(new Walls(1, 13, 2, 23));	// Left wall
			GUI.board.addEntity(new Walls(78, 13, 2, 23)); 	// Right wall
			resetApple(); // ad hoc solution to apples disappearing because of walls
		}
		
		if(level == 3) {
			removeWalls();
			GUI.board.addEntity(new Walls(1, 3, 79, 2)); 	// Top
			GUI.board.addEntity(new Walls(1, 45, 79, 2)); 	// Bottom
			GUI.board.addEntity(new Walls(1, 3, 2, 43));	// Left
			GUI.board.addEntity(new Walls(78, 3, 2, 43));	// Right
			resetApple();
		}
			
		if(level >= 4 && level < 7) {
			GUI.board.addEntity(new Walls(1+size, 3+size, 79-size, 2)); 	// Top
			GUI.board.addEntity(new Walls(1+size, 45-size, 79-size, 2)); 	// Bottom
			GUI.board.addEntity(new Walls(1+size, 3+size, 2, 43-size));		// Left
			GUI.board.addEntity(new Walls(78-size, 3+size, 2, 43-size));	// Right
			size+=2;
			resetApple();
		}
		if (level == 8) {
			removeWalls(); 
		}
	}
	/**
	 * removes all walls from board
	 */
	public void removeWalls() { 
		for(Entity e : GUI.board.getEntityList()) {
			if(e instanceof Walls) {
				GUI.board.removeEntity(e);
				
			}
		}
	}
	/**
	 * removes apple from board and creates a new one, last moment solution to walls removing apples from board.
	 */
	private void resetApple() {
		for (Entity e : GUI.board.getEntityList()) {
			if (e instanceof Apple) {
				GUI.board.removeEntity(e);
			}
		}
		GUI.board.makeApple();
	}
	
	public int getLevel()           { return level;       }
	public void setLevel(int level) { this.level = level; }

	public int getScore()           { return score;       }
	public void setScore(int score) { this.score = score; }
}
