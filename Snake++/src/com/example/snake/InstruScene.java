package com.example.snake;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class InstruScene extends Scene implements IOnSceneTouchListener {

	Text LeftInstru;
	Text RightInstru;
	MainActivity activity;
	private int LEFT_ID = 0;
	private int RIGHT_ID = 1;
	
	public InstruScene() {
		activity = MainActivity.getSharedInstance();
		attachChild( new Entity() );
		attachChild( new Entity() );
		LeftInstru = new Text(10 , 50, activity.mFont8B, "Tap left\nto turn\ncounter\nclockwise", "Tap left to turn\nCounter\nClockwise".length(), activity.getVertexBufferObjectManager());
		RightInstru = new Text(400 , 50, activity.mFont8B, "Tap right\nto turn\nclockwise", "Tap right\nto turn\nclockwise".length(), activity.getVertexBufferObjectManager());
		getChildByIndex(LEFT_ID).attachChild(LeftInstru);
		getChildByIndex(RIGHT_ID).attachChild(RightInstru);
		
		
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
