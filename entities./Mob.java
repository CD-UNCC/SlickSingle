package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.inventory.Inventory;
import world.GameWorld;
import it.randomtower.engine.entity.Entity;

public class Mob extends Entity {

	protected static final int modifier = 64;
	protected static final double modDouble = 64.0;

	@SuppressWarnings("unused")
	private boolean immortal;

	@SuppressWarnings("unused")
	private String name;

	private int health;
	private int maxHealth;

	private boolean moving;

	protected Inventory inventory;

	protected Image dragoonImage;
	protected Image eDragoonImage;

	public Mob(float x, float y, boolean immortal) throws SlickException {
		super(x, y);

		this.immortal = immortal;

		defineImages();

		addType("SOLID");
		setHitBox(0, 0, (int) modifier, (int) modifier);

		depth = 0;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
	}

	/**
	 * Movement
	 * 
	 * @param xa
	 * @param ya
	 */

	public void move(float xa, float ya) {
		float xc = x + (xa * modifier);
		float yc = y + (ya * modifier);

		if (collide("SOLID", xc, yc) == null && xc >= 0 && yc >= 0
				&& yc < GameWorld.getMap().getHeight() * modifier
				&& xc < GameWorld.getMap().getWidth() * modifier) {
			x = xc;
			y = yc;
		}
	}

	/**
	 * All character info is set here
	 * 
	 * @param id
	 * @param name
	 * @param health
	 */
	public void setCharacterInfo(int id, String name, int experience, int health) {

		changeClass(id);
		changeClassGraphic(id);
		this.name = name;
		this.maxHealth = health;
	}

	public void changeClass(int id) {
		if (id > 0 && id < 10) {
			changeClassGraphic(id);
		}
	}

	private Image changeClassGraphic(int id) {
		switch (id) {
		case 1:
			// setGraphic to warrior
			return dragoonImage;
		case 2:
			return eDragoonImage;
		default:
			return changeClassGraphic(1);
		}
	}

	public Image getClassGraphic(int id) {
		return changeClassGraphic(id);
	}

	/**
	 * Sets all the images
	 * 
	 * @throws SlickException
	 */

	public void defineImages() throws SlickException {
		dragoonImage = new Image("Images/Entities/Player Classes/Player3.png");
		eDragoonImage = new Image(
				"Images/Entities/Enemy Humanoid/Enemy_Dragoon.png");
	}
	
	public void setSpriteSheet(){
		
	}

	/**
	 * Item Pickup
	 * 
	 * @param item
	 *            - Item to pick up
	 */

	public void pickUp(Item item) {
		if (this.inventory.isFull() || item == null) {
		} else {
			world.remove(item);
			this.inventory.add(item, false);
		}
	}

	public Inventory inventory() {
		return inventory;
	}

	/**
	 * Function to Get Player Position for Camera
	 */
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	/**
	 * Moves the character to a new Location
	 * 
	 * @param xa
	 *            - new player x Position
	 * @param ya
	 *            - new player y Position
	 */
	public void reposition(int xa, int ya) {
		x = xa;
		y = ya;
	}
}
