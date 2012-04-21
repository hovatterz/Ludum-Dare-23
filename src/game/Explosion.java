package game;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Explosion {
	private Animation _animation;
	private ArrayList<Point> _positions = new ArrayList<Point>();
	
	public Explosion(SpriteSheet spriteSheet) {
		_animation = new Animation(spriteSheet, 0, 2, 5, 2, true, 10, true);
		_animation.setLooping(false);
	}
	
	public Animation getAnimation() {
		return _animation;
	}
	
	public ArrayList<Point> getPositions() {
		return _positions;
	}
	
	public void addPosition(Point position) {
		_positions.add(position);
	}
	
	public void draw() {
		for (Point position : _positions) {
			_animation.draw(position.x * Game.BLOCK_WIDTH, position.y * Game.BLOCK_HEIGHT);
		}
	}
}
