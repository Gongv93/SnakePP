package com.example.snake;

import java.io.File;
import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import android.graphics.Color;

public class MainActivity extends SimpleBaseGameActivity {

	// Amount of cells horizontally and vertically
	static final int CELLS_HORIZONTAL = 25;
	static final int CELLS_VERTICAL   = 15;
	
	// Size of each cell Unit: pixels
	static final int CELL_WIDTH       = 32;
	static final int CELL_HEIGHT      = CELL_WIDTH;
	// Camera resolution
	
	static final int CAMERA_WIDTH     = CELLS_HORIZONTAL * CELL_WIDTH; 	 // 800 px
	static final int CAMERA_HEIGHT    = CELLS_VERTICAL   * CELL_HEIGHT;  // 480 px
	

	
	
	public Font mFont;
	public Font mFont8B;
	public Camera mCamera;
	public Music mMusic;
	public File scoreFile;
	public File sdcard;
	
	public Sound mSelect;
	public Sound mEat;
	
	// Snake texture
	public BitmapTextureAtlas mBitmapTextureAtlas;
	public ITextureRegion mTailTextureRegion;
	public ITextureRegion mFoodTextureRegion;
	public ITextureRegion mHeadTextureRegion;
		 
	// Reference to the current scene
	public Scene mCurrentScene;
	public static MainActivity instance;
	
	// When Engine is created
	// set instance and create camera
	public EngineOptions onCreateEngineOptions() {
	    instance = this;
	    mCamera = new Camera( 0, 0, CAMERA_WIDTH, CAMERA_HEIGHT );
	    final EngineOptions engineOptions = new EngineOptions( true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera );
	    engineOptions.getAudioOptions().setNeedsMusic(true);
	    engineOptions.getAudioOptions().setNeedsSound(true);
	    return engineOptions;
	}

	// Load resources needed
	protected void onCreateResources() {
		//Font
		FontFactory.setAssetBasePath("Fonts/");
	    mFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 512, 512, TextureOptions.BILINEAR, this.getAssets(), "LCD.ttf", 52, true, Color.WHITE);
	    mFont8B = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 512, 512, TextureOptions.BILINEAR, this.getAssets(), "8B.TTF", 32, true, Color.WHITE);
	    mFont.load();
	    mFont8B.load();
	       
	    // textures
	    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	    mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 33, 33);
		mTailTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset( this.mBitmapTextureAtlas, this, "SnakeTail.png", 17, 0 );
		mHeadTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset( this.mBitmapTextureAtlas, this, "SnakeHead.png", 0, 0 );
		mFoodTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset( this.mBitmapTextureAtlas, this, "Food.png", 17, 16 );
		mBitmapTextureAtlas.load();
		
		MusicFactory.setAssetBasePath("mfx/");
		try {
			mMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "10_Arpanauts.ogg");
			mMusic.setVolume(1);
		} catch (final IOException e) {
			Debug.e(e);
		}
		SoundFactory.setAssetBasePath("mfx/");
		try {
			mSelect = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "eat.ogg");
			mEat = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "select.ogg");
		} catch(final IOException e) {
			Debug.e(e);
		}
		
	}
	
	// Set scene to the splash screen
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler( new FPSLogger() );
		mCurrentScene = new SplashScreen();
		return mCurrentScene;
	}

	// return instance
	public static MainActivity getSharedInstance() {
	    return instance;
	}
	 
	// to change the current main scene
	public void setCurrentScene(Scene scene) {
	    mCurrentScene = scene;
	    getEngine().setScene(mCurrentScene);
	}

}
