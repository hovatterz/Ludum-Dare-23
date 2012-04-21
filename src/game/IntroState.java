package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class IntroState extends BasicGameState {
	
	public static final int ID = 50;
	
	private StateBasedGame _game;
	
	private final String _introMessage = "In an alternate reality of a parallel universe there exists a civilization\n" + 
			"of tiny beings. These beings inhabit tiny worlds rich with rubies - the\n" + 
			"galaxies most valuable resource.\n" + 
			"\n" + 
			"It is your job to attack these planets and plant bombs at their cores. \n" + 
			"The blast should explode the planets and eject massive amounts of rubies \n" + 
			"into space for our collector ships.\n" + 
			"\n" + 
			"Unfortunately, we have to outfit you in some very old scuba gear. \n" + 
			"Good luck.\n" + 
			"\n" + 
			"\n" + 
			"Press enter/return to continue.\n";

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		_game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString(_introMessage, 10, 50);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_RETURN || key == Input.KEY_ENTER) {
			_game.enterState(LevelInterstitialState.ID);
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
