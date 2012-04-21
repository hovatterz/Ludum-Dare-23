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
			_position = newPosition;
			this.applyPoints(block.getPoints());
			
			if (block.getType() == Block.BLOCK_TYPE_BOMB) {
				level.blockAt(_position.x, _position.y - 1).setType(Block.BLOCK_TYPE_NONE);
				level.blockAt(_position.x, _position.y + 1).setType(Block.BLOCK_TYPE_NONE);
				level.blockAt(_position.x - 1, _position.y).setType(Block.BLOCK_TYPE_NONE);
				level.blockAt(_position.x + 1, _position.y).setType(Block.BLOCK_TYPE_NONE);
			}
			
			block.setType(Block.BLOCK_TYPE_NONE);
		}
	}
	
	public void setPosition(int x, int y) {
		_position.setLocation(x, y);
	}
}
