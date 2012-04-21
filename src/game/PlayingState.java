package game;

import java.awt.Point;
import java.util.Iterator;

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
		
		Game.player.setPosition(_level.getPlayerStart().x, _level.getPlayerStart().y);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int blockWidth = container.getWidth() / Game.BLOCK_WIDTH;
		int blockHeight = container.getHeight() / Game.BLOCK_HEIGHT;
		
		Game.spriteSheet.startUse();
		
		for (int x = 0; x < blockWidth; ++x) {
			for (int y = 0; y < blockHeight; ++y) {
				Block block = _level.blockAt(x, y);
				Game.spriteSheet.renderInUse(x * Game.BLOCK_WIDTH, y * Game.BLOCK_WIDTH, 
						block.getSpritePosition().x, block.getSpritePosition().y);
			}
		}

		Game.spriteSheet.renderInUse(Game.player.getPosition().x * Game.BLOCK_WIDTH, 
				Game.player.getPosition().y * Game.BLOCK_HEIGHT, 0, 3);
		
		Game.spriteSheet.endUse();
		
		String scoreDisplay = String.format("Score: %d", Game.player.getPoints());
		int width = g.getFont().getWidth(scoreDisplay);
		g.drawString(scoreDisplay, container.getWidth() - width - 10, 10);
		
		for (int x = 0; x < _level.getWidth(); ++x) {
			Block block = _level.blockAt(x, 4);
			if (block.getType() == Block.BLOCK_TYPE_CITY) {
				if (_level.blockAt(x, 5).getType() == Block.BLOCK_TYPE_NONE) {
					Explosion explosion = new Explosion(Game.spriteSheet);
					explosion.addPosition(new Point(x, 4));
					_level.getExplosions().add(explosion);
				}
			}
		}
		
		Iterator<Explosion> iterator = _level.getExplosions().iterator();
		while (iterator.hasNext()) {
			Explosion e = iterator.next();
			if (e.getAnimation().isStopped()) {
				Iterator<Point> posIter = e.getPositions().iterator();
				while (posIter.hasNext()) {
					Point position = posIter.next();
					Block block = _level.blockAt(position.x, position.y);
					Game.player.applyPoints(block.getPoints());
					block.setType(Block.BLOCK_TYPE_NONE);
				}
				
				iterator.remove();
			} else {
				e.draw();
			}
		}
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
