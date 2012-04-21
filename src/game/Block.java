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
	
	private int _points;
	private int _type;
	private Point _spritePosition;
	private Rectangle _bounds;
	
	public Block(int type, int x, int y, int width, int height) {
		_bounds = new Rectangle(x, y, width, height);
		
		setType(type);
	}
	
	public Rectangle getBounds() {
		return _bounds;
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
	
	public void setType(int type) {
		Random rand = new Random();
		
		_type = type;
		switch (type) {
		case BLOCK_TYPE_NONE:
			_points = 0;
			_spritePosition = new Point(0, 0);
			break;

		case BLOCK_TYPE_GRASS:
			_points = 1;
			_spritePosition = new Point(1, 0);
			break;
			
		case BLOCK_TYPE_DIRT:
			_points = 2;
			_spritePosition = new Point(2, 0);
			break;
			
		case BLOCK_TYPE_STONE:
			_points = 3;
			_spritePosition = new Point(3, 0);
			break;
			
		case BLOCK_TYPE_CITY:
			_points = -3;
			_spritePosition = new Point(rand.nextInt(2), 1);
			break;
		}
	}
}
