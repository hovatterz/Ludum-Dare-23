package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Block {
	public static final int BLOCK_TYPE_NONE = 0;
	public static final int BLOCK_TYPE_GRASS = 1;
	public static final int BLOCK_TYPE_DIRT = 2;
	public static final int BLOCK_TYPE_STONE = 3;
	public static final int BLOCK_TYPE_CITY = 4;
	public static final int BLOCK_TYPE_RUBY = 5;
	public static final int BLOCK_TYPE_PLAYER_SPAWN = 6;
	
	private boolean _passable;
	private int _points;
	private int _type;
	private Point _spritePosition;
	private Rectangle _bounds;
	
	public Block(int type, int x, int y, int width, int height) {
		_bounds = new Rectangle(x, y, width, height);
		
		setType(type);
	}
	
	public static int typeForChar(char symbol) {
		if (symbol == '1') {
			return BLOCK_TYPE_GRASS;
		} else if (symbol == '2') {
			return BLOCK_TYPE_DIRT;
		} else if (symbol == '3') {
			return BLOCK_TYPE_STONE;
		} else if (symbol == '4') {
			return BLOCK_TYPE_CITY;
		} else if (symbol == 'R') {
			return BLOCK_TYPE_RUBY;
		} else if (symbol == 'P') {
			return BLOCK_TYPE_PLAYER_SPAWN;
		} else {
			return BLOCK_TYPE_NONE;
		}
	}
	
	public boolean isPassable() {
		return _passable;
	}
	
	public int getPoints() {
		return _points;
	}
	
	public int getType() {
		return _type;
	}
	
	public Point getSpritePosition() {
		return _spritePosition;
	}
	
	public Rectangle getBounds() {
		return _bounds;
	}
	
	public void setType(int type) {
		Random rand = new Random();
		
		_type = type;
		switch (type) {
		case BLOCK_TYPE_PLAYER_SPAWN:
		case BLOCK_TYPE_NONE:
			_passable = true;
			_points = 0;
			_spritePosition = new Point(0, 0);
			break;

		case BLOCK_TYPE_GRASS:
			_passable = true;
			_points = 1;
			_spritePosition = new Point(1, 0);
			break;
			
		case BLOCK_TYPE_DIRT:
			_passable = true;
			_points = 3;
			_spritePosition = new Point(2, 0);
			break;
			
		case BLOCK_TYPE_STONE:
			_passable = false;
			_points = 0;
			_spritePosition = new Point(3, 0);
			break;
			
		case BLOCK_TYPE_CITY:
			_passable = true;
			_points = -20;
			_spritePosition = new Point(rand.nextInt(2), 1);
			break;
			
		case BLOCK_TYPE_RUBY:
			_passable = true;
			_points = 20;
			_spritePosition = new Point(4, 0);
			break;
		}
	}
}
