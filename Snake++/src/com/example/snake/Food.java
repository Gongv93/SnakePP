package com.example.snake;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class Food extends Cells{
	
	static final int CELL_WIDTH       = 32;
	static final int CELL_HEIGHT      = CELL_WIDTH;
	
	public Food( int cellX, int cellY,  ITextureRegion pTiledTextureRegion,  VertexBufferObjectManager pVertexBufferObjectManager) {
		super(cellX, cellY, CELL_WIDTH, CELL_HEIGHT, pTiledTextureRegion, pVertexBufferObjectManager);
	}
}
