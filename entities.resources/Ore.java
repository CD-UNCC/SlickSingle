package entities.resources;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import entities.Item;
import entities.Resource;

public class Ore extends Resource{
	
	private float purity;
	
	private Random rand;
	
	public Ore(float x, float y) throws SlickException{
		super(x, y, ID);
		
		this.x = x * modifier;
		this.y = y * modifier;
		
		initOre();
		
		depth = 0;
	}
	
	private void initOre() throws SlickException{
		
		sSImage = new Image("Images/Resources/Ore.png");
		spriteSheet = new SpriteSheet(sSImage, 64, 64);
		
		setPurity();
		
		addType("SOLID");
	}
	
	private void addOre(Item item, int amt){
		for(int i = 0; i < amt; i++){
			inventory.add(item, false);
		}
	}
	
	private void setPurity(){
		purity = rand.nextInt(100);
		if(purity < 50){
			purity += rand.nextInt(20);
		}
	}
	
	public float getPurity(){
		return purity;
	}
	
	public String getOreName(int id){
		return null;
	}
	
	public int getID(){
		return 1;
	}
}
