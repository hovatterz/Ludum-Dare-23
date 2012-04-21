package game;

import java.awt.Point;

public class Player {
	private int _health = 100;
	private int _points = 0;
	private Point _position = new Point(0, 0);
	
	public int getHealth() {
		return _health;
	}
	
	public int getPoints() {
		return _points;
	}
	
	public Point getPosition() {
		return _position;
	}
	
	public void applyPoints(int points) {
		_points += points;
	}
	
	public void move(int dx, int dy, Level level) {
		Point newPosition = new Point(_position);
		newPosition.translate(dx, dy);
		
		if (newPosition.x < 0 || newPosition.x >= level.getWidth() 
				|| newPosition.y < 0 || newPosition.y >= level.getHeight()) { 
			return;
		}
		
		Block block = level.blockAt(newPosition.x, newPosition.y);
		if (block.isPassable()) {
			if (block.getType() == Block.BLOCK_TYPE_BOMB) {
				Explosion explosion = new Explosion(Game.spriteSheet);
				explosion.addPosition(new Point(newPosition.x, newPosition.y - 1));
				explosion.addPosition(new Point(newPosition.x, newPosition.y + 1));
				explosion.addPosition(new Point(newPosition.x - 1, newPosition.y));
				explosion.addPosition(new Point(newPosition.x + 1, newPosition.y));
				explosion.addPosition(new Point(newPosition));
				level.getExplosions().add(explosion);
			} else if (block.getType() == Block.BLOCK_TYPE_CITY) {
				Explosion explosion = new Explosion(Game.spriteSheet);
				explosion.addPosition(new Point(newPosition));
				level.getExplosions().add(explosion);
			} else {
				_position = newPosition;
				this.applyPoints(block.getPoints());
				block.setType(Block.BLOCK_TYPE_NONE);
			}
		}
	}
	
	public void setPosition(int x, int y) {
		_position.setLocation(x, y);
	}
}
