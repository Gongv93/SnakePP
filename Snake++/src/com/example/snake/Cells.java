package com.example.snake;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class Cells extends Sprite implements InterCell {
	static final int CELL_WIDTH       = 32;
	static final int CELL_HEIGHT      = CELL_WIDTH;
	
	public int cellX;
	public int cellY;
	
	public Cells( int pCellX, int pCellY, int pWidth, int pHeight, ITextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		super(pCellX * CELL_WIDTH, pCellY * CELL_HEIGHT, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
		this.cellX = pCellX;
		this.cellY = pCellY;
	}

	public int getCX() {
		return this.cellX;
	}
	
	public int getCY() {
		return this.cellY;
	}
	
	public void setCell( int CellX, int CellY ) {
		this.cellX = CellX;
		this.cellY = CellY;
		this.setPosition(this.cellX * CELL_WIDTH, this.cellY * CELL_HEIGHT);
	}
	
	public void setCell( InterCell pCell ) {
		this.setCell(pCell.getCX(), pCell.getCY());
	}
	
	@Override
	public boolean sameCell( InterCell pCellEntity ) {
		return this.cellX == pCellEntity.getCX() && this.cellY == pCellEntity.getCY();
	}
}
