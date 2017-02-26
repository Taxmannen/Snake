package model;

import java.util.concurrent.CopyOnWriteArrayList;

import utils.Random;

public class Board implements EntityObserver {

	private ControlledCharacter player;
	private Entity[][] gameField; // A matrix/grid of the game
	private CopyOnWriteArrayList<Entity> entityList;
	private int width;
	private int height;
	private int cellSize;
	private int spawnPowerUp = 0;
	
	private String highScoreHolder;
	private int highScore;

	/**
	 * Creates the logic side of the game field
	 * @param cellSize sets how large the gui will draw every 'cell' of the gameField
	 */
	public Board(int cellSize) {
		this.cellSize = cellSize;
		entityList = new CopyOnWriteArrayList<Entity>();
		try {
			parseNameAndHighScore(new FR().readingFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return player
	 */
	public ControlledCharacter getPlayer() {
		return player;
	}

	/**
	 * @return cellSize
	 */
	public int getCellSize() {
		return cellSize;
	}

	/**
	 * Get list of all entities currently on board.
	 * @return list of entities
	 */
	public CopyOnWriteArrayList<Entity> getEntityList() {
		return entityList;
	}

	/**
	 * get value of the specified cell in the matrix.
	 * 
	 * @param row
	 * @param column
	 * @return Entity or null
	 */
	public Entity getValueAtIndex(int row, int column) {
		return gameField[row][column];
	}
	
	/**
	 * 
	 * @return name of player with highscore
	 */
	public String getHighScoreHolder() {
		return highScoreHolder;
	}
	
	/**
	 * 
	 * @return highscore
	 */
	public int getHighScore() {
		return highScore;
	}
	
	/**
	 * instantiates the matrix with player and other entities
	 * 
	 * @param width
	 * @param height
	 */
	public void createGameField(int width, int height) {
		this.width = width / cellSize;
		this.height = height / cellSize;
		gameField = new Entity[this.width][this.height];
		createStartEntities();
		player.registerObserver(this);
	}


	/** 
	 * Add ControlledCharacter to the board
	 * @param player
	 */
	public void addPlayer(ControlledCharacter player) {
		this.player = player;
		addEntity(this.player);
	}

	/**
	 * Add game object to the board
	 * @param e game object to add
	 */
	public void addEntity(Entity e) {
		entityList.add(e);
		toField(e);
	}

	/**
	 * Remove game object from entity list
	 * @param e game object to remove
	 */
	public void removeFromList(Entity e) {
		entityList.remove(e);
	}
	
	/**
	 * Remove game object from game
	 * @param e
	 */
	public void removeEntity(Entity e) {
		for (int[] pair : e.getCoordinates()) {
			gameField[pair[0]][pair[1]] = null;
		}
		removeFromList(e);
	}

	/**
	 * Update positions of objects on board and checks collision
	 */
	public void update(ControlledCharacter player) {
		if (!player.isAlive() && player.getScore() > highScore) {
            FW write = new FW();
            write.writeFile(player.getScore());
            removeEntity(player);
            return; 
        }
		
		ChangeObject update = player.getUpdateSuggestion();
		if (update.e != null) {
			outOfBoundsCorrection(update);
			if (!isEmptyCell(update.x, update.y)) {
				Entity entity = gameField[update.x][update.y];
				resolveCollision(entity);
			}
		}
		CopyOnWriteArrayList<int[]> pair = new CopyOnWriteArrayList<int[]>();
		int[] XY = new int[] { update.x, update.y };
		pair.add(XY);
		toField(update.e, pair);
		
		if(spawnPowerUp >= 20) {
			Entity entity = gameField[update.x][update.y];
			makeNew(entity);
			spawnPowerUp = 0;
		}
		
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i) instanceof PowerUp) {
				PowerUp pUp = (PowerUp) entityList.get(i);
				if(System.currentTimeMillis() - pUp.timer >= 3000) {
					removeEntity(entityList.get(i));
				}
			}
		}
	}
	
	/**
	 * make apple at random empty boardposition
	 */
	public void makeApple() {
		int[] emptyCell = randomEmptyCell();
		Entity entity = new Apple(emptyCell[0], emptyCell[1], cellSize, cellSize);
		addEntity(entity);
	}
	
	private void createStartEntities() {
		addPlayer(new Worm(width / 2, height / 2, cellSize, cellSize, 5));
		addEntity(new Apple(width / 3, height / 3, cellSize, cellSize));
	}
	
	private void toField(Entity e) {
		for (int[] pair : e.getCoordinates()) {
			gameField[pair[0]][pair[1]] = e;
		}
	}
	
	private void toField(Entity e, CopyOnWriteArrayList<int[]> coordinates) {
		for (int[] pair : coordinates) {
			gameField[pair[0]][pair[1]] = e;
		}
	}
	
	private void resolveCollision(Entity entity) {
		player.collision(entity);
		if (entity instanceof Edible) {
			if(entity instanceof Apple)
				removeEntityAndMakeNew(entity);
			else {
					removeFromList(entity);
					Sound sound = new Sound("PowerUp.wav");
					sound.playSoundOnce();
					player.collision(entity);
			}
			spawnPowerUp += Random.integer(1, 20);
		}
	}
	
	/* Creates a new power up when being called.
	 *@param entity.
	 */
	private void makeNew(Entity entity) {
		int[] emptyCell = randomEmptyCell();
		entity = new PowerUp(emptyCell[0], emptyCell[1], cellSize, cellSize);
		addEntity(entity);
	}
	
	private void removeEntityAndMakeNew(Entity entity) {
		removeFromList(entity);
		makeApple();
	}
	

	private boolean isEmptyCell(int x, int y) {
		return gameField[x][y] == null;
	}

	private int[] randomEmptyCell() {
		while (true) {
			int x = Random.integer(1, width - 2);
			int y = Random.integer(6, height - 2);
			if (isEmptyCell(x, y))
				return new int[] { x, y };
		}
	}
	
	private void outOfBoundsCorrection(ChangeObject c) {
		if (c.x >= width - 1) {
			c.x = 0;
		} else if (c.x < 0) {
			c.x = width - 1;
		}
		if (c.y >= height) {
			c.y = 2;
		} else if (c.y < 1) {
			c.y = height - 1;
		}
	}
	
	private void parseNameAndHighScore(String nameAndScore) {
		try {
			System.out.println(nameAndScore);
			String[] splitter = nameAndScore.split("\\s+");
			highScoreHolder = splitter[1];
			highScore = Integer.parseInt(splitter[2]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			highScoreHolder = "Fahad";
			highScore = 10;
		}
	}
	

}
