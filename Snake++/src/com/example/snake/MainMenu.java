package com.example.snake;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;

public class MainMenu extends MenuScene implements IOnMenuItemClickListener {
	MainActivity activity;
	final int MENU_ID  	= 0;
	final int INSTRU_ID = 2;
	
	public MainMenu() {
		super(MainActivity.getSharedInstance().mCamera);
		activity = MainActivity.getSharedInstance();
		 
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		IMenuItem startButton = new TextMenuItem(MENU_ID, activity.mFont, activity.getString(R.string.start), activity.getVertexBufferObjectManager());
		IMenuItem instruButton = new TextMenuItem(INSTRU_ID, activity.mFont, activity.getString(R.string.instru), activity.getVertexBufferObjectManager());
		
		startButton.setPosition((mCamera.getWidth() / 2 - startButton.getWidth() / 2) , mCamera.getHeight() / 3 - startButton.getHeight() / 3);
		instruButton.setPosition((mCamera.getWidth() / 2 - instruButton.getWidth() / 2) , 70+(mCamera.getHeight() / 3 - instruButton.getHeight() / 3));
		
		addMenuItem(startButton);
		addMenuItem(instruButton);
		setOnMenuItemClickListener(this);
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
	    switch ( arg1.getID() ) {
	        case MENU_ID:
	        	activity.mSelect.play();
	        	activity.setCurrentScene( new GameScene() );
	            return true;
	        case INSTRU_ID:
	        	activity.mSelect.play();
	        	activity.setCurrentScene(new InstruScene() );
	        	return true;
	        default:
	            break;
	    }
	    return false;
	}
	
	
}
