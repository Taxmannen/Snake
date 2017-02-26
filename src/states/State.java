package states;

import java.awt.Graphics;

public class State {
	@SuppressWarnings("unused")
	private FSM fsm;
	private String name;
	
	public State(FSM fsm, String name) {
		this.fsm = fsm;
		this.name = name;
	}
	
	/* Paint the buttons
	 * @param Graphics.
	 */
	public void paint(Graphics g) {
		
	}
	
	/* Returns the name of the state. 
	 */
	public String getName() { return name; }

	/* Sets the name of the state.
	 * @param name - the name of the state.
	 */
	public void setName(String name) { this.name = name;}
}
