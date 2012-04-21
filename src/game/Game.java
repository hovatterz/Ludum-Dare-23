package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	
	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;

	public static int currentLevel = 1;
	public static Player player = new Player();
	public static SpriteSheet terrainSpriteSheet;

	public Game() {
		super("PlanetSaver - Ludum Dare 23 - Zack Hovatter");
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {	
		String filePath = String.format("data/terrain_%dx%d.png", BLOCK_WIDTH, BLOCK_HEIGHT);
		terrainSpriteSheet = new SpriteSheet(filePath, BLOCK_WIDTH, BLOCK_HEIGHT);
		
		addState(new PlayingState());
		addState(new LevelInterstitialState());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(800, 608, false);
		app.start();
	}

}
