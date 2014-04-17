package com.example.snake;

public enum Direction {
	
	// Give each direction a value
	UP, 
	DOWN, 
	LEFT, 
	RIGHT;
	
	public static int addToX(final Direction curDirection, final int X) {
		switch(curDirection) {
			// If it goes Up or down X doesn't change
			// Left: X decreases 
			// Right: X Increases
			case UP:
			case DOWN:
				return X;
			case LEFT:
				return X - 1;
			case RIGHT:
				return X + 1;
			default:
				throw new IllegalArgumentException();
		}
	}

	public static int addToY(final Direction curDirection, final int Y) {
		switch(curDirection) {
			// If it goes left or right Y doesn't change
			// Up: Y decreases 
			// Down: Y Increases
			case LEFT:
			case RIGHT:
				return Y;
			case UP:
				return Y - 1;
			case DOWN:
				return Y + 1;
			default:
				throw new IllegalArgumentException();
		}
	}

	public static Direction opposite(final Direction curDirection) {
		switch(curDirection) {
			// return opposite direction
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default:
				return null;
		}
	}
}
