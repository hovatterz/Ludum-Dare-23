package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayingState extends BasicGameState {
	
	public static final int ID = 10;
	
	private Level _level;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		_level = new Level(container.getWidth() / Game.BLOCK_WIDTH, 
				container.getHeight() / Game.BLOCK_HEIGHT, 
				Game.BLOCK_WIDTH, Game.BLOCK_HEIGHT);
		_level.load(Game.currentLevel);
		
		Game.player.setPosition(0, 0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int blockWidth = container.getWidth() / Game.BLOCK_WIDTH;
		int blockHeight = container.getHeight() / Game.BLOCK_HEIGHT;
		
		Game.terrainSpriteSheet.startUse();
		for (int x = 0; x < blockWidth; ++x) {
			for (int y = 0; y < blockHeight; ++y) {
				Block block = _level.blockAt(x, y);
				Game.terrainSpriteSheet.renderInUse(x * Game.BLOCK_WIDTH, y * Game.BLOCK_WIDTH, 
						block.getSpritePosition().x, block.getSpritePosition().y);
			}
		}
		Game.terrainSpriteSheet.endUse();
		
		g.setColor(Color.white);
		g.drawRect(Game.player.getPosition().x * Game.BLOCK_WIDTH, 
				Game.player.getPosition().y * Game.BLOCK_HEIGHT, 32, 32);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (Game.player.getPosition().y == (container.getHeight() / Game.BLOCK_HEIGHT) - 1) {
			game.enterState(LevelInterstitialState.ID);
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_LEFT) {
			Game.player.move(-1, 0, _level);
		} else if (key == Input.KEY_RIGHT) {
			Game.player.move(1, 0, _level);
		} else if (key == Input.KEY_DOWN) {
			Game.player.move(0, 1, _level);
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
