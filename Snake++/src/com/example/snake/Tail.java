package com.example.snake;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;



public class Tail extends Cells {
	public Tail( SnakeHead SnakeHead, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		this(SnakeHead.cellX, SnakeHead.cellY, pTextureRegion, pVertexBufferObjectManager);
	}

	public Tail( int pCellX, int pCellY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pCellX, pCellY, CELL_WIDTH, CELL_HEIGHT, pTextureRegion, pVertexBufferObjectManager);
	}
}
