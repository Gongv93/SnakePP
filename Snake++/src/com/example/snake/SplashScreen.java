package com.example.snake;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;

public class SplashScreen extends Scene{
	MainActivity activity;
	
	public SplashScreen() {
		setBackground(new Background(0.09804f, 0.6274f, 0));
		activity = MainActivity.getSharedInstance();
		Text title1 = new Text(0, 0, activity.mFont, activity.getString(R.string.title_1), activity.getVertexBufferObjectManager());
		Text title2 = new Text(0, 0, activity.mFont, activity.getString(R.string.title_2), activity.getVertexBufferObjectManager());
		
		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);
		title2.setPosition(activity.mCamera.getWidth(), activity.mCamera.getHeight() / 2);
		
		attachChild(title1);
		attachChild(title2);
		
		title1.registerEntityModifier(new MoveXModifier(1, title1.getX(), activity.mCamera.getWidth() / 2 - title1.getWidth()));
		title2.registerEntityModifier(new MoveXModifier(1, title2.getX(), activity.mCamera.getWidth() / 2));
		
		loadResources();
	}
	
	void loadResources() {
		DelayModifier dMod = new DelayModifier(2){
		    @Override
		    protected void onModifierFinished(IEntity pItem) {
		        activity.setCurrentScene(new MainMenu());
		    }
		};
		registerEntityModifier(dMod);
	}

}
