package entities.players;

import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import world.GameWorld;
import entities.Item;
import entities.Mob;
import entities.Resource;

public class Player extends Mob {

	TiledMap map;

	private SpriteSheet movement;
	Animation anim;

	private float intX;
	private float intY;

	private int direction = -1;

	private final String name;

	@SuppressWarnings("unused")
	private int height;
	@SuppressWarnings("unused")
	private int width;

	// Movement Controls //
	private static final String UP = "up";
	private static final String DOWN = "down";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String INTERACT = "interact";

	public Player(float x, float y, int clothing) throws SlickException {
		super(x, y, false);

		name = "Cocoa";

		// anim = new Animation(butts, 2);

		this.x = x * modifier;
		this.y = y * modifier;

		System.out.println(x * modifier);

		setGraphic(getClassGraphic(clothing));

		depth = 0;

		defineControls();
	}

	/**
	 * Defines User Controls
	 */
	private void defineControls() {
		define(UP, Input.KEY_W, Input.KEY_UP);
		define(DOWN, Input.KEY_S, Input.KEY_DOWN);
		define(LEFT, Input.KEY_A, Input.KEY_LEFT);
		define(RIGHT, Input.KEY_D, Input.KEY_RIGHT);
		define(INTERACT, Input.KEY_E);
	}

	/**
	 * Non Combat Movement
	 * 
	 * @throws InterruptedException
	 */
	private void nonCombatControls() {
		if (pressed(LEFT)) {
			direction = 0;
			intX = (x - modifier);
			intY = y;
			doMove(direction);
		}
		if (pressed(RIGHT)) {
			direction = 1;
			intX = (x + modifier);
			intY = y;
			doMove(direction);
		}
		if (pressed(UP)) {
			direction = 2;
			intX = x;
			intY = (y - modifier);
			doMove(direction);
		}
		if (pressed(DOWN)) {
			direction = 3;
			intX = x;
			intY = (y + modifier);
			doMove(direction);
		}
		if (pressed(INTERACT)) {
			
		}
	}

	private void doMove(int direction) {
		switch (direction) {
		case 0:
			if (collide("item", x - modifier, y) != null
					|| collide("item", x - modifier, y) == null) {
				move(-1, 0);
			}
			break;
		case 1:
			if (collide("item", x + modifier, y) != null) {
				move(1, 0);
			} else {
				move(1, 0);
			}
			break;
		case 2:
			if (collide("item", x, y - modifier) != null) {
				move(0, -1);
			} else {
				move(0, -1);
			}
			break;
		case 3:
			if (collide("item", x, y + modifier) != null) {
				move(0, 1);
			} else {
				move(0, 1);
			}
			break;

		}
	}

	public void interact(Entity e){
		if(e instanceof Mob)
			talk(e);
		if(e instanceof Resource)
			harvest(e);
	}
	
	private void harvest(Entity e) {
		// TODO Auto-generated method stub
		
	}

	private void talk(Entity e) {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * Player Update Loop
	 */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		nonCombatControls();
	}

	/**
	 * Item Collision detection
	 */
	@Override
	public void collisionResponse(Entity itemPickup) {
		if (itemPickup instanceof Item) {
			Item item = (Item) itemPickup;
			this.pickUp(item);
		}
	}

	/**
	 * Map Information and Limit functions
	 * 
	 * @param h
	 *            -Height
	 * @param w
	 *            -Width
	 * @param map
	 *            -Tiled Map
	 */
	public void setHeight(int h) {
		this.height = h - modifier;
	}

	public void setWidth(int w) {
		this.width = w - modifier;
	}

	/**
	 * Name Getter
	 * 
	 * @return - name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the map
	 * 
	 * @param map
	 */
	public void setMap(TiledMap map) {
		this.map = map;
	}
	
	public float getDirX(){
		return intX;
	}
	
	public float getDirY(){
		return intY;
	}

}
