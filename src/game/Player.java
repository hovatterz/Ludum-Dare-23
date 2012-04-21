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
		if (_position.x + dx < 0 || _position.x + dx >= level.getWidth() 
				|| _position.y + dy < 0 || _position.y + dy >= level.getHeight()) { 
			return;
		}
		
		_position.translate(dx, dy);
		
		Block block = level.blockAt(_position.x, _position.y);
		this.applyPoints(block.getPoints());
		block.setType(Block.BLOCK_TYPE_NONE);
	}
	
	public void setPosition(int x, int y) {
		_position.setLocation(x, y);
	}
}
