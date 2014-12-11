package world;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.randomtower.engine.ResourceManager;

public class Tile {
	
	// UNIVERSAL TRUTH
	private static final int modifier = 64;
	private static final float scale = 2;
	
	// X & Y position
	private int x;
	private int y;
	
	// Tile Image				          ////////////////////////////////////////////////////////////////////////
	private Image tileImage;      /// (x, 2) - in/topLeft // (x, 3) - in/topMid // (x, 4) - in/topRight///
								                /// (x, 6) - in/midLeft //////////////////////// (x, 6) - in/midRight///
								                /// (x, 7) - in/botLeft // (x, 8) - in/botMid // (x, 9) - in/botRight///
							               	////////////////////////////////////////////////////////////////////////////
								              // (x, 10) - out/topLeft // (x, 11) - out/topMid // (x, 12) - out/topRight//
								              // (x, 13) - out/midLeft ////////////////////////// (x, 14) - out/midRight//
								              // (x, 17) - out/botLeft // (x, 16) - out/botMid // (x, 15) - out/botRight//
								              ////////////////////////////////////////////////////////////////////////////
	
	// Is tile occupied
	private boolean occupied;
	
	// Is tile traversable
	private boolean traversable;
	
	public Tile(int x,int y, int id, int subID) throws SlickException{
		this.x = x * modifier;
		this.y = y * modifier;
		
		if(id >= 5)
			traversable = false;
		else 
			traversable = true;
		
		tileImage = ResourceManager.getSpriteSheet("env").getSubImage(id * modifier, subID * modifier).getScaledCopy(scale);
	}
}
