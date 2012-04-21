package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	
	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;
	public static final int NUM_LEVELS = 2;

	public static int currentLevel = 0;
	public static Player player = new Player();
	public static Animation explosionAnimation;
	public static SpriteSheet spriteSheet;

	public Game() {
		super("PlanetSaver - Ludum Dare 23 - Zack Hovatter");
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		container.getInput().enableKeyRepeat();
		
		spriteSheet = new SpriteSheet("data/tiles.png", BLOCK_WIDTH, BLOCK_HEIGHT);
		explosionAnimation = new Animation(spriteSheet, 0, 2, 5, 2, true, 100, true);
		explosionAnimation.setLooping(false);
		
		addState(new IntroState());
		addState(new LevelInterstitialState());
		addState(new PlayingState());
		addState(new WinState());
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
