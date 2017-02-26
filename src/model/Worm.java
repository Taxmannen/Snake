package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import main.GUI;
import states.FSM;
import states.GameOverState;
import model.ControlledCharacter;

public class Worm extends ControlledCharacter {
	private ChangeObject change;
	private int width;
	private int height;
	private CopyOnWriteArrayList<int[]> wormBody;
	private int xSpeed = 0;
	private int ySpeed = -1;
	private int score = 0;
	private boolean isGrowing = false;
	private boolean isShrinking = false;
	private boolean isAlive = true;
	
	public Worm(int x, int y, int width, int height, int length) {
		wormBody = new CopyOnWriteArrayList<int[]>();
		this.width = width;
		this.height = height;
		for (int alignmentFactor = 0; alignmentFactor < length; alignmentFactor++) {
			makeBodyPart(x, y + alignmentFactor);
		}
	}
	
	public CopyOnWriteArrayList<int[]> getCoordinates() {
		return wormBody;
	}
	
	public int[] getDimensions() {
		return new int[]{width, height};
	}
	
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void turnLeft() {
		if(FSM.currentState.getName() == "PlayState") {
			int lastX = xSpeed;
			xSpeed = ySpeed;
			ySpeed = lastX * (-1);
			Sound sound = new Sound("Turn.wav");
			sound.changeVolume(-10);
			sound.playSoundOnce();
		}
	}
	
	public void turnRight() {
		if(FSM.currentState.getName() == "PlayState") {
			int lastX = xSpeed;
			xSpeed = ySpeed * (-1);
			ySpeed = lastX;
			Sound sound = new Sound("Turn.wav");
			sound.changeVolume(-10);
			sound.playSoundOnce();
		}
	}
	
	public void updatePosition() {
		if(isAlive) {
			int newX = wormBody.get(0)[0] + (xSpeed);
			int newY = wormBody.get(0)[1] + (ySpeed);
			change = new ChangeObject(newX, newY, this);
			notifyObservers();
			newHeadPosition(change.x, change.y); 
			if (!isGrowing) {
				currentChange(getTailX(), getTailY(), null);
				notifyObservers();
				removeTail();
			}
			if (isShrinking) {
				currentChange(getTailX(), getTailY(), null);
				notifyObservers();
				removeTail();
				isShrinking = false;
			}
			else
				isGrowing = false;
			if(GUI.level.getScore() >= 50) { //
				GUI.level.setLevel(GUI.level.getLevel()+1);	
				GUI.level.generateLevel();
				GUI.level.setScore(0);
			}
		}
	}
	
	protected ChangeObject getUpdateSuggestion() {
		return change;
	}
	
	private void grow() {
		isGrowing = true;
		Sound sound = new Sound("Apple.wav");
		sound.playSoundOnce();
	}
	
	private void shrink() {
		isShrinking = true;
	}
	
	public void notifyObservers() {
		for (EntityObserver o : getObservers()) {
			o.update(this);
		}
	}
	
	public void collision(Entity e) {
		if (e instanceof Apple) {
			score+=(((Edible)e).getValue());
			grow();
			GUI.level.setScore(GUI.level.getScore()+10);
		}
		
		else if (e instanceof Walls) {
			isAlive = false;
			FSM.setState(new GameOverState(null, null));
		}
		
		else if (e instanceof ControlledCharacter) {
			isAlive = false;
			FSM.setState(new GameOverState(null, null));
		}
		
		else if(e instanceof PowerUp) {
			shrink();
			score+=(((Edible)e).getValue());
			GUI.level.setScore(GUI.level.getScore()+10);
		}
	}
	
	@Override
	public synchronized void paint(Graphics g, int spacing) {
		g.setColor(new Color(210, 105, 30));
		ListIterator<int[]> it = wormBody.listIterator();
		while (it.hasNext()) {
			int[] part = it.next();
			g.fillRect(part[0]*spacing, part[1]*spacing, spacing, spacing);
		}
	}

	public int[] getCoordinatePair(int index) {
		return wormBody.get(index);
	}
	
	private void makeBodyPart(int x, int y) {
		wormBody.add(new int[]{x, y});
	}
	
	private void makeBodyPart(int index, int x, int y) {
		wormBody.add(index, new int[]{x, y});
	}
	
	private void newHeadPosition(int newX, int newY) {
		// new coordinates is current position + 1 forward in moving direction
		makeBodyPart(0, newX, newY);
	}
	
	private void removeTail() {
		wormBody.remove(wormBody.size()-1);
	}
	
	private void currentChange(int newX, int newY, ControlledCharacter e) {
		change = new ChangeObject(newX, newY, e);
	}

	private int getTailX() {
		return wormBody.get(wormBody.size()-1)[0];
	}
	
	private int getTailY() {
		return wormBody.get(wormBody.size()-1)[1];
	}

	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }

}
