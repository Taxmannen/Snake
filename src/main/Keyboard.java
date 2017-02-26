package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import states.FSM;
import states.MenuState;

public class Keyboard extends KeyAdapter {	
	public static String userInput;
	
	/* A function that checks if the keyboard is being pressed on a specific key.
	 * @param KeyEvent - an event that checks if the keyboard is being used.
	 */
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_RIGHT) {
				userInput = "right";
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				userInput = "left";
			}
			
			if(e.getKeyCode() == KeyEvent.VK_P) {
				if(FSM.currentState.getName() == "PlayState") {
					GUI.pause = !GUI.pause;
				}
			}
			
			if(code == KeyEvent.VK_ESCAPE) {
				FSM.setState(new MenuState(null));
			}
			
			if(code == KeyEvent.VK_SPACE) {
				GUI.level.setLevel(GUI.level.getLevel()+1);	
				GUI.level.generateLevel();
			}
		}
		
		/*
		 * 
		 */
		public static void handleEvent() {
			if (userInput == "right") {
				GUI.board.getPlayer().turnRight();
			}
			else if(userInput == "left") {
				GUI.board.getPlayer().turnLeft();
			}
			userInput = "nothing";
		}
	}	