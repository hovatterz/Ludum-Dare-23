package game;

import java.util.ArrayList;
import java.util.Random;

public class Level {
	private int _blockWidth, _blockHeight;
	private int _width, _height;
	private Block[][] _levelData;
	
	public Level(int width, int height, int blockWidth, int blockHeight) {
		_width = width;
		_height = height;
		_blockWidth = blockWidth;
		_blockHeight = blockHeight;
		
		_levelData = new Block[_width][_height];
		for (int x = 0; x < _width; ++x) {
			for (int y = 0; y < _height; ++y) {
				_levelData[x][y] = new Block(Block.BLOCK_TYPE_NONE, 
						x * _blockWidth, y * _blockHeight, 
						_blockWidth, _blockHeight);
			}
		}
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public void generate() {
		Random rand = new Random();
		
		for (int x = 0; x < _width; ++x) {
			int type = rand.nextInt(3);
			
			if (type == 0) {
				_levelData[x][4].setType(Block.BLOCK_TYPE_CITY);
			}
			
			_levelData[x][5].setType(Block.BLOCK_TYPE_GRASS);
		}
		
		for (int x = 0; x < _width; ++x) {
			for (int y = 6; y < _height - 3; ++y) {
				_levelData[x][y].setType(Block.BLOCK_TYPE_DIRT);
			}
			
			for (int y = _height - 3; y < _height; ++y) {
				_levelData[x][y].setType(Block.BLOCK_TYPE_STONE);
			}
		}
	}
	
	public Block blockAt(int x, int y) {
		return _levelData[x][y];
	}
	
	public ArrayList<Block> blocksNear(int x, int y) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		blocks.add(_levelData[x][y]);
		if (x - 1 >= 0) { blocks.add(_levelData[x - 1][y]); }
		if (x + 1 < _width) { blocks.add(_levelData[x + 1][y]); }
		if (y - 1 >= 0) { blocks.add(_levelData[x][y - 1]); }
		if (y + 1 < _height) { blocks.add(_levelData[x][y + 1]); }
		if (x - 1 >= 0 && y - 1 >= 0) { blocks.add(_levelData[x - 1][y - 1]); }
		if (x - 1 >= 0 && y + 1 < _height) { blocks.add(_levelData[x - 1][y + 1]); }
		if (x + 1 < _width && y - 1 >= 0) { blocks.add(_levelData[x + 1][y - 1]); }
		if (x + 1 < _width && y + 1 < _height) { blocks.add(_levelData[x + 1][y + 1]); }
		
		return blocks;
	}
}
