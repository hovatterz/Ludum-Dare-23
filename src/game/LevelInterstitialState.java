package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelInterstitialState extends BasicGameState {
	
	public static final int ID = 20;
	
	private final String SCORE_MESSAGE = "Your score so far: %d";
	private final String CONTINUE_PROMPT = "Press enter/return to continue."; 
	
	private PlayingState _playingState;
	private StateBasedGame _game;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		_game = game;
		_playingState = (PlayingState) game.getState(PlayingState.ID);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		++Game.currentLevel;
		if (Game.currentLevel > Game.NUM_LEVELS) {
			game.enterState(WinState.ID);
		} else {
			_playingState.getLevel().load(Game.currentLevel);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		String intro = _playingState.getLevel().getIntro();
		g.drawString(intro, 10, 50);
		
		if (Game.currentLevel != 1) {
			String message = String.format(SCORE_MESSAGE, Game.player.getPoints());
			int width = g.getFont().getWidth(message);
			int height = g.getFont().getHeight(message);
			g.drawString(message, container.getWidth() / 2 - width / 2, container.getHeight() / 2 - height / 2);
		}
		
		int width = g.getFont().getWidth(CONTINUE_PROMPT);
		int height = g.getFont().getHeight(CONTINUE_PROMPT);
		g.drawString(CONTINUE_PROMPT, container.getWidth() / 2 - width / 2, container.getHeight() / 2 - height / 2 + 20);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_RETURN || key == Input.KEY_ENTER) {
			_game.enterState(PlayingState.ID);
		}
	}

	@Override
	public int getID() {
		return 20;
	}

}
