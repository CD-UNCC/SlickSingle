package entities;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import entities.inventory.Inventory;
import entities.resources.Fish;
import entities.resources.Herb;
import entities.resources.Ore;
import entities.resources.Tree;
import it.randomtower.engine.entity.Entity;

public class Resource extends Entity{
	
	// UNIVERSAL TRUTH
	protected static final float modifier = 64;
	
	// Resource ID (i.e. Tree, ore, etc)
	protected static int ID;
	
	// Individual ID (i.e Iron, Steel, Mithril for Ore)
	protected int subID;
	
	// Skill of Player
	private int pSkill;
	
	// Skill required to Harvest
	protected int skillReq;
	
	// Name of Resource
	protected String name;
	
	// Sprite Sheet Image
	protected Image sSImage;
	
	// Loaded Sprite Sheet
	protected SpriteSheet spriteSheet;
	
	// Resource Inventory
	protected Inventory inventory;
	
	// Harvested
	protected boolean isHarvested;

	// Rand for ID Generation
	protected Random newRand = new Random(System.currentTimeMillis());
	
	
	public Resource(float x, float y, int pSkill) {
		super(x, y);
		
		setHitBox(0f, 0f, (int) modifier, (int) modifier); //Hitbox
		
		inventory = new Inventory(5); //Inventory for Resource Items
		
		setID(pSkill); //Gives Resource an ID
		
		setInfo(ID); //Sets all info based on ID
	}
	
	/**
	 * Sets all Ore info at Instantiation
	 */
	private void setInfo(int id){
		
		isHarvested = false;
		
		assignName();
		
		switch(id){
		case 1:
			skillReq = 0;
			setGraphic(spriteSheet.getSubImage(0, 0));
			break;
		case 2:
			skillReq = 20;
			setGraphic(spriteSheet.getSubImage(64, 0));
			break;
		case 3:
			skillReq = 35;
			setGraphic(spriteSheet.getSubImage(128, 0));
			break;
		case 4:
			skillReq = 55;
			setGraphic(spriteSheet.getSubImage(192, 0));
			break;
		case 5:
			skillReq = 70;
			setGraphic(spriteSheet.getSubImage(256, 0));
			break;
		case 6:
			skillReq = 85;
			setGraphic(spriteSheet.getSubImage(320, 0));
			break;
		default:
			skillReq = 150;
			break;
		}
	}
	
	private void assignName(){
		switch(ID){
		case 1: // ORE
			switch (subID){
			case 1:
				name = "Stone";
				break;
			case 2:
				name = "Bronze";
				break;
			case 3:
				name = "Iron";
				break;
			case 4:
				name = "Coal";
				break;
			case 5:
				name = "Mithril";
			default:
				break;
			}
		case 2: // TREE
			switch (subID){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				break;
			}
		case 3: // FISH
			switch (subID){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				break;
			}
		case 4: // HERB
			switch (subID){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				break;
			}
		case 5:
			switch (subID){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				break;
			}
		default:
			name = null;
		}
	}
	
	public void harvest(){
		isHarvested = true;
		// either a give function called here or handle harvest in another way
		this.destroy();
	}

	protected void setID(int pSkill){
		ID = newRand.nextInt(5);
	}
}
