package com.example.snake;

import java.util.LinkedList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import org.andengine.util.math.MathUtils;

import android.opengl.GLES20;

public class GameScene extends Scene implements IOnSceneTouchListener{
	
	// Amount of cells horizontally and vertically
	static final int CELLS_HORIZONTAL = 25;
	static final int CELLS_VERTICAL   = 15;
	// Size of each cell Unit: pixels
	static final int CELL_WIDTH       = 32;
	static final int CELL_HEIGHT      = CELL_WIDTH;
	// Camera resolution
	static final int CAMERA_WIDTH     = CELLS_HORIZONTAL * CELL_WIDTH; 	 // 800 px
	static final int CAMERA_HEIGHT    = CELLS_VERTICAL   * CELL_HEIGHT;  // 480 px
	
	//private int BACKGROUND_ID = 0;
	private int FOOD_ID 	  = 0;
	private int SNAKE_ID 	  = 1;
	private int SCORE_ID 	  = 2;
	public int score;
	public boolean isRunning;
	
	public Snake mSnake;
	public Food mFood;
	Camera mCamera;
	public DigitalOnScreenControl mControl;
	public Text mScoreboard;
	
	
	MainActivity activity;

	/**
	  	public BitmapTextureAtlas mBitmapTextureAtlas;
		public ITextureRegion mTailPartTextureRegion;
		public ITextureRegion mHeadTextureRegion;
		public ITextureRegion mFoodTextureRegion;
	
		public BitmapTextureAtlas mBackgroundTexture;
		public ITextureRegion mBackgroundTextureRegion;
		
		public BitmapTextureAtlas mControlTexture;
		public ITextureRegion mControlBaseTextureRegion;
		public ITextureRegion mControlKnobTextureRegion;
	 */
	
	public GameScene() {
		int i;
		
		score = 0;
		isRunning = true;
		activity = MainActivity.getSharedInstance();
		
		setOnSceneTouchListener(this);
		
		for( i = 0; i < 3; i++ ) 
			attachChild( new Entity() );
		// Set Music
		activity.mMusic.setLooping(true);
		activity.mMusic.play();
		activity.mMusic.seekTo(0);

		// Set Background
		setBackground(new Background(0, 0, 0));
		
		mScoreboard = new Text(5, 5, activity.mFont, "Score: 0", "Score: 9999".length(), activity.getVertexBufferObjectManager());
		mScoreboard.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mScoreboard.setAlpha(0.5f);
		getChildByIndex(SCORE_ID).attachChild(mScoreboard);
		
		// Set snake in the middle
		mSnake = new Snake(Direction.RIGHT, CELLS_HORIZONTAL / 2, CELLS_VERTICAL / 2, activity.mHeadTextureRegion, activity.mTailTextureRegion, activity.getVertexBufferObjectManager());
		
		// Create the tail
		mSnake.grow();
		getChildByIndex(SNAKE_ID).attachChild(mSnake);
		
		// Create food
		mFood = new Food(0, 0, activity.mFoodTextureRegion, activity.getVertexBufferObjectManager());
		mFood.setCell(MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
		getChildByIndex(FOOD_ID).attachChild(mFood);	
		
		// Game clock
		registerUpdateHandler(new TimerHandler(0.25f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				if(GameScene.this.isRunning) {
					try {
						GameScene.this.mSnake.move();
					} 
					catch (final SuicideException e) {
						// GameOver scene starts
						isRunning = false;
						activity.mMusic.pause();
						activity.setCurrentScene(new GameOverScene(score));
					}
					// Get new snake position
					if(mSnake.getHead().getCX() < 0 || mSnake.getHead().getCX() >= CELLS_HORIZONTAL || mSnake.getHead().getCY() < 0 || mSnake.getHead().getCY() >= CELLS_VERTICAL) {
						// GameOver scene starts
						isRunning = false;
						activity.mMusic.pause();
						activity.setCurrentScene(new GameOverScene(score));
					} 
					else if(mSnake.getHead().sameCell(mFood)) {
						// If player eats food
						activity.mEat.play();
						score += 50;
						mScoreboard.setText("Score: " + score);
						mSnake.grow();
						
						mFood.setCell(MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
						
						while( sameAsTail(mFood) ) {
							mFood.setCell(MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
						}
					}
				}
			}
		}));
	}
	
	boolean sameAsTail( InterCell pCell ) {
		int i;
		LinkedList<Tail> temp;
		temp = mSnake.getTail();
		for(i = 0; i < mSnake.getTailLength(); i++) {
			if( pCell.sameCell(temp.get(i)) ) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// Left screen touched: Counter clockwise rotation
		if(pSceneTouchEvent.isActionDown()) {
			if( pSceneTouchEvent.getX() < CAMERA_WIDTH/2 ) {
				switch( mSnake.getDir() ) {
					case UP:
						GameScene.this.mSnake.setDir(Direction.LEFT);
						break;
					case DOWN:
						GameScene.this.mSnake.setDir(Direction.RIGHT);
						break;
					case LEFT:
						GameScene.this.mSnake.setDir(Direction.DOWN);
						break;
					case RIGHT:
						GameScene.this.mSnake.setDir(Direction.UP);
						break;
				}
				
			}
			// Right screen touched: clockwise rotation
			else {
				switch( mSnake.getDir() ) {
					case UP:
						GameScene.this.mSnake.setDir(Direction.RIGHT);
						break;
					case DOWN:
						GameScene.this.mSnake.setDir(Direction.LEFT);
						break;
					case LEFT:
						GameScene.this.mSnake.setDir(Direction.UP);
						break;
					case RIGHT:
						GameScene.this.mSnake.setDir(Direction.DOWN);
						break;
				}
			}
			return true;
		}	
		return false;
	}	


}
