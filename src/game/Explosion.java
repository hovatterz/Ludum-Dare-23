package game;

import java.awt.Point;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Explosion {
	private Animation _animation;
	private Point _position;
	
	public Explosion(SpriteSheet spriteSheet, Point position) {
		_animation = new Animation(spriteSheet, 0, 2, 5, 2, true, 10, true);
		_animation.setLooping(false);
		
		_position = position;
	}
	
	public Animation getAnimation() {
		return _animation;
	}
	
	public void draw() {
		_animation.draw(_position.x * Game.BLOCK_WIDTH, _position.y * Game.BLOCK_HEIGHT);
	}
}
