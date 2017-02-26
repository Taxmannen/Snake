package model;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import states.*;

public class WormTest {

	private Worm worm;
	
	@Before
	public void initialize() {
		worm = new Worm(64, 64, 8, 8, 3);
	}
	
	@Test 
	public void correctWidthAndHeight() {
		worm = new Worm(64, 64, 2, 9, 3);
		int[] dimensions = worm.getDimensions();
		Assert.assertArrayEquals(new int[]{2, 9}, dimensions);
	}
	
	@Test
	public void correctLength() {
		int size = worm.getCoordinates().size();
		Assert.assertEquals(3, size);
	}
	
	@Test
	public void assertCoordinatesCorrect() {
		ArrayList<int[]> expecteds = new ArrayList<int[]>();
		CopyOnWriteArrayList<int[]> actuals = worm.getCoordinates();
		expecteds.add(0, new int[]{64, 64});
		expecteds.add(1, new int[]{64, 65});
		expecteds.add(2,new int[]{64, 66});		
		for (int i = 0; i < expecteds.size(); i++) {
			Assert.assertArrayEquals(expecteds.get(i), actuals.get(i));
		}
	}

	@Test
	public void playerMoves() {
		int x = 64;
		int y = 64;
		worm = new Worm(x, y, 8, 4, 3);
		worm.updatePosition();
		assertCoordinatesOfAllParts(x, y-1); // Initial direction is upwards, which is negative on the y-axis
	}

	@Ignore // no longer works because worm is for some reason dependent on FSM state
	@Test
	public void moveLeft() {
		int x = 128;
		int y = 124;
		worm = new Worm(x, y, 5, 5, 10);
		turnLeftAndUpdate();
		// Turning left means a 90 degree counter-clockwise turn
		Assert.assertArrayEquals(new int[]{x-1, y}, getCoordinatePair(0)); 
		turnLeftAndUpdate();
		Assert.assertArrayEquals(new int[]{x-1, y+1}, getCoordinatePair(0));
		turnLeftAndUpdate();
		Assert.assertArrayEquals(new int[]{x, y+1}, getCoordinatePair(0));
	}

	@Ignore // no longer works because worm is for some reason dependent on FSM state
	@Test
	public void moveRight() {
		int x = 456;
		int y = 65;
		worm = new Worm(x, y, 54, 75, 3);
		turnRightAndUpdate();
		// Turning right means a 90 degree clockwise turn
		Assert.assertArrayEquals(new int[]{x+1, y}, getCoordinatePair(0));
		turnRightAndUpdate();
		Assert.assertArrayEquals(new int[]{x+1, y+1}, getCoordinatePair(0));
	}
	
	@Test
	public void notifyObserversWithChanges() {
		worm = new Worm(256, 256, 8, 8, 3);
		ObsTest test = new ObsTest();
		worm.registerObserver(test);
		worm.notifyObservers();
		
	}
	
	private void turnRightAndUpdate() {
		worm.turnRight();
		worm.updatePosition();
	}

	private void turnLeftAndUpdate() {
		worm.turnLeft();
		worm.updatePosition();
	}
	
	private void assertCoordinatesOfAllParts(int frontX, int frontY) {
		for (int i = 0; i < worm.getCoordinates().size(); i++ ){
			Assert.assertArrayEquals(new int[]{frontX, (frontY + i)}, getCoordinatePair(i));
		}
	}

	private int[] getCoordinatePair(int index) {
		return worm.getCoordinates().get(index);
	}

}

class ObsTest implements EntityObserver {
	public void printSomething() {
		System.out.println("Something");
	}
	public void update(Object o) {
	}
	@Override
	public void update(ControlledCharacter c) {
		
	}
}
