package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;
	
	@Before
	public void instantiate() {
		board = new Board(1);
		createField(512, 512);
	}

	@Test
	public void createPlayer() {
		board.addPlayer((createPlayerObject(45, 45)));
	}
	
	@Test
	public void playerAddedToField() {
		board.addPlayer((createPlayerObject(64, 64)));
	}
	
	@Test
	public void entityAddedToField() {
		Entity apple = new Apple(42, 42, 8, 8);
		Entity wall = new Walls(42, 42, 8, 8);
		board.addEntity(apple);
		board.addEntity(wall);
	}
	
	@Test
	public void emptyIndex() {
		board.addPlayer((createPlayerObject(90, 90)));
		Assert.assertEquals(null, board.getValueAtIndex(89, 90));
	}
	
	@Test
	public void entityCoordinatesAndBoardCoordinatesMatch() {
		int x = 32;
		int y = 16;
		ControlledCharacter worm = createPlayerObject(x, y);
		worm.registerObserver(board);
		isIndexMatch(worm, x, y);
		isIndexMatch(worm, x, y+1);
		isIndexMatch(worm, x, y+2);
		worm.updatePosition();
		isIndexMatch(worm, x, y-1);
		isIndexMatch(worm, x, y);
		isIndexMatch(worm, x, y+1);
		worm.turnLeft();
		worm.updatePosition();
		isIndexMatch(worm, x-1, y-1);		
	}
	
	@Test
	public void playerMoves() {
		int x = 256;
		int y = 256;
		ControlledCharacter worm = createPlayerObject(x, y);
		board.addPlayer(worm);
		worm.registerObserver(board);
		worm.updatePosition();
		
		isIndexMatch(worm, x, y-1);
		isIndexMatch(worm, x, y);
		isIndexMatch(worm, x, y+1);
		isIndexMatch(null, x, y/10);
		isIndexMatch(null, x/10, y/25);
		isIndexMatch(null, x+5, y+5);
	}
	
	@Test
	public void playerEatsAnApple() {
		int x = 200;
		int y = 130;
		ControlledCharacter worm = createPlayerObject(x, y);
		Entity apple = new Apple(x, y-1, 8, 8);
		board.addPlayer(worm);
		board.addEntity(apple);
		worm.registerObserver(board);
		worm.updatePosition();
		worm.updatePosition();
		Assert.assertEquals(4, worm.getCoordinates().size()); // IF player eats apple, player grows
	}
	
	@Test
	public void outOfBoundsScreenWrap() {
		int x = 50;
		int y = 0;
		ControlledCharacter worm = createPlayerObject(x, y);
		board.addPlayer(worm);
		worm.registerObserver(board);
		for (int i = 0; i < 5; i++) {
			worm.updatePosition();
			int newX = getHeadCoordinates(worm)[0];
			int newY = getHeadCoordinates(worm)[1];
			Entity cell = board.getValueAtIndex(newX, newY);
			Assert.assertEquals(worm, cell);
		}
	}

	
	private int[] getHeadCoordinates(ControlledCharacter worm) {
		return worm.getCoordinates().get(0);
	}
	
	private void createField(int x, int y) {
		board.createGameField(x, y);
	}
	
	private ControlledCharacter createPlayerObject(int x, int y) {
		return new Worm(x, y, 8, 8, 3);
	}
	
	public void isIndexMatch(Entity entity, int row, int column) {
		Assert.assertEquals(entity, board.getValueAtIndex(row, column));
	}
}
