package com.example.snake;


public interface InterCell {
	public abstract int getCX();
	public abstract int getCY();

	public abstract void setCell( InterCell pCell );
	public abstract void setCell( int pCellX, int pCellY );

	public abstract boolean sameCell( InterCell pCell );
}
