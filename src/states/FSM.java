package states;

public class FSM {
	public static State currentState;
	
	public FSM() {
		currentState = null;
		init();
	}
	
	/* initializes the FSM.
	 */
	private void init() {
		State firstState = new MenuState(this);
		setState(firstState);
	}
	
	/* Sets the current state.
	 * @param state - the state you are currently on.
	 */
	public static void setState(State s) {
		currentState = s;
	}
}
