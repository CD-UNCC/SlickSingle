package world;

import it.randomtower.engine.ME;
import it.randomtower.engine.World;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import entities.Resource;
import entities.players.Player;
import entities.resources.Fish;
import entities.resources.Herb;
import entities.resources.Ore;
import entities.resources.Tree;
import factory.Factory;

public class GameWorld extends World {

	// UNIVERSAL TRUTHS //
	public static int MODIFIER = 64;

	// MAP VARIABLES //
	private static TiledMap map;
	private static Camera cam;
	
	// ENTITIES //
	private static Player player;
	private static Entity objInt = null;
	
	// FACTORY //
	private static Factory factory;

	// RESOURCES // 
	private static Resource resources;
	
	
	// CONTROLS //
	private Input input = container.getInput();

	/**
	 * GameWorld Constructor
	 * 
	 * @param id
	 *            - State ID
	 * @param container
	 *            - Window
	 * @throws SlickException
	 *             - Exception Handler
	 */
	public GameWorld(int id, GameContainer container) throws SlickException {
		super(id, container);
		ME.keyToggleDebug = Input.KEY_1;
	}

	/**
	 * Update Function GAMEWORLD
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);

		// MAP
		map = new TiledMap("Images/tileMAp/test1.tmx");

		// CAMERA
		cam = new Camera(container, map);

		// ENTITIES
		player = new Player(3, 3, 1);
		add(player);
	}

	/**
	 * Update Function GAMEWORLD
	 * 
	 * @param container
	 *            - Window
	 * @param game
	 *            - Game State
	 * @param delta
	 *            - Game Time
	 */

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		cam.centerOn(player.getX(), player.getY());
		keyShortcuts(game);
	}

	/**
	 * Render Function GAMEWORLD
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);

		g.setColor(Color.black);       // Container Colors
		g.setBackground(Color.white); //

		cam.renderDetails(g);   // Sends g off for Camera Rendering

		for (Entity e : getEntities()) {    //Renders all Entities
			e.render(container, g);
		}
	}

	private void keyShortcuts(StateBasedGame game) throws SlickException {
		// Menu Transitions
		if (input.isKeyPressed(Input.KEY_I)) {
			game.enterState(1);
		}
	}
	
	private void interaction(){
		for(Entity e : getEntities()){
			if(e.x == player.getDirX() && e.y == player.getDirY())
				player.interact(e);
		}
	}

	/**
	 * Gets The Player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * Gets Current Map
	 * 
	 * @return - map
	 */
	public static TiledMap getMap() {
		return map;
	}
}
