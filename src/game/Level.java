package game;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Level {
	private int _blockWidth, _blockHeight;
	private int _width, _height;
	private ArrayList<Explosion> _explosions = new ArrayList<Explosion>();
	private Block[][] _levelData;
	private Point _playerStart = new Point(0, 0);
	
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
	
	public ArrayList<Explosion> getExplosions() {
		return _explosions;
	}
	
	public Point getPlayerStart() {
		return _playerStart;
	}
	
	public void load(int level) {
		String filePath = String.format("data/%d.level", level);
		try {
			ArrayList<String> lines = new ArrayList<String>();
			Scanner scanner = new Scanner(new File(filePath));
			while (scanner.hasNext()) {
				lines.add(scanner.next());
			}
			scanner.close();
			
			int y = 0;
			Iterator<String> row = lines.iterator();
			while (row.hasNext()) {
				String current = row.next();
				for (int x = 0; x < current.length(); ++x) {
					int type = Block.typeForChar(current.charAt(x));
					_levelData[x][y].setType(type);
					
					if (type == Block.BLOCK_TYPE_PLAYER_SPAWN) {
						_playerStart.setLocation(x, y);
					}
				}
				
				++y;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
