package com.example.snake;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class GameOverScene extends Scene implements IOnSceneTouchListener {
	// Amount of cells horizontally and vertically
		static final int CELLS_HORIZONTAL = 25;
		static final int CELLS_VERTICAL   = 15;
		// Size of each cell Unit: pixels
		static final int CELL_WIDTH       = 32;
		static final int CELL_HEIGHT      = CELL_WIDTH;
		// Camera resolution
		static final int CAMERA_WIDTH     = CELLS_HORIZONTAL * CELL_WIDTH; 	 // 800 px
		static final int CAMERA_HEIGHT    = CELLS_VERTICAL   * CELL_HEIGHT;  // 480 px
	
	
	private Text mGameOverText;
	private Text mScoreText;
	MainActivity activity;
	
	private int LOSE_ID = 0;
	private int SCORE_ID = 1;
	
	public GameOverScene(int score) {
		//super(MainActivity.getSharedInstance().mCamera);
		activity = MainActivity.getSharedInstance();
		int i;
		for( i = 0; i < 2; i++ ) 
			attachChild( new Entity() );
		
		mGameOverText = new Text((CAMERA_WIDTH / 2) - 150 , (CAMERA_HEIGHT / 2) - 50, activity.mFont8B, "Game Over.", "Game Over.".length(), activity.getVertexBufferObjectManager());
		mScoreText = new Text((CAMERA_WIDTH / 2) - 150 , (CAMERA_HEIGHT / 2) + 20, activity.mFont8B, "Score: " + score , "Score: 99999".length(), activity.getVertexBufferObjectManager());
		getChildByIndex(LOSE_ID).attachChild(mGameOverText);
		getChildByIndex(SCORE_ID).attachChild(mScoreText);
		
		
		
		setOnSceneTouchListener(this);
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// Left screen touched: Counter clockwise rotation
		if(pSceneTouchEvent.isActionDown()) {
			activity.setCurrentScene(new MainMenu());
			return true;
		}
		return false;
	}

}
