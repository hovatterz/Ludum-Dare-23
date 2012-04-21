package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinState extends BasicGameState {
	
	public static final int ID = 30;
	
	private StateBasedGame _game;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		_game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		String message = String.format("You win! Your score: %d", Game.player.getPoints());
		int width = g.getFont().getWidth(message);
		int height = g.getFont().getHeight(message);
		g.drawString(message, container.getWidth() / 2 - width / 2, container.getHeight() / 2 - height / 2);
		
		message = "Press enter/return to quit.";
		width = g.getFont().getWidth(message);
		height = g.getFont().getHeight(message);
		g.drawString(message, container.getWidth() / 2 - width / 2, container.getHeight() / 2 - height / 2 + 20);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_RETURN || key == Input.KEY_ENTER) {
			_game.getContainer().exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
