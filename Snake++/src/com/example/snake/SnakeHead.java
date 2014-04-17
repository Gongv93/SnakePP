package com.example.snake;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SnakeHead extends Cells{
	static final int CELL_WIDTH       = 32;
	static final int CELL_HEIGHT      = CELL_WIDTH;
	
	public SnakeHead( int pCellX, int pCellY, ITextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		super( pCellX, pCellY, CELL_WIDTH, CELL_HEIGHT, pTiledTextureRegion, pVertexBufferObjectManager );

		this.setRotationCenterY( CELL_HEIGHT / 2 );
	}
	
	public void setRotation( Direction direction ) {
		switch( direction ) {
			case LEFT:
				this.setRotation( 90 );
				break;
			case RIGHT:
				this.setRotation( 270 );
				break;
			case UP:
				this.setRotation( 180 );
				break;
			case DOWN:
				this.setRotation( 0 );
				break;
		}
	}
}
