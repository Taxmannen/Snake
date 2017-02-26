package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import model.Sound;
import states.Button;
import states.CreditsState;
import states.FSM;
import states.MenuState;

public class Mouse extends MouseAdapter {
	
	/* A function that checks if the mouse is being pressed on a button.
	 * @param MouseEvent - an event that checks if the mouse is being used.
	 */
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		if(code == 1) {
			if(FSM.currentState.getName() == "MenuState") {
				for(Button i:MenuState.button) {
					if(e.getX() > i.getX() && e.getX() < i.getX() + i.getWidth() && e.getY() > i.getY() && e.getY() < i.getY() + i.getHeight()) {
						FSM.setState(i.getState());
					}
				}
			}
			
			if(FSM.currentState.getName() == "CreditsState") {
				for(Button i:CreditsState.button) {
						if(e.getX() > i.getX() && e.getX() < i.getX() + i.getWidth() && e.getY() > i.getY() && e.getY() < i.getY() + i.getHeight()) {
							if(CreditsState.page == 1) {
								CreditsState.page ++;
							} else {
								CreditsState.page --;
						}
					}
				}
			}
		}
	}
}